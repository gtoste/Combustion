package com.example.combustion.ui.fuel;

import static com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.combustion.DatabaseHelper;
import com.example.combustion.DatabaseManager;
import com.example.combustion.databinding.FragmentFuelBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.OnTokenCanceledListener;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class FuelFragment extends Fragment {

    private FragmentFuelBinding binding;

    LocationManager locationManager;

    final String[] PERMISSIONS = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

    private ActivityResultContracts.RequestMultiplePermissions multiplePermissionsContract;
    private ActivityResultLauncher<String[]> multiplePermissionLauncher;

    Context mContext;
    Activity mActivity;
    private ArrayList<String> cars;
    DatabaseManager db;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FuelViewModel fuelViewModel =
                new ViewModelProvider(this).get(FuelViewModel.class);

        binding = FragmentFuelBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mContext = getContext();
        mActivity = getActivity();

        fuelViewModel.getCarsMutableLiveData(mContext).observe(getViewLifecycleOwner(), cars ->{
            this.cars = cars;
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, cars);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            binding.cars.setAdapter(adapter);
        });

        db = new DatabaseManager(mContext);
        binding.send.setOnClickListener(this::sendFuelData);
        return root;
    }



    private void sendFuelData(View view){
        String onTheMeter = binding.onTheMeter.getText().toString().trim();
        String liters = binding.liters.getText().toString().trim();
        String cost_per_liter = binding.costPerLiter.getText().toString().trim();
        String location = binding.stationEditText.getText().toString().trim();

        boolean isOk = true;
        if(onTheMeter.isEmpty())
        {
            binding.onTheMeter.setError("Required");
            binding.onTheMeter.requestFocus();
            isOk = false;
        }

        if(liters.isEmpty())
        {
            binding.liters.setError("Required");
            binding.liters.requestFocus();
            isOk = false;
        }

        if(cost_per_liter.isEmpty())
        {
            binding.costPerLiter.setError("Required");
            binding.costPerLiter.requestFocus();
            isOk = false;
        }

        if(location.isEmpty())
        {
            binding.stationEditText.setError("Required");
            binding.stationEditText.requestFocus();
            isOk = false;
        }

        if(isOk)
        {
            try {
                int odometer = Integer.parseInt(onTheMeter);
                double fuel = Double.parseDouble(liters);
                double price = Double.parseDouble(cost_per_liter);

                TextView textView = (TextView)binding.cars.getSelectedView();
                String car = textView.getText().toString();
                String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
                if(db.addFuelRecord(odometer, fuel, location, price, car, date)){
                    Toast.makeText(mContext, "Success!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(mContext, "Fail!", Toast.LENGTH_SHORT).show();
                }

            }catch (Exception e){
                Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
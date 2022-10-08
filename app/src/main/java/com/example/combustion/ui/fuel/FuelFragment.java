package com.example.combustion.ui.fuel;

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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.combustion.databinding.FragmentFuelBinding;
import com.example.combustion.ui.DBManager;
import com.example.combustion.ui.Fueling;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class FuelFragment extends Fragment {

    private FragmentFuelBinding binding;

    LocationManager locationManager;

    private double latitude;
    private double longitude;
    private Geocoder geocoder;
    DBManager dbManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FuelViewModel fuelViewModel =
                new ViewModelProvider(this).get(FuelViewModel.class);

        binding = FragmentFuelBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.send.setOnClickListener(view -> {
            try {
                sendFuelData(view);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Context mContext = getContext();
        Activity activity = getActivity();

        dbManager = new DBManager(mContext).open();
        geocoder= new Geocoder(mContext, Locale.getDefault());

        locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
        }else{
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    500,
                    50, locationListener);
        }
        return root;
    }

    private final LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(@NonNull Location location) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            Toast.makeText(getContext(), String.valueOf(latitude), Toast.LENGTH_LONG).show();
        }
    };

    private void sendFuelData(View view) throws IOException {
        String onTheMeter = binding.onTheMeter.getText().toString().trim();
        String liters = binding.liters.getText().toString().trim();
        String cost_per_liter = binding.costPerLiter.getText().toString().trim();


        try {
            int onTheMeterI = Integer.parseInt(onTheMeter);
            double litersD = Double.parseDouble(liters);
            double cost_per_literD = Double.parseDouble(cost_per_liter);

            List<Address> addresses;
            @SuppressLint("MissingPermission") Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            String address = addresses.get(0).getAddressLine(0);
            Toast.makeText(getContext(), address, Toast.LENGTH_LONG).show();
            Fueling fueling = new Fueling(onTheMeterI, litersD, address , cost_per_literD);

            dbManager.insert(fueling);
        }catch (Exception e){
            Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 1 && permissions.length > 0 && ContextCompat.checkSelfPermission(getContext(),Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 50, locationListener);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
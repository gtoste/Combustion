package com.example.combustion.ui.home;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.combustion.DatabaseHelper;
import com.example.combustion.R;
import com.example.combustion.StatisticModel;
import com.example.combustion.StatisticsAdapter;
import com.example.combustion.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    ArrayList<String> cars;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        homeViewModel.getStatisticsMutableLiveData(getContext()).observe(getViewLifecycleOwner(), statistics -> {
            ArrayList<StatisticModel> statisticModels = new ArrayList<StatisticModel>();
            statisticModels.add(new StatisticModel(R.drawable.card_icon_combustion, "Combustion",String.valueOf(statistics.getCombustion())));
            statisticModels.add(new StatisticModel(R.drawable.card_icon_distance, "Distance",String.valueOf(statistics.getDistance())));
            statisticModels.add(new StatisticModel(R.drawable.card_icon_fuel, "Fuel",String.valueOf(statistics.getFuel())));
            statisticModels.add(new StatisticModel(R.drawable.card_icon_cost, "Cost",String.valueOf(statistics.getCost())));
            statisticModels.add(new StatisticModel(R.drawable.card_icon_cost_per_kilometer, "zł / km",String.valueOf(statistics.getCost_per_kilometer())));

            StatisticsAdapter statisticsAdapter = new StatisticsAdapter(getContext(), statisticModels);
            binding.gridViewStats.setAdapter(statisticsAdapter);
        });
        cars = new ArrayList<>();

        homeViewModel.getCarsMutableLiveData(getContext()).observe(getViewLifecycleOwner(), cars ->{
            this.cars = cars;
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, cars);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            binding.cars.setAdapter(adapter);
        });

        DatabaseHelper db = new DatabaseHelper(getContext());

        binding.add.setOnClickListener((view)->{
            Context mContext = getContext();
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setTitle("Add car");

            final EditText input = new EditText(mContext);
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);

            builder.setPositiveButton("OK", (dialog, which) -> {
                String name = input.getText().toString().trim();
                cars.add(name);
                homeViewModel.getCarsMutableLiveData(getContext()).setValue(cars);
                db.addCarRecord(name);
                Toast.makeText(mContext, "Added car to database!", Toast.LENGTH_LONG).show();

                //TODO REFRESH SPINNER
            });
            builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

            builder.show();
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
package com.example.combustion.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.combustion.DatabaseHelper;
import com.example.combustion.Statistics;
import com.example.combustion.databinding.FragmentHomeBinding;
import com.example.combustion.ui.DBManager;
import com.example.combustion.ui.Fueling;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private DBManager dbManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        dbManager = new DBManager(getContext()).open();
        ArrayList<Fueling> fuelings;
        fuelings = dbManager.fetchAll();

        Statistics statistics = new Statistics(fuelings);

        binding.combustion.setText(String.valueOf(statistics.getCombustion()));
        binding.distance.setText(String.valueOf(statistics.getDistance()));
        binding.fuel.setText(String.valueOf(statistics.getFuel()));
        binding.cost.setText(String.valueOf(statistics.getCost()));
        binding.costPerKilometer.setText(String.valueOf(statistics.getCost_per_kilometer()));

        dbManager.close();


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
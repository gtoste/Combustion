package com.example.combustion.ui.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.combustion.HistoryData;
import com.example.combustion.HistoryDataAdapter;
import com.example.combustion.databinding.FragmentHistoryBinding;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {

    private FragmentHistoryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HistoryViewModel historyViewModel =
                new ViewModelProvider(this).get(HistoryViewModel.class);

        binding = FragmentHistoryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        ArrayList<HistoryData> dataArrayList = new ArrayList<>();
        dataArrayList.add(new HistoryData(30.3, 6.84, 200.72, "Berlingo", 1024, "21e312"));
        dataArrayList.add(new HistoryData(30.3, 6.84, 200.72, "Berlingo", 1024, "1665609574006"));
        dataArrayList.add(new HistoryData(30.3, 6.84, 200.72, "Berlingo", 1024, "1665609574006"));
        dataArrayList.add(new HistoryData(30.3, 6.84, 200.72, "Berlingo", 1024, "1665609574006"));
        dataArrayList.add(new HistoryData(30.3, 6.84, 200.72, "Berlingo", 1024, "1665609574006"));

        HistoryDataAdapter historyDataAdapter = new HistoryDataAdapter(getContext(),0, dataArrayList);
        this.binding.historyListView.setAdapter(historyDataAdapter);

//        historyViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
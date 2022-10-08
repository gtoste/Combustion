package com.example.combustion;

import com.example.combustion.ui.Fueling;

import java.util.ArrayList;

public class Statistics {

    private ArrayList<Fueling> fuelings;
    private double combustion;
    private int distance;
    private double fuel;
    private double cost;
    private double cost_per_kilometer;

    public Statistics(ArrayList<Fueling> _fuelings) {
        this.fuelings = _fuelings;

        if(this.fuelings.size() > 0)
        {
            distance = fuelings.get(0).getOdometer() - fuelings.get(fuelings.size() - 1).getOdometer();
            for(Fueling fueling: fuelings)
            {
                fuel += fueling.getFuel_refueled();
                cost += fueling.getFuel_refueled() * fueling.getCost_per_liter();
            }
        }
    }

    public int getDistance() {
        return distance;
    }

    public double getFuel() {
        return fuel;
    }

    public double getCost() {
        return cost;
    }

    public double getCombustion() {
        if(distance > 0) return fuel/distance * 100;
        else return 0;
    }

    public double getCost_per_kilometer()
    {
        if(distance > 0) return cost / distance;
        else return 0;
    }
}

package com.example.combustion.ui;

public class Fueling {
    private int odometer;
    private double fuel_refueled;
    private String station;
    private double cost_per_liter;

    public Fueling(int odometer, double fuel_refueled, String station, double cost_per_liter) {
        this.odometer = odometer;
        this.fuel_refueled = fuel_refueled;
        this.station = station;
        this.cost_per_liter = cost_per_liter;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public double getFuel_refueled() {
        return fuel_refueled;
    }

    public void setFuel_refueled(double fuel_refueled) {
        this.fuel_refueled = fuel_refueled;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public double getCost_per_liter() {
        return cost_per_liter;
    }

    public void setCost_per_liter(double cost_per_liter) {
        this.cost_per_liter = cost_per_liter;
    }
}

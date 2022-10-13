package com.example.combustion.ui;

public class Fueling {
    private int _id;
    private int odometer;
    private double fuel_refueled;
    private String station;
    private double cost_per_liter;
    private String car;
    private String date;

    public Fueling(int id, int odometer, double fuel_refueled, String station, double cost_per_liter, String car, String date) {
        this._id = id;
        this.odometer = odometer;
        this.fuel_refueled = fuel_refueled;
        this.station = station;
        this.cost_per_liter = cost_per_liter;
        this.car = car;
        this.date = date;
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
    public String getCar() {
        return car;
    }

    public int get_id() {
        return _id;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

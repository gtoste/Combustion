package com.example.combustion;

public class HistoryData {
    double fuel;
    double price;
    double total;
    String car;
    int distance;
    String date;

    public HistoryData(double fuel, double price, double total, String car, int distance, String date)
    {
        this.fuel = fuel;
        this.price = price;
        this.total = total;
        this.car = car;
        this.distance = distance;
        this.date = date;
    }


    public double getFuel() {
        return fuel;
    }

    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

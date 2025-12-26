package com.hotel.services;

public class ReportService {

    public double calculateBill(double pricePerNight, int days) {
        return pricePerNight * days;
    }
}

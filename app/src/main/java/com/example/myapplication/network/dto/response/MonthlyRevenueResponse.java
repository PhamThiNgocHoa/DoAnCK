package com.example.myapplication.network.dto.response;



public class MonthlyRevenueResponse {
    int month;
    int revenue;

    public MonthlyRevenueResponse(int month, int revenue) {
        this.month = month;
        this.revenue = revenue;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    @Override
    public String toString() {
        return "MonthlyRevenueResponse{" +
                "month=" + month +
                ", revenue=" + revenue +
                '}';
    }
}

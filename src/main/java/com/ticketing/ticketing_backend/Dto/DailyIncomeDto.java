package com.ticketing.ticketing_backend.Dto;

public class DailyIncomeDto {
    private String date;
    private Double totalIncome;
    public DailyIncomeDto(String date, Double totalIncome) {
        this.date = date;
        this.totalIncome = totalIncome;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public Double getTotalIncome() {
        return totalIncome;
    }
    public void setTotalIncome(Double totalIncome) {
        this.totalIncome = totalIncome;
    }
}

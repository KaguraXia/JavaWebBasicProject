package com.example.model;

import java.sql.Date;

public class DailyTotal {
    private Date day;       // 日期（精确到天）
    private double total;   // 当天总金额
    public DailyTotal(Date day, double total) {
        this.day = day;
        this.total = total;
    }

    public Date getDay() {
        return day;
    }
    public double getTotal() {
        return total;
    }
}
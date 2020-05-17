package com.example.firstapp.presentation.model;

public class Info {

    private double pages;


    public void setpages(double pages) {
        this.pages = pages;
    }
    public double getpages() {
        return pages;
    }

    public Integer getpagesint(){
        return (int) pages;
    }
}
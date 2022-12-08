package com.bcafinance._01springbootrestapi.dto;
/*
Created by IntelliJ IDEA 2022.2.3 (Community Edition)
Build #IC-222.4345.14, built on October 5, 2022
@Author widia a.k.a. Widia
Created on 06/12/2022 11:07
Last modified on 11:07
Version 1.0
*/


import java.time.LocalDate;

public class CarDTO {

    private Long carId;

    private String CarName;

    private String CarModel;

    private Double Price;

    private Double Discount;

    private Integer Stock;

    private Integer Sold;

    private LocalDate DateIn;

    private LocalDate DateOut;

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getCarName() {
        return CarName;
    }

    public void setCarName(String carName) {
        CarName = carName;
    }

    public String getCarModel() {
        return CarModel;
    }

    public void setCarModel(String carModel) {
        CarModel = carModel;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public Double getDiscount() {
        return Discount;
    }

    public void setDiscount(Double discount) {
        Discount = discount;
    }

    public Integer getStock() {
        return Stock;
    }

    public void setStock(Integer stock) {
        Stock = stock;
    }

    public Integer getSold() {
        return Sold;
    }

    public void setSold(Integer sold) {
        Sold = sold;
    }

    public LocalDate getDateIn() {
        return DateIn;
    }

    public void setDateIn(LocalDate dateIn) {
        DateIn = dateIn;
    }

    public LocalDate getDateOut() {
        return DateOut;
    }

    public void setDateOut(LocalDate dateOut) {
        DateOut = dateOut;
    }
}

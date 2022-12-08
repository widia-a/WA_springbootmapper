package com.bcafinance._01springbootrestapi.models;
/*
Created by IntelliJ IDEA 2022.2.3 (Community Edition)
Build #IC-222.4345.14, built on October 5, 2022
@Author widia a.k.a. Widia
Created on 06/12/2022 10:28
Last modified on 10:28
Version 1.0
*/

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "MstCars")
public class Cars implements Serializable  {
    private static final long serialversionUID = 1L;

    @Id
    @Column(name = "CarID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;

    @Column(name = "CarName" , nullable = false)
    private String carName;

    @Column(name = "CarModel",nullable = false)
    private String carModel;

    @Column(name = "Sold", nullable = false)
    private int sold;

    @Column(name = "Stock", nullable = false)
    private int stock;

    @Column(name = "Price", nullable = false)
    private double price;

    @Column(name = "Discount", nullable = false)
    private double discount;

    @Column(name = "DateIn", nullable = false)
    private LocalDate dateIn;

    @Column(name = "DateOut", nullable = false)
    private LocalDate dateOut;

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public LocalDate getDateIn() {
        return dateIn;
    }

    public void setDateIn(LocalDate dateIn) {
        this.dateIn = dateIn;
    }

    public LocalDate getDateOut() {
        return dateOut;
    }

    public void setDateOut(LocalDate dateOut) {
        this.dateOut = dateOut;
    }
}

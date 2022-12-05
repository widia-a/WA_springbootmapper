package com.bcafinance._01springbootrestapi.dto;
/*
Created by IntelliJ IDEA 2022.2.3 (Community Edition)
Build #IC-222.4345.14, built on October 5, 2022
@Author  a.k.a. Widia
Created on 05/12/2022 11:52
Last modified on 11:52
Version 1.0
*/


public class MessengerDTO {
    private Long id;

    private String fullName;

    private String address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

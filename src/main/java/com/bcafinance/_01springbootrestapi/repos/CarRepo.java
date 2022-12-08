package com.bcafinance._01springbootrestapi.repos;
/*
Created by IntelliJ IDEA 2022.2.3 (Community Edition)
Build #IC-222.4345.14, built on October 5, 2022
@Author widia a.k.a. Widia
Created on 06/12/2022 10:34
Last modified on 10:34
Version 1.0
*/


import com.bcafinance._01springbootrestapi.models.Cars;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepo extends JpaRepository<Cars, Long> {
    List<Cars> findByCarNameIsContaining(String name);

    List<Cars> findTop3ByCarNameIsContaining(String name);

    List<Cars> findBySoldIsGreaterThan(Integer sold);

    List<Cars> findByCarNameIsContainingOrSoldLike(String name, Integer sold);

    List<Cars> findByCarModelIsContainingAndStockIsGreaterThan(String model, Integer stock);

    List<Cars> findByPriceIsGreaterThan(Double price);

    List<Cars> findBySoldBetween(Integer sold, Integer solds);


    List<Cars> findDistinctFirstByCarName(String name);
}

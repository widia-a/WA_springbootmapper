package com.bcafinance._01springbootrestapi.services;
/*
Created by IntelliJ IDEA 2022.2.3 (Community Edition)
Build #IC-222.4345.14, built on October 5, 2022
@Author widia a.k.a. Widia
Created on 06/12/2022 10:34
Last modified on 10:34
Version 1.0
*/


import com.bcafinance._01springbootrestapi.models.Cars;
import com.bcafinance._01springbootrestapi.repos.CarRepo;
import com.bcafinance._01springbootrestapi.utils.CsvReader;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class CarService {

    @Getter
    private CarRepo carRepo;

    @Autowired
    public CarService(CarRepo carRepo){this.carRepo = carRepo;}

    @Transactional(rollbackFor = Exception.class)
    public List<Cars> saveBulkCar(MultipartFile multipartFile) throws Exception {

            try {
                List<Cars> lsCar = CsvReader.csvToCarData(multipartFile.getInputStream());
                return carRepo.saveAll(lsCar);
            }
            catch (Exception e)
            {
                throw new Exception(e.getMessage());
            }
    }

    public List<Cars> findAllCar() {
        return (List<Cars>) carRepo.findAll();
    }

    public List<Cars> findByCarName(String name) {
        return carRepo.findByCarNameIsContaining(name);
    }

    public List<Cars> findTop3ByCarName(String name) {
        return carRepo.findTop3ByCarNameIsContaining(name);
    }

    public List<Cars> findBySoldIsGreaterThan(Integer sold) {
        return carRepo.findBySoldIsGreaterThan(sold);
    }


    public List<Cars> findByCarNameIsContainingOrSoldLike(String name, Integer sold) {
        return carRepo.findByCarNameIsContainingOrSoldLike(name, sold);
    }

    public List<Cars> findByModelSort(String model, Integer stock) {
        return carRepo.findByCarModelIsContainingAndStockIsGreaterThan(model, stock);
    }

    public List<Cars> findByPriceIsGreaterThan(Double price) {
        return carRepo.findByPriceIsGreaterThan(price);
    }

    public List<Cars> findBySoldBetween(Integer sold, Integer solds) {
        return carRepo.findBySoldBetween(sold, solds);
    }

    public List<Cars> findDistinctByCarName(String name) {
        return carRepo.findDistinctFirstByCarName(name);
    }
}

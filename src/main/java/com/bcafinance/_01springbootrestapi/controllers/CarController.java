package com.bcafinance._01springbootrestapi.controllers;
/*
Created by IntelliJ IDEA 2022.2.3 (Community Edition)
Build #IC-222.4345.14, built on October 5, 2022
@Author widia a.k.a. Widia
Created on 06/12/2022 10:34
Last modified on 10:34
Version 1.0
*/

import com.bcafinance._01springbootrestapi.dto.CarDTO;
import com.bcafinance._01springbootrestapi.dto.MessengerDTO;
import com.bcafinance._01springbootrestapi.handler.ResourceNotFoundException;
import com.bcafinance._01springbootrestapi.handler.ResponseHandler;
import com.bcafinance._01springbootrestapi.models.Cars;
import com.bcafinance._01springbootrestapi.models.Messenger;
import com.bcafinance._01springbootrestapi.services.CarService;
import com.bcafinance._01springbootrestapi.services.MessengerService;
import com.bcafinance._01springbootrestapi.utils.ConstantMessage;
import com.bcafinance._01springbootrestapi.utils.CsvReader;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/")
public class CarController {

    @Getter
    private CarService carService;

    @Autowired
    private ModelMapper modelMapper;

    private List<Cars> lsCar = new ArrayList<Cars>();

    @Autowired
    public CarController(CarService carService) {

        this.carService = carService;
    }

    @PostMapping("/v1/cars/upl/bat")
    public ResponseEntity<Object>
    uploadCar(@Valid @RequestParam("uploadCar") MultipartFile multipartFile) throws Exception {
        try{
            if(CsvReader.isCsv(multipartFile))
            {
                carService.saveBulkCar(multipartFile);
            }
            else
            {
                throw new ResourceNotFoundException(ConstantMessage.ERROR_NOT_CSV_FILE+" -- "+multipartFile.getOriginalFilename());
            }
        }catch (Exception e)
        {
            throw new Exception(ConstantMessage.ERROR_UPLOAD_CSV+multipartFile.getOriginalFilename());
        }
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE,
                HttpStatus.CREATED,null,null,null);
    }

    @GetMapping("/v1/car/datas/all")
    public ResponseEntity<Object> findAllCar()throws Exception {

        List<Cars> lsCar = carService.findAllCar();

        if(lsCar.size()!=0)
        {
            List<CarDTO> lsCarDTO = modelMapper.map(lsCar, new TypeToken<List<CarDTO>>() {}.getType());

            return new ResponseHandler().
                    generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,lsCarDTO,null,null);
        }
        throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
    }

    @GetMapping("/v1/car/search/car/")
        public ResponseEntity<Object> findByCarName(
                @RequestParam String name
        ) throws Exception{
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,carService.findByCarName(name), null, null);
        }

    @GetMapping("/v1/car/search/names/sold/")
    public ResponseEntity<Object> findByCarNameOr(
            @RequestParam String name, Integer sold
    ) throws Exception{
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,carService.findByCarNameIsContainingOrSoldLike(name,sold), null, null);
    }

    @GetMapping("/v1/car/search/top/")
    public ResponseEntity<Object> findTop3ByCarName(
            @RequestParam String name
    ) throws Exception{
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,carService.findTop3ByCarName(name), null, null);
    }

    @GetMapping("/v1/car/search/sold/")
    public ResponseEntity<Object> findBySoldIsGreaterThan(
            @RequestParam Integer sold
    ) throws Exception{
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,carService.findBySoldIsGreaterThan(sold), null, null);
    }

    @GetMapping("/v1/car/model/")
    public ResponseEntity<Object> findByModelSort(
            @RequestParam String model, Integer stock
    ) throws Exception{
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,carService.findByModelSort(model, stock), null, null);
    }

    @GetMapping("/v1/car/price")
    public ResponseEntity<Object> findByPrice(
            @RequestParam Double price
    ) throws Exception{
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,carService.findByPriceIsGreaterThan(price), null, null);
    }

    @GetMapping("/v1/car/bet")
    public ResponseEntity<Object> findBySoldBetween(
            @RequestParam Integer sold, Integer solds
    ) throws Exception{
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,carService.findBySoldBetween(sold, solds), null, null);

    }

    @GetMapping("v1/car/dst")
    public ResponseEntity<Object> findDistinctByCarName(
            @RequestParam String name
    ) throws Exception{
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,carService.findDistinctByCarName(name), null,null);
    }

}

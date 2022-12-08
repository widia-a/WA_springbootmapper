package com.bcafinance._01springbootrestapi.controllers;
/*
Created by IntelliJ IDEA 2022.2.3 (Community Edition)
Build #IC-222.4345.14, built on October 5, 2022
@Author widia a.k.a. Widia
Created on 07/12/2022 11:05
Last modified on 11:05
Version 1.0
*/

import com.bcafinance._01springbootrestapi.dto.CarDTO;
import com.bcafinance._01springbootrestapi.handler.ResourceNotFoundException;
import com.bcafinance._01springbootrestapi.handler.ResponseHandler;
import com.bcafinance._01springbootrestapi.models.Account;
import com.bcafinance._01springbootrestapi.models.Cars;
import com.bcafinance._01springbootrestapi.services.AccountService;
import com.bcafinance._01springbootrestapi.services.MessengerService;
import com.bcafinance._01springbootrestapi.utils.ConstantMessage;
import com.bcafinance._01springbootrestapi.utils.CsvReader;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/")
public class AccountController {
    @Getter
    private AccountService accountService;

    @Autowired
    private ModelMapper modelMapper;

    private List<Account> lsAcc = new ArrayList<Account>();

    @Autowired
    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @PostMapping("/v1/account/upl/bat")
    public ResponseEntity<Object>
    uploadAccount(@Valid @RequestParam("uploadAcc") MultipartFile multipartFile) throws Exception {
        try{
            if(CsvReader.isCsv(multipartFile))
            {
                accountService.saveBulkAcc(multipartFile);
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

    @PutMapping("/v1/account/update")
    public ResponseEntity<Object> updateAccountByAccNumber(@RequestParam String accNumber1,
                                                              @RequestParam String accNumber2,
                                                              @RequestParam Double value)throws Exception{
        accountService.updateAccountByAccNumber(accNumber1, accNumber2, value);
        return new ResponseHandler().
                generateResponse(ConstantMessage.TRANSFER_SUCCESS,HttpStatus.OK,null,null,null);
    }

    @GetMapping("/v1/account/datas/all")
    public ResponseEntity<Object> findALl() throws Exception{
        List<Account> lsAcc = accountService.findAllAcc();

        if(lsAcc.size()!=0)
        {
            return new ResponseHandler().
                    generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,lsAcc,null,null);
        }
        throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
    }

}

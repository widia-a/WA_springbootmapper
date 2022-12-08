package com.bcafinance._01springbootrestapi.services;
/*
Created by IntelliJ IDEA 2022.2.3 (Community Edition)
Build #IC-222.4345.14, built on October 5, 2022
@Author widia a.k.a. Widia
Created on 07/12/2022 11:06
Last modified on 11:06
Version 1.0
*/

import com.bcafinance._01springbootrestapi.handler.ResourceNotFoundException;
import com.bcafinance._01springbootrestapi.models.Account;
import com.bcafinance._01springbootrestapi.models.Cars;
import com.bcafinance._01springbootrestapi.repos.AccountRepo;
import com.bcafinance._01springbootrestapi.utils.ConstantMessage;
import com.bcafinance._01springbootrestapi.utils.CsvReader;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AccountService {
    @Getter
    private AccountRepo accountRepo;

    @Autowired
    public AccountService(AccountRepo accountRepo){this.accountRepo = accountRepo;}

    @Transactional(rollbackFor = Exception.class)
    public List<Account> saveBulkAcc(MultipartFile multipartFile) throws Exception {
        try {
            List<Account> lsAccount = CsvReader.csvToAccData(multipartFile.getInputStream());
            return accountRepo.saveAll(lsAccount);
        }
        catch (Exception e)
        {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional(rollbackFor = {Exception.class, SQLException.class})
    public void updateAccountByAccNumber(String accNumber1, String accNumber2, Double value) throws Exception{

        Account Account1 = accountRepo.findByAccNumber(accNumber1).orElseThrow(()->
                new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND));

        Account Account2 = accountRepo.findByAccNumber(accNumber2).orElseThrow(()->
                new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND));

        Account1.setModifiedBy("1");
        Account2.setModifiedBy("1");

        Account1.setModifiedDate(new Date());
        Account2.setModifiedDate(new Date());

        if (Account1.getBalance() < value){
            throw new ResourceNotFoundException(ConstantMessage.TRANSFER_FAILED);
        } else {
            Account1.setBalance(Account1.getBalance()-value);
            Account2.setBalance(Account2.getBalance()+value);
        }
    }

    public void updateAccountByAccNumbers(String accNumber1, String accNumber2, Double value) throws Exception{
        Account Account1 = accountRepo.findByAccNumber(accNumber1).orElseThrow(()->
                new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND));

        Account Account2 = accountRepo.findByAccNumber(accNumber2).orElseThrow(()->
                new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND));

        Account1.setModifiedBy("1");
        Account2.setModifiedBy("1");

        Account1.setModifiedDate(new Date());
        Account2.setModifiedDate(new Date());

        if (Account1.getBalance() < value){
            throw new ResourceNotFoundException(ConstantMessage.TRANSFER_FAILED);
        } else {
            Account1.setBalance(Account1.getBalance()-value);
            Account2.setBalance(Account2.getBalance()+value);
        }
    }


    public List<Account> findAllAcc() {
        return (List<Account>) accountRepo.findAll();
    }

    @Transactional(rollbackFor = {Exception.class, SQLException.class})
    public void saveBanksQuery(Account account) {
        accountRepo.insertBanks(account.getAccNumber(), account.getBalance(), "1", new Date(),true);
    }

}

package com.bcafinance._01springbootrestapi.services;
/*
Created by IntelliJ IDEA 2022.2.3 (Community Edition)
Build #IC-222.4345.14, built on October 5, 2022
@Author widia a.k.a. Widia
Created on 05/12/2022 11:20
Last modified on 11:20
Version 1.0
*/


import com.bcafinance._01springbootrestapi.models.Messenger;
import com.bcafinance._01springbootrestapi.repos.MessengerRepo;
import com.bcafinance._01springbootrestapi.utils.CsvReader;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class MessengerService {

    @Getter
    private MessengerRepo messengerRepo;

    @Autowired
    public MessengerService(MessengerRepo messengerRepo){this.messengerRepo = messengerRepo;}

    @Transactional (rollbackFor = Exception.class)
    public List<Messenger> saveBulkMessenger(MultipartFile multipartFile) throws Exception {
        try {
            List<Messenger> lsMessenger = CsvReader.csvToMessengerData(multipartFile.getInputStream());
            return messengerRepo.saveAll(lsMessenger);
        }
        catch (Exception e)
        {
            throw new Exception(e.getMessage());
        }
    }

    public List<Messenger> findAllMessenger() {
        return (List<Messenger>)messengerRepo.findAll();
    }

    public Iterable<Messenger> pagingByMessengerName(String name, Pageable pageable) {
        return messengerRepo.findByFullNameIsContaining(name, pageable);
    }

}

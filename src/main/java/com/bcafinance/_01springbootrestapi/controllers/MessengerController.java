package com.bcafinance._01springbootrestapi.controllers;
/*
Created by IntelliJ IDEA 2022.2.3 (Community Edition)
Build #IC-222.4345.14, built on October 5, 2022
@Author widia a.k.a. Widia
Created on 05/12/2022 11:18
Last modified on 11:18
Version 1.0
*/


import com.bcafinance._01springbootrestapi.dto.CitizenDTO;
import com.bcafinance._01springbootrestapi.dto.MessengerDTO;
import com.bcafinance._01springbootrestapi.handler.ResourceNotFoundException;
import com.bcafinance._01springbootrestapi.handler.ResponseHandler;
import com.bcafinance._01springbootrestapi.models.Citizen;
import com.bcafinance._01springbootrestapi.models.Messenger;
import com.bcafinance._01springbootrestapi.services.CitizenService;
import com.bcafinance._01springbootrestapi.services.MessengerService;
import com.bcafinance._01springbootrestapi.utils.ConstantMessage;
import com.bcafinance._01springbootrestapi.utils.CsvReader;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
public class MessengerController {

    @Getter
    private MessengerService messengerService;
    @Autowired
    private ModelMapper modelMapper;

    private List<Messenger> lsMessenger = new ArrayList<Messenger>();

    @Autowired
    public MessengerController(MessengerService messengerService) {
        this.messengerService = messengerService;
    }

    @PostMapping("/v1/messenger/upl/bat")
    public ResponseEntity<Object>
    uploadMessenger(@Valid @RequestParam("CobaCSV") MultipartFile multipartFile) throws Exception {
        try{
            if(CsvReader.isCsv(multipartFile))
            {
                messengerService.saveBulkMessenger(multipartFile);
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

    @GetMapping("/v1/messenger/datas/all")
    public ResponseEntity<Object> findAllMessengerDTO()throws Exception {

        List<Messenger> lsMessenger = messengerService.findAllMessenger();

        if(lsMessenger.size()!=0)
        {
            List<MessengerDTO> lsMessengerDTO = modelMapper.map(lsMessenger, new TypeToken<List<MessengerDTO>>() {}.getType());

            return new ResponseHandler().
                    generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,lsMessengerDTO,null,null);
        }
        throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
    }


//    @GetMapping("/v1/messenger/datas/all/{sort}")
//    @GetMapping("/v1/messenger/datas/all/{size}/{page}")

    @GetMapping("/v1/messenger/search/{size}/{page}")
    public ResponseEntity<Object> pageFindByMessengerName(
            @RequestParam String name, @PathVariable("size") int size, @PathVariable("page") int page
    ) throws Exception{
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,messengerService.pagingByMessengerName(name, pageable), null, null);
    }

    @GetMapping("/v1/messenger/search/dto/{size}/{page}/{sort}")
    public ResponseEntity<Object> pageSortByName(@RequestParam String name,
                                                    @PathVariable("size") int size,
                                                    @PathVariable("page") int page,
                                                    @PathVariable("sort") String sortt)throws Exception {

        Pageable pageable;
        if(sortt.equalsIgnoreCase("desc"))
        {
            pageable = PageRequest.of(page,size, Sort.by("id").descending());
        }
        else
        {
            pageable = PageRequest.of(page,size, Sort.by("id"));//default asc
        }

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,messengerService.pagingByMessengerName(name,pageable),null,null);
    }

}

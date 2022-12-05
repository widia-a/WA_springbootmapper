package com.bcafinance._01springbootrestapi.controllers;

import com.bcafinance._01springbootrestapi.dto.CitizenDTO;
import com.bcafinance._01springbootrestapi.handler.ResourceNotFoundException;
import com.bcafinance._01springbootrestapi.handler.ResponseHandler;
import com.bcafinance._01springbootrestapi.models.Citizen;
import com.bcafinance._01springbootrestapi.services.CitizenService;
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
public class CitizenController {

    @Getter
    private CitizenService citizenService;
    @Autowired
    private ModelMapper modelMapper;

    private List<Citizen> lsCitizen = new ArrayList<Citizen>();

    @Autowired
    public CitizenController(CitizenService citizenService) {
        this.citizenService = citizenService;
    }

    @PostMapping("/v1/citizen/upl/bat/11")
    public ResponseEntity<Object>
    uploadCitizen(@Valid @RequestParam("demoFile") MultipartFile multipartFile) throws Exception {
        try{
            if(CsvReader.isCsv(multipartFile))
            {
                citizenService.saveBulkCitizen(multipartFile);
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

    @GetMapping("/v1/citizen/datas/all/dto/9")
    public ResponseEntity<Object> findAllCitizenDTO()throws Exception {

        List<Citizen> lsCitizen = citizenService.findAllCitizen();

        if(lsCitizen.size()!=0)
        {
            List<CitizenDTO> lsEmployeeDTO = modelMapper.map(lsCitizen, new TypeToken<List<CitizenDTO>>() {}.getType());

            return new ResponseHandler().
                    generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,lsEmployeeDTO,null,null);
        }
        throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
    }

    @GetMapping("/v1/citizen/search/dto/{size}/{page}")
    public ResponseEntity<Object> pageFindCitizenByNameDTO
            (@RequestParam String name,
             @PathVariable("size") int size,
             @PathVariable("page") int page )throws Exception {

        Pageable pageable = PageRequest.of(page,size);
        return new ResponseHandler().
                generateResponse(
                        ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,citizenService.pagingFindCitizenByName(name,pageable),null,null);
    }

    @GetMapping("/v1/citizen/search/dto/{size}/{page}/{sort}")
    public ResponseEntity<Object> pageSortByNameDTO(@RequestParam String name,
                                                           @PathVariable("size") int size,
                                                           @PathVariable("page") int page,
                                                    @PathVariable("sort") String sortz)throws Exception {

        Pageable pageable;
        if(sortz.equalsIgnoreCase("desc"))
        {
            pageable = PageRequest.of(page,size, Sort.by("id").descending());
        }
        else
        {
            pageable = PageRequest.of(page,size, Sort.by("id"));//default asc
        }

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,citizenService.pagingFindCitizenByName(name,pageable),null,null);
    }
}
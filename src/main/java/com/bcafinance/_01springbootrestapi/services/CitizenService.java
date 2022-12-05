package com.bcafinance._01springbootrestapi.services;

import com.bcafinance._01springbootrestapi.models.Citizen;
import com.bcafinance._01springbootrestapi.repos.CitizenRepo;
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
public class CitizenService {

    @Getter
    private CitizenRepo citizenRepo;

    @Autowired
    public CitizenService(CitizenRepo citizenRepo) {
        this.citizenRepo = citizenRepo;
    }

    @Transactional(rollbackFor = Exception.class)
    public List<Citizen> saveBulkCitizen(MultipartFile multipartFile)
            throws Exception
    {
        try{
            List<Citizen> lsCitizen = CsvReader.csvToCitizenData(multipartFile.getInputStream());
            return citizenRepo.saveAll(lsCitizen);
        }catch (Exception e)
        {
            throw new Exception(e.getMessage());
        }
    }

    public List<Citizen> findAllCitizen()
    {
        return (List<Citizen>)citizenRepo.findAll();
    }

    public Iterable<Citizen> pagingFindCitizenByName(String name, Pageable pageable)
    {
        return citizenRepo.findByFullNameIsContaining(name,pageable);
    }
}

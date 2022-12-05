package com.bcafinance._01springbootrestapi.utils;


import com.bcafinance._01springbootrestapi.models.Citizen;
import com.bcafinance._01springbootrestapi.models.Messenger;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    public static boolean isCsv(MultipartFile multipartFile)
    {
        if(!ConstantMessage.CONTENT_TYPE_CSV.equals(multipartFile.getContentType()))
        {
            return false;
        }
        return true;
    }

    public static List<Citizen> csvToCitizenData(InputStream inputStream) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        CSVParser csvParser = new CSVParser(bufferedReader,
                CSVFormat.DEFAULT.withFirstRecordAsHeader().
                        withIgnoreHeaderCase().
                        withTrim()
        );
        List<Citizen> lsCitizens = new ArrayList<Citizen>();
        try {

            Iterable<CSVRecord> iterRecords = csvParser.getRecords();

            for (CSVRecord record : iterRecords) {
                Citizen citizen = new Citizen();
                citizen.setFullName(record.get("FullName"));
                citizen.setAddress(record.get("Address"));
                citizen.setIdCardNumber(record.get("IDCardNumber"));
                citizen.setBirthDate(LocalDate.parse(record.get("BirthDate")));
                lsCitizens.add(citizen);
            }

        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        } finally {

            if (!csvParser.isClosed()) {
                csvParser.close();
            }
            return lsCitizens;
        }
    }

    public static List<Messenger> csvToMessengerData(InputStream inputStream) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        CSVParser csvParser = new CSVParser(bufferedReader,
                CSVFormat.DEFAULT.withFirstRecordAsHeader().
                        withIgnoreHeaderCase().
                        withTrim()
        );
        List<Messenger> lsMessenger = new ArrayList<Messenger>();
        try {

            Iterable<CSVRecord> iterRecords = csvParser.getRecords();

            for (CSVRecord record : iterRecords) {
                Messenger messenger = new Messenger();
                messenger.setFullName(record.get("FullName"));
                messenger.setAddress(record.get("Address"));
                messenger.setJoinDate(LocalDate.parse(record.get("JoinDate")));
                lsMessenger.add(messenger);
            }

        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        } finally {

            if (!csvParser.isClosed()) {
                csvParser.close();
            }
            return lsMessenger;
        }
    }
}
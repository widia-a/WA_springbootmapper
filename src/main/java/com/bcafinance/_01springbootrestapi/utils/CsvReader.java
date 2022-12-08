package com.bcafinance._01springbootrestapi.utils;


import com.bcafinance._01springbootrestapi.models.Account;
import com.bcafinance._01springbootrestapi.models.Cars;
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

    public static List<Cars> csvToCarData(InputStream inputStream) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        CSVParser csvParser = new CSVParser(bufferedReader,
                CSVFormat.DEFAULT.withFirstRecordAsHeader().
                        withIgnoreHeaderCase().
                        withTrim()
        );
        List<Cars> lsCar = new ArrayList<Cars>();
        try {

            Iterable<CSVRecord> iterRecords = csvParser.getRecords();

            for (CSVRecord record : iterRecords) {
                Cars cars = new Cars();
                cars.setCarName(record.get("CarName"));
                cars.setCarModel(record.get("CarModel"));
                cars.setSold(Integer.parseInt(record.get("Sold")));
                cars.setStock(Integer.parseInt(record.get("Stock")));
                cars.setPrice(Double.parseDouble(record.get("Price")));
                cars.setDiscount(Double.parseDouble(record.get("Discount")));
                cars.setDateIn(LocalDate.parse(record.get("DateIn")));
                cars.setDateOut(LocalDate.parse(record.get("DateOut")));
                lsCar.add(cars);
            }

        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        } finally {

            if (!csvParser.isClosed()) {
                csvParser.close();
            }
            return lsCar;
        }
    }

    public static List<Account> csvToAccData(InputStream inputStream) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        CSVParser csvParser = new CSVParser(bufferedReader,
                CSVFormat.DEFAULT.withFirstRecordAsHeader().
                        withIgnoreHeaderCase().
                        withTrim()
        );
        List<Account> lsAcc = new ArrayList<Account>();
        try {

            Iterable<CSVRecord> iterRecords = csvParser.getRecords();

            for (CSVRecord record : iterRecords) {
                Account account = new Account();
                account.setAccNumber(record.get("AccountNumber"));
                account.setBalance(Double.parseDouble(record.get("Balance")));
                lsAcc.add(account);
            }

        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        } finally {

            if (!csvParser.isClosed()) {
                csvParser.close();
            }
            return lsAcc;
        }
    }
}
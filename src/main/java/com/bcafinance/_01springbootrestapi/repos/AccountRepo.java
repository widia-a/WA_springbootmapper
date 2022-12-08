package com.bcafinance._01springbootrestapi.repos;
/*
Created by IntelliJ IDEA 2022.2.3 (Community Edition)
Build #IC-222.4345.14, built on October 5, 2022
@Author widia a.k.a. Widia
Created on 07/12/2022 11:09
Last modified on 11:09
Version 1.0
*/


import com.bcafinance._01springbootrestapi.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.Optional;

public interface AccountRepo extends JpaRepository<Account, Long> {
    Optional<Account> findByAccNumber(String accNumber);

//    @Modifying
//    @Query(
//            value =
//                    "INSERT INTO MstBank (AccountID, AccountNumber, Balance, CreatedBy, CreatedDate, IsActive, ModifiedBy, ModifiedDate)" +
//                            " VALUES (:AccountID, :AccountNumber, :Balance, :CreatedBy, :CreatedDate, :IsActive, :ModifiedBy, :ModifiedDate)",
//            nativeQuery = true)
//    void insertBanks(@Param("AccountID") String accountId, @Param("AccountNumber") String accountNumber,
//                        @Param("Balance") Double balance,
//                        @Param("CreatedBy") String createdBy, @Param("CreatedDate") Date createdDate,
//                        @Param("IsActive") boolean isActive,
//                        @Param("ModifiedBy") String modifiedBy, @Param("ModifiedDate") Date modifiedDate
//                        );
}


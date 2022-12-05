package com.bcafinance._01springbootrestapi.repos;
/*
Created by IntelliJ IDEA 2022.2.3 (Community Edition)
Build #IC-222.4345.14, built on October 5, 2022
@Author widia a.k.a. Widia
Created on 05/12/2022 11:20
Last modified on 11:20
Version 1.0
*/


import com.bcafinance._01springbootrestapi.models.Messenger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessengerRepo extends JpaRepository<Messenger, Long> {

    Page<Messenger> findByFullNameIsContaining(String name, Pageable pageable);
}

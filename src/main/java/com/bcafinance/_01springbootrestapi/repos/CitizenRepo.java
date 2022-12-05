package com.bcafinance._01springbootrestapi.repos;

import com.bcafinance._01springbootrestapi.models.Citizen;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitizenRepo extends JpaRepository<Citizen,Long> {
    Page<Citizen> findByFullNameIsContaining(String name, Pageable pageable);
}
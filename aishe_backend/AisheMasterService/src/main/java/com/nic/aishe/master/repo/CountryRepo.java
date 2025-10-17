package com.nic.aishe.master.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nic.aishe.master.entity.Country;

import java.util.List;

@Repository
public interface CountryRepo extends JpaRepository<Country, Integer> {

    @Query(value = "select * from ref_country where country_id <> 1  order by country_name",nativeQuery = true)
    List<Country> findAllCountriesExceptOne();

    @Query(value = "select * from ref_country where  is_active is true order by country_name ",nativeQuery = true)
    List<Country> findAll();
}
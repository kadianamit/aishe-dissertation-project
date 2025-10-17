package com.nic.aishe.master.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nic.aishe.master.entity.Management;

import java.util.List;

@Repository
public interface ManagmentRepo extends JpaRepository<Management, Integer> {

    @Query(value = "SELECT * FROM ref_institution_ownership  WHERE is_valid_for_university is true order by id ",  nativeQuery = true)
    List<Management> applicableForUniversity();
    @Query(value = "SELECT * FROM ref_institution_ownership  WHERE is_valid_for_college is true order by id ",  nativeQuery = true)
    List<Management> applicableForCollege();
    @Query(value = "SELECT * FROM ref_institution_ownership  WHERE is_valid_for_standalone is true order by id ",  nativeQuery = true)
    List<Management> applicableForStandalone();
}

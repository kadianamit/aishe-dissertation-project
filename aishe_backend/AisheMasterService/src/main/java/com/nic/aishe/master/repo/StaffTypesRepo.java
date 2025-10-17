package com.nic.aishe.master.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nic.aishe.master.entity.StaffTypes;

import java.util.List;
import java.util.Optional;

@Repository
public interface StaffTypesRepo extends JpaRepository<StaffTypes, Integer> {

    @Query(value = "select * from ref_non_teaching_staff_type where is_active is true order by id ", nativeQuery = true)
    List<StaffTypes> findAll();

    @Query(value = "select * from ref_non_teaching_staff_type where is_active is true and id=:id order by id", nativeQuery = true)
    Optional<StaffTypes> findById(@Param("id") Integer id);
}
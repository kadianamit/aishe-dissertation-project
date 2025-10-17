package com.nic.aishe.master.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nic.aishe.master.entity.Qualification;

import java.util.List;

@Repository
public interface QualificationRepo extends JpaRepository<Qualification, Integer> {
    @Query(value = "select * from ref_teacher_highest_qualification where is_active is true ",nativeQuery = true)
    List<Qualification> findAll();
}
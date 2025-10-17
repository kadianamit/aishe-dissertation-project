package com.nic.aishe.master.repo;

import com.nic.aishe.master.entity.AppointmentNature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nic.aishe.master.entity.TeacherDesignation;

import java.util.List;

@Repository
public interface TeacherDesignationRepo extends JpaRepository<TeacherDesignation, Integer> {

    @Query(value = "SELECT * FROM ref_teaching_staff_designation  WHERE id=:id ",  nativeQuery = true)
    TeacherDesignation findByDesignationId(@Param("id") String id);

}
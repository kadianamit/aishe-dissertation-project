package com.nic.aishe.master.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nic.aishe.master.entity.AppointmentNature;

import java.util.List;

@Repository
public interface AppointmentNatureRepo extends JpaRepository<AppointmentNature, Integer> {


    @Query(value = "select * from ref_nature_of_appointment where is_active is true order by id ", nativeQuery = true)
    List<AppointmentNature> findAll();

    @Query(value = "select * from ref_nature_of_appointment where is_active is true and id=:id order by id ", nativeQuery = true)
    AppointmentNature getById(@Param("id") Integer id);
}
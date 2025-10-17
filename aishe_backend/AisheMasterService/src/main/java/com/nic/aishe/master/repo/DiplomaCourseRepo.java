package com.nic.aishe.master.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nic.aishe.master.entity.DiplomaType;
@Repository
public interface DiplomaCourseRepo extends JpaRepository<DiplomaType, Integer> {

}
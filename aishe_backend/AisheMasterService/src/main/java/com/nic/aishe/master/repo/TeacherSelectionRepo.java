package com.nic.aishe.master.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nic.aishe.master.entity.TeacherSelectionMode;
@Repository
public interface TeacherSelectionRepo extends JpaRepository<TeacherSelectionMode, String> {

}
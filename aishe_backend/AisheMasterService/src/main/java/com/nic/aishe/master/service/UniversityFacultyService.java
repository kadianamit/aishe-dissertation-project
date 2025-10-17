package com.nic.aishe.master.service;

import com.nic.aishe.master.entity.Faculty;
import com.nic.aishe.master.entity.University;
import com.nic.aishe.master.entity.UniversityFacultyInfo;
import com.nic.aishe.master.entity.UniversityInfoFaculty;

import java.util.List;

public interface UniversityFacultyService {

    List<Faculty> findById(Integer id);

    Integer maxId();

 }

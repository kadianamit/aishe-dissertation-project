package com.nic.aishe.master.service;

import com.nic.aishe.master.entity.Faculty;

import java.util.List;

public interface CollegeFacultyService {

    List<Faculty> findById(Integer id);

    Integer maxId();

   }

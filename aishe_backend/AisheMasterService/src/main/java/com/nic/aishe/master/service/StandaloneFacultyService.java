package com.nic.aishe.master.service;

import com.nic.aishe.master.entity.Faculty;
import com.nic.aishe.master.entity.StandaloneFacultyInfo;
import com.nic.aishe.master.entity.StandaloneInfo;
import com.nic.aishe.master.entity.StandaloneInstitutionFacultyInfo;

import java.util.List;

public interface StandaloneFacultyService {

    List<Faculty> findById(Integer id);

    Integer maxId();


}

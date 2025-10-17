package com.nic.aishe.master.impl;

import com.nic.aishe.master.entity.Faculty;
import com.nic.aishe.master.entity.StandaloneFacultyInfo;
import com.nic.aishe.master.entity.StandaloneInfo;
import com.nic.aishe.master.entity.StandaloneInstitutionFacultyInfo;
import com.nic.aishe.master.repo.FacultyInfoRepo;
import com.nic.aishe.master.repo.StandaloneFacultyRapo;
import com.nic.aishe.master.repo.StandaloneInFoRapo;
import com.nic.aishe.master.repo.StandaloneInstitutionFacultyRapo;
import com.nic.aishe.master.service.StandaloneFacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StandaloneInstitutionFacultyServiceImpl implements StandaloneFacultyService {
    @Autowired
    private FacultyInfoRepo facultyRepo;


    @Override
    public List<Faculty> findById(Integer id) {
        return facultyRepo.findAllById(id);
    }

    @Override
    public Integer maxId() {
        return facultyRepo.maxId();
    }

}

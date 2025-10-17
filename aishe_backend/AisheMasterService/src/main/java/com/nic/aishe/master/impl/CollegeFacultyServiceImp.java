package com.nic.aishe.master.impl;

import com.nic.aishe.master.entity.Faculty;
import com.nic.aishe.master.repo.FacultyInfoRepo;
import com.nic.aishe.master.service.CollegeFacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollegeFacultyServiceImp implements CollegeFacultyService {

    @Autowired(required=true)
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

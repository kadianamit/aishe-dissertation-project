package com.nic.aishe.master.controller;

import com.nic.aishe.master.entity.AppointmentNature;
import com.nic.aishe.master.entity.TeacherDesignation;
import com.nic.aishe.master.repo.AppointmentNatureRepo;
import com.nic.aishe.master.repo.TeacherDesignationRepo;
import com.nic.aishe.master.security.UserInfo;
import com.nic.aishe.master.security.WithUser;
import com.nic.aishe.master.util.CommonObjectOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

//@CrossOrigin("*")
@RequestMapping("/api/appointment-nature")
@RestController
public class AppointmentNatureController {

    @Autowired(required = false)
    private AppointmentNatureRepo appointmentNatureRepo;

    @Autowired(required = false)
    private TeacherDesignationRepo designationRepo;


    @GetMapping
    public List<AppointmentNature> getAllType(@WithUser UserInfo userInfo, @RequestParam(required = false) String designationId) {
         CommonObjectOperation.usernameValidate(userInfo);
        if (null != designationId) {
            CommonObjectOperation.sevenLength(designationId);
            TeacherDesignation designation = designationRepo.findByDesignationId(designationId);
            if (null != designation) {
                List<Integer> list = designation.getNatureOfAppointment();
                if (CommonObjectOperation.listValidate(list)) {
                    List<AppointmentNature> natures = new ArrayList<>();
                    for (Integer id : list) {
                        AppointmentNature nature = appointmentNatureRepo.getById(id);
                        if (null != nature)
                            natures.add(nature);
                    }
                    return natures;
                }
                return appointmentNatureRepo.findAll();
            }

        }
        return appointmentNatureRepo.findAll();
    }
}

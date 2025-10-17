package com.nic.aishe.master.controller;

import com.nic.aishe.master.entity.InstituteManagement;
import com.nic.aishe.master.entity.InstituteManagementRequestStatus;
import com.nic.aishe.master.impl.InstituteManagementService;
import com.nic.aishe.master.repo.InstituteManagementRequestStatusRepo;
import com.nic.aishe.master.security.UserInfo;
import com.nic.aishe.master.security.WithUser;
import com.nic.aishe.master.util.CommonObjectOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

//@CrossOrigin("*")
@RequestMapping("/api")
@RestController
public class InstituteManagementController {


    @Autowired(required = false)
    private InstituteManagementService instituteManagementService;


    @Autowired(required = false)
    private  InstituteManagementRequestStatusRepo requestStatusRepo;
/*

    @PostMapping("/institute-management")
    public ResponseEntity<?> addManagement(@RequestBody InstituteManagement Management) {
        InstituteManagement Managements = instituteManagementService.addManagement(Management);
        return ResponseEntity.ok(Managements);
    }
*/

    @GetMapping("/institute-management")
    public ResponseEntity<?> getAllManagement(@WithUser UserInfo userInfo) {
        CommonObjectOperation.usernameValidate(userInfo);
        List<InstituteManagement> Managements = instituteManagementService.getAllManagement();
        if (Managements != null && Managements.size() > 0) {
            return ResponseEntity.ok(Managements);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/institute-management/{id}")
    public ResponseEntity<?> getOneManagement(@PathVariable Integer id, @WithUser UserInfo userInfo) {
        CommonObjectOperation.usernameValidate(userInfo);
        InstituteManagement Management = instituteManagementService.getOneManagement(id);
        if (Management != null) {
            return ResponseEntity.ok(Management);
        }
        return ResponseEntity.notFound().build();

    }


    @GetMapping("/institute-management-request-status")
    public ResponseEntity<?> getAllManagementStatus(@WithUser UserInfo userInfo) {
        CommonObjectOperation.usernameValidate(userInfo);
        List<InstituteManagementRequestStatus> Managements = requestStatusRepo.findAll();
        if (Managements != null && Managements.size() > 0) {
            return ResponseEntity.ok(Managements);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/institute-management-request-status/{id}")
    public ResponseEntity<?> getOneManagementStatus(@PathVariable Integer id, @WithUser UserInfo userInfo) {
        CommonObjectOperation.usernameValidate(userInfo);
        Optional<InstituteManagementRequestStatus> Management = requestStatusRepo.findById(id);
        if (Management.isPresent()) {
            return ResponseEntity.ok(Management.get());
        }
        return ResponseEntity.notFound().build();

    }


/*
    @DeleteMapping("/institute-management/{id}")
    public ResponseEntity<?> deleteOneManagement(@PathVariable Integer id) {
        Boolean isdel = instituteManagementService.deleteOneManagement(id);
        if (isdel) {
            return ResponseEntity.ok("Success deleted with id=" + id);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/institute-management")
    public ResponseEntity<?> deleteAllManagement() {
        instituteManagementService.deleteAllManagement();

        return ResponseEntity.ok("All deleted Success !");
    }
*/


}

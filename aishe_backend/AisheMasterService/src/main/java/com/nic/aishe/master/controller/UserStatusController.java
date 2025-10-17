package com.nic.aishe.master.controller;

import com.nic.aishe.master.entity.UserStatus;
import com.nic.aishe.master.repo.UserStatusRepo;
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

@RequestMapping("/api")
@RestController
//@CrossOrigin("*")
public class UserStatusController {

    @Autowired(required = false)
    private UserStatusRepo roleRepo;

    @GetMapping("/user-status")
    public List<UserStatus> getAllUserStatus(@WithUser UserInfo userInfo) {
         CommonObjectOperation.usernameValidate(userInfo);
        return roleRepo.findAll();
    }


    @GetMapping("/user-status/{id}")
    public ResponseEntity<?> getUserstatusById(@PathVariable Integer id , @WithUser UserInfo userInfo) {
         CommonObjectOperation.usernameValidate(userInfo);
        Optional<UserStatus> userRole = roleRepo.findById(id);
        if (userRole.isPresent()) {
            return ResponseEntity.ok(userRole.get());
        }
        return ResponseEntity.notFound().build();
    }
}


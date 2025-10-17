package com.nic.aishe.master.controller;

import com.nic.aishe.master.entity.RefInstitutionManagementRole;
import com.nic.aishe.master.entity.UserRole;
import com.nic.aishe.master.repo.RefInstitutionManagementRoleRapo;
import com.nic.aishe.master.repo.UserRoleRepo;
import com.nic.aishe.master.security.UserInfo;
import com.nic.aishe.master.security.WithUser;
import com.nic.aishe.master.util.CommonObjectOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api")
@RestController
//@CrossOrigin("*")
public class UserRoleController {

    @Autowired(required = false)
    private UserRoleRepo roleRepo;

    @Autowired(required = false)
    private RefInstitutionManagementRoleRapo managementRoleRapo;


    @GetMapping("/user-role")
    public ResponseEntity<?> getUserRoleById(@RequestParam(required = false) Integer roleId, @RequestParam(required = false) Integer levelId, @WithUser UserInfo userInfo) {
         CommonObjectOperation.usernameValidate(userInfo);
        Optional<List<UserRole>> userRole = null;
        if (null != levelId && null != roleId)
            userRole = roleRepo.getUserRoleByIdandLevel(roleId, levelId);
        else if (null == levelId && null != roleId) {
            userRole = roleRepo.getUserRoleById(roleId);
        } else if (null != levelId && null == roleId) {
            userRole = roleRepo.getUserRoleByLevel(levelId);
        } else {
            userRole = Optional.of(roleRepo.findAll());
        }
        if (userRole.isPresent()) {
            return ResponseEntity.ok(userRole.get());
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/institution-by-role")
    public ResponseEntity<?> getInstitutionManagementRole(@RequestParam(required = false) Integer roleId, @WithUser UserInfo userInfo) {
         CommonObjectOperation.usernameValidate(userInfo);
        List<RefInstitutionManagementRole> userRole = null;
        if (null == roleId) {
            userRole = managementRoleRapo.findAll();
        } else {
            Optional<RefInstitutionManagementRole> userRoles = managementRoleRapo.findById(roleId);
            if (userRoles.isPresent()) {
                RefInstitutionManagementRole role = userRoles.get();
                userRole = new ArrayList<>();
                userRole.add(role);
            }
        }
        return ResponseEntity.ok(userRole);
    }
}


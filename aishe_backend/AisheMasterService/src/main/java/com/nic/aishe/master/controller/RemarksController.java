package com.nic.aishe.master.controller;

import com.nic.aishe.master.entity.Remarks;
import com.nic.aishe.master.repo.RemarksRepo;
import com.nic.aishe.master.security.UserInfo;
import com.nic.aishe.master.security.WithUser;
import com.nic.aishe.master.util.CommonObjectOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@CrossOrigin("*")
@RequestMapping("/api/remarks")
@RestController
//@CrossOrigin("*")
public class RemarksController {

    @Autowired(required = false)
    private RemarksRepo RemarksRepo;

    @GetMapping
    public ResponseEntity<?> getAllRemarks(@WithUser UserInfo userInfo) {
         CommonObjectOperation.usernameValidate(userInfo);
        List<Remarks> remarks = RemarksRepo.findAll();
        if (remarks != null && remarks.size() > 0) {
            return ResponseEntity.ok(remarks);
        }
        return ResponseEntity.notFound().build();
    }


}

package com.nic.aishe.master.controller;

import com.nic.aishe.master.entity.InstituteBasicInfo;
import com.nic.aishe.master.impl.InstituteService;
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
//@CrossOrigin("*")
 @RequestMapping("/api")
@RestController
public class InstituteController {

    @Autowired(required = false)
    private InstituteService InstituteService;

/*
    @PostMapping("/institute")
    public ResponseEntity<?> addRemark(@RequestBody InstituteBasicInfo institute) {
        InstituteBasicInfo inst = InstituteService.addInstitute(institute);
        return ResponseEntity.ok(inst);
    }
*/

    @GetMapping("/institute")
    public ResponseEntity<?> getAllInstitute(@WithUser UserInfo userInfo) {
        CommonObjectOperation.usernameValidate(userInfo);
        List<InstituteBasicInfo> institutes = InstituteService.getAllInstitute();
        if (institutes != null && institutes.size() > 0) {
            return ResponseEntity.ok(institutes);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/institute/{id}")
    public ResponseEntity<?> getOneInstitute(@PathVariable Integer id, @WithUser UserInfo userInfo) {
        CommonObjectOperation.usernameValidate(userInfo);
        InstituteBasicInfo inst = InstituteService.getOneInstitute(id);
        if (inst != null) {
            return ResponseEntity.ok(inst);
        }
        return ResponseEntity.notFound().build();

    }


}

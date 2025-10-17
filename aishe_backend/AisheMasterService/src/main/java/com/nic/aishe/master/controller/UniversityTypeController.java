package com.nic.aishe.master.controller;

import java.util.List;

import com.nic.aishe.master.security.UserInfo;
import com.nic.aishe.master.security.WithUser;
import com.nic.aishe.master.util.CommonObjectOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nic.aishe.master.entity.UniversityType;
import com.nic.aishe.master.impl.UniversityTypeService;

//@CrossOrigin("*")
@RequestMapping("/api")
@RestController
//@CrossOrigin("*")
public class UniversityTypeController {


    @Autowired(required = false)
    private UniversityTypeService universityTypeService;

   /* @PostMapping("/university-type")
    public ResponseEntity<?> addType(@RequestBody UniversityType University) {
        UniversityType uni = universityTypeService.addUniversityType(University);
        return ResponseEntity.ok(uni);
    }*/

    @GetMapping("/university-type")
    public ResponseEntity<?> getAllUniversityType() {
        List<UniversityType> Universitys = universityTypeService.getAllUniversityType();
        if (Universitys != null && Universitys.size() > 0) {
            return ResponseEntity.ok(Universitys);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/university-types")
    public ResponseEntity<?> getAllUniversityTypes() {
        List<UniversityType> Universitys = universityTypeService.getAllUniversityType();
        if (Universitys != null && Universitys.size() > 0) {
            return ResponseEntity.ok(Universitys);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/university-type-private")
    public ResponseEntity<?> getAllUniversityType(@WithUser UserInfo userInfo) {
        CommonObjectOperation.usernameValidate(userInfo);
        List<UniversityType> Universitys = universityTypeService.getAllUniversityType();
        if (Universitys != null && Universitys.size() > 0) {
            return ResponseEntity.ok(Universitys);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/university-types-private")
    public ResponseEntity<?> getAllUniversityTypes(@WithUser UserInfo userInfo) {
        CommonObjectOperation.usernameValidate(userInfo);
        List<UniversityType> Universitys = universityTypeService.getAllUniversityType();
        if (Universitys != null && Universitys.size() > 0) {
            return ResponseEntity.ok(Universitys);
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/university-type/{id}")
    public ResponseEntity<?> getOneUniversityType(@PathVariable Integer id, @WithUser UserInfo userInfo) {
         CommonObjectOperation.usernameValidate(userInfo);
        UniversityType University = universityTypeService.getOneUniversityType(id);
        if (University != null) {
            return ResponseEntity.ok(University);
        }
        return ResponseEntity.notFound().build();

    }


/*
    @DeleteMapping("/university-type/{id}")
    public ResponseEntity<?> deleteOneUniversityType(@PathVariable Integer id) {
        Boolean isdel = universityTypeService.deleteOneUniversityType(id);
        if (isdel) {
            return ResponseEntity.ok("Success deleted with id=" + id);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/university-type")
    public ResponseEntity<?> deleteAllUniversityType() {
        universityTypeService.deleteAllUniversityType();

        return ResponseEntity.ok("All deleted Success !");
    }
*/


}

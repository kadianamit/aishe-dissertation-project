package com.nic.aishe.master.controller;

import com.nic.aishe.master.entity.InstituteSpeciality;
import com.nic.aishe.master.impl.InstituteSpecialityService;
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
public class InstituteSpecialityController {


    @Autowired(required = false)
    private InstituteSpecialityService instituteSpecialityService;
/*

    @PostMapping("/institute-speciality")
    public ResponseEntity<?> addSpeciality(@RequestBody InstituteSpeciality Speciality) {
        InstituteSpeciality Specialitys = instituteSpecialityService.addSpeciality(Speciality);
        return ResponseEntity.ok(Specialitys);
    }
*/

    @GetMapping("/institute-speciality")
    public ResponseEntity<?> getAllSpeciality(@WithUser UserInfo userInfo) {
        CommonObjectOperation.usernameValidate(userInfo);
        List<InstituteSpeciality> Specialitys = instituteSpecialityService.getAllSpeciality();
        if (Specialitys != null && Specialitys.size() > 0) {
            return ResponseEntity.ok(Specialitys);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/institute-speciality/{id}")
    public ResponseEntity<?> getOneSpeciality(@PathVariable Integer id, @WithUser UserInfo userInfo) {
        CommonObjectOperation.usernameValidate(userInfo);
        InstituteSpeciality Speciality = instituteSpecialityService.getOneSpeciality(id);
        if (Speciality != null) {
            return ResponseEntity.ok(Speciality);
        }
        return ResponseEntity.notFound().build();

    }

 /*   @DeleteMapping("/institute-speciality/{id}")
    public ResponseEntity<?> deleteOneSpeciality(@PathVariable Integer id) {
        Boolean isdel = instituteSpecialityService.deleteOneSpeciality(id);
        if (isdel) {
            return ResponseEntity.ok("Success deleted with id=" + id);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/institute-speciality")
    public ResponseEntity<?> deleteAllSpeciality() {
        instituteSpecialityService.deleteAllSpeciality();

        return ResponseEntity.ok("All deleted Success !");
    }

*/
}

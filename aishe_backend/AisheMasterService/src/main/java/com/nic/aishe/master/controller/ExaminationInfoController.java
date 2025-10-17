package com.nic.aishe.master.controller;

import com.nic.aishe.master.entity.ExaminationInfo;
import com.nic.aishe.master.repo.ExaminationInfoRepo;
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
public class ExaminationInfoController {


    @Autowired(required = false)
    private ExaminationInfoRepo examinationInfoRepo;

  /*  @PostMapping("/examination-info")
    public ExaminationInfo addExaminationInfo(@RequestBody ExaminationInfo ExaminationInfo) {

        return examinationInfoRepo.save(ExaminationInfo);
    }

    @PutMapping("/examination-info/{id}")
    public ResponseEntity<?> updateExaminationInfo(@PathVariable Integer id, @RequestBody ExaminationInfo ExaminationInfo) {

        ExaminationInfo prog = examinationInfoRepo.findById(id).get();
        if (prog != null) {
            ExaminationInfo.setId(prog.getId());
            examinationInfoRepo.save(ExaminationInfo);
            return ResponseEntity.ok(ExaminationInfo);
        }
        return ResponseEntity.notFound().build();
    }
*/

    @GetMapping("/examination-info")
    public ResponseEntity<?> getAllExaminationInfo(@WithUser UserInfo userInfo) {
        CommonObjectOperation.usernameValidate(userInfo);
        List<ExaminationInfo> ExaminationInfos = examinationInfoRepo.findAll();
        if (ExaminationInfos != null && ExaminationInfos.size() > 0) {
            return ResponseEntity.ok(ExaminationInfos);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/examination-info/{id}")
    public ResponseEntity<?> getOneExaminationInfo(@PathVariable Integer id, @WithUser UserInfo userInfo) {
        CommonObjectOperation.usernameValidate(userInfo);
        ExaminationInfo ExaminationInfo = examinationInfoRepo.findById(id).get();
        if (ExaminationInfo != null) {
            return ResponseEntity.ok(ExaminationInfo);
        }
        return ResponseEntity.notFound().build();

    }

/*

    @DeleteMapping("/examination-info/{id}")
    public ResponseEntity<?> deleteOneExaminationInfo(@PathVariable Integer id) {
        ExaminationInfo ExaminationInfo = examinationInfoRepo.findById(id).get();
        if (ExaminationInfo != null) {
            examinationInfoRepo.deleteById(id);
            return ResponseEntity.ok("Success deleted with id=" + id);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/examination-info")
    public ResponseEntity<?> deleteAllExaminationInfo() {
        examinationInfoRepo.deleteAll();

        return ResponseEntity.ok("All deleted Success !");
    }
*/


}

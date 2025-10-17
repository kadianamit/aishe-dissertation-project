package com.nic.aishe.master.controller;

import com.nic.aishe.master.entity.StatutoryBody;
import com.nic.aishe.master.impl.StatutoryBodyService;
import com.nic.aishe.master.security.UserInfo;
import com.nic.aishe.master.security.WithUser;
import com.nic.aishe.master.util.CommonObjectOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api")
@RestController

public class StatutoryBodyController {


    @Autowired(required = false)
    private StatutoryBodyService StatutoryBodyService;

    /*@PostMapping("/statutory-body")
    public ResponseEntity<?> addStatutoryBody(@RequestBody StatutoryBody StatutoryBody) {
        StatutoryBody StatutoryBodys = StatutoryBodyService.addStatutoryBody(StatutoryBody);
        return ResponseEntity.ok(StatutoryBodys);
    }*/

    @GetMapping("/statutory-body")
    public ResponseEntity<?> getAllStatutoryBody(@RequestParam(required=false)String stateCode/*,@RequestParam(required=false)Integer standaloneBodyTypeId*/,@WithUser UserInfo userInfo) {
         CommonObjectOperation.usernameValidate(userInfo);
         List<StatutoryBody> StatutoryBodys =new ArrayList<>();
        if(/*standaloneBodyTypeId==null &&*/ stateCode!=null) {
        	StatutoryBodys = StatutoryBodyService.getAllStatutoryBodyForState(stateCode);
        }/*else if(standaloneBodyTypeId!=null && stateCode!=null) {
        	StatutoryBodys = StatutoryBodyService.getAllStatutoryBodyForStateAndStandaloneBodyType(stateCode,standaloneBodyTypeId);
        } */else {
        	StatutoryBodys = StatutoryBodyService.getAllStatutoryBody();
        }
         
        if (StatutoryBodys != null && StatutoryBodys.size() > 0) {
            return ResponseEntity.ok(StatutoryBodys);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/statutory-body-public")
    public ResponseEntity<?> getAllStatutoryBody(@RequestParam(required=false)String stateCode) {
        List<StatutoryBody> StatutoryBodys =new ArrayList<>();
        if(null !=stateCode) {
            StatutoryBodys = StatutoryBodyService.getAllStatutoryBodyForState(stateCode);
        }else {
            StatutoryBodys = StatutoryBodyService.getAllStatutoryBody();
        }

        if (StatutoryBodys != null && StatutoryBodys.size() > 0) {
            return ResponseEntity.ok(StatutoryBodys);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/statutory-body/{id}")
    public ResponseEntity<?> getOneStatutoryBody(@PathVariable Integer id, @WithUser UserInfo userInfo) {
         CommonObjectOperation.usernameValidate(userInfo);
        StatutoryBody StatutoryBody = StatutoryBodyService.getOneStatutoryBody(id);
        if (StatutoryBody != null) {
            return ResponseEntity.ok(StatutoryBody);
        }
        return ResponseEntity.notFound().build();

    }


    /*@DeleteMapping("/statutory-body/{id}")
    public ResponseEntity<?> deleteOneStatutoryBody(@PathVariable Integer id) {
        Boolean isdel = StatutoryBodyService.deleteOneStatutoryBody(id);
        if (isdel) {
            return ResponseEntity.ok("Success deleted with id=" + id);
        }
        return ResponseEntity.notFound().build();
    }*/

    /*@DeleteMapping("/statutory-body")
    public ResponseEntity<?> deleteAllStatutoryBody() {
        StatutoryBodyService.deleteAllStatutoryBody();

        return ResponseEntity.ok("All deleted Success !");
    }*/


}

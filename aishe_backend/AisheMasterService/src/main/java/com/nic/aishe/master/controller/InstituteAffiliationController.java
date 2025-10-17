/*
package com.nic.aishe.master.controller;

import com.nic.aishe.master.entity.InstituteAffiliation;
import com.nic.aishe.master.impl.AffiliationService;
import com.nic.aishe.master.security.UserInfo;
import com.nic.aishe.master.security.WithUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@CrossOrigin("*")
@RequestMapping("/api")
@RestController
public class InstituteAffiliationController {


    @Autowired(required = false)
    private AffiliationService affiliationService;

    @PostMapping("/institute-affiliation")
    public ResponseEntity<?> addInstituteAffiliation(@RequestBody InstituteAffiliation InstituteAffiliation) {
        InstituteAffiliation InstituteAffiliations = affiliationService.addAffiliation(InstituteAffiliation);
        return ResponseEntity.ok(InstituteAffiliations);
    }

    @GetMapping("/institute-affiliation")
    public ResponseEntity<?> getAllInstituteAffiliation(@WithUser UserInfo userInfo) {
        List<InstituteAffiliation> InstituteAffiliations = affiliationService.getAllAffiliation();
        if (InstituteAffiliations != null && InstituteAffiliations.size() > 0) {
            return ResponseEntity.ok(InstituteAffiliations);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/institute-affiliation/{id}")
    public ResponseEntity<?> getOneInstituteAffiliation(@PathVariable Integer id, @WithUser UserInfo userInfo) {
        InstituteAffiliation InstituteAffiliation = affiliationService.getOneAffiliation(id);
        if (InstituteAffiliation != null) {
            return ResponseEntity.ok(InstituteAffiliation);
        }
        return ResponseEntity.notFound().build();

    }


    @DeleteMapping("/institute-affiliation/{id}")
    public ResponseEntity<?> deleteOneInstituteAffiliation(@PathVariable Integer id) {
        Boolean isdel = affiliationService.deleteOneAffiliation(id);
        if (isdel) {
            return ResponseEntity.ok("Success deleted with id=" + id);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/institute-affiliation")
    public ResponseEntity<?> deleteAllInstituteAffiliation() {
        affiliationService.deleteAllAffiliation();

        return ResponseEntity.ok("All deleted Success !");
    }


}
*/

package com.nic.aishe.master.controller;

import com.nic.aishe.master.entity.RefFormRemunerationNormType;
import com.nic.aishe.master.entity.RefRemunerationNormType;
import com.nic.aishe.master.entity.RefRemunerationStatementApprovalStatus;
import com.nic.aishe.master.entity.RefRemunerationStatus;
import com.nic.aishe.master.entity.RefRemunerationTransactionStatus;
import com.nic.aishe.master.entity.RefRemunerationType;
import com.nic.aishe.master.entity.Remarks;
import com.nic.aishe.master.repo.RefFormRemunerationNormTypeRepo;
import com.nic.aishe.master.repo.RefRemunerationNormTypeRepo;
import com.nic.aishe.master.repo.RefRemunerationStatementApprovalStatusRepo;
import com.nic.aishe.master.repo.RefRemunerationStatusRepo;
import com.nic.aishe.master.repo.RefRemunerationTransactionStatusRepo;
import com.nic.aishe.master.repo.RefRemunerationTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api")
@RestController
//@CrossOrigin("*")
public class RemunerationController {
    @Autowired
    private RefRemunerationTypeRepo typeRepo;
    @Autowired
    private RefFormRemunerationNormTypeRepo formNormTypeRepo;
    @Autowired
    private RefRemunerationNormTypeRepo normTypeRepo;
    @Autowired
    private RefRemunerationStatementApprovalStatusRepo approvalStatusRepo;
    @Autowired
    private RefRemunerationTransactionStatusRepo transactionStatusRepo;

    @Autowired
    private RefRemunerationStatusRepo statusRepo;

    @GetMapping("/remunerationType")
    public ResponseEntity<?> getAllRemunerationType() {
        List<RefRemunerationType> remarks = typeRepo.findAll();
        if (remarks != null && remarks.size() > 0) {
            return ResponseEntity.ok(remarks);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/formRemunerationNormType")
    public ResponseEntity<?> getAllFormRemunerationNormType() {
        List<RefFormRemunerationNormType> remarks = formNormTypeRepo.findAll();
        if (remarks != null && remarks.size() > 0) {
            return ResponseEntity.ok(remarks);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/remunerationNormType")
    public ResponseEntity<?> getAllRemunerationNormType() {
        List<RefRemunerationNormType> remarks = normTypeRepo.findAll();
        if (remarks != null && remarks.size() > 0) {
            return ResponseEntity.ok(remarks);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/remunerationStatementApprovalStatus")
    public ResponseEntity<?> getAllRemunerationStatementApprovalStatus() {
        List<RefRemunerationStatementApprovalStatus> remarks = approvalStatusRepo.findAll();
        if (remarks != null && remarks.size() > 0) {
            return ResponseEntity.ok(remarks);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/remunerationTransactionStatus")
    public ResponseEntity<?> getAllRemunerationTransactionStatus() {
        List<RefRemunerationTransactionStatus> remarks = transactionStatusRepo.findAll();
        if (remarks != null && remarks.size() > 0) {
            return ResponseEntity.ok(remarks);
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/ref-remuneration-status")
    public ResponseEntity<?> getAllRemunerationStatus() {
        List<RefRemunerationStatus> remarks = statusRepo.findAll();
        if (remarks != null && remarks.size() > 0) {
            return ResponseEntity.ok(remarks);
        }
        return ResponseEntity.notFound().build();
    }

}

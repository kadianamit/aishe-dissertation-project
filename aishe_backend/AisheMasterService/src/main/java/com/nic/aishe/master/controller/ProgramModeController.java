package com.nic.aishe.master.controller;

import com.nic.aishe.master.entity.ProgramMode;
import com.nic.aishe.master.repo.ProgramModeRepo;
import com.nic.aishe.master.security.UserInfo;
import com.nic.aishe.master.security.WithUser;
import com.nic.aishe.master.util.CommonObjectOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@CrossOrigin("*")
@RequestMapping("/api")
@RestController
//@CrossOrigin("*")
public class ProgramModeController {


    @Autowired(required = false)
    private ProgramModeRepo programModeRepo;

    /*@PostMapping("/program-mode")
    public ProgramMode addProgramMode(@RequestBody ProgramMode ProgramMode) {
        return programModeRepo.save(ProgramMode);
    }*/

    @GetMapping("/program-mode")
    public List<ProgramMode> getAllType(@WithUser UserInfo userInfo) {
         CommonObjectOperation.usernameValidate(userInfo);
        return programModeRepo.findAll();
    }


}

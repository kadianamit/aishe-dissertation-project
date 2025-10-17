package com.nic.aishe.master.controller;

import com.nic.aishe.master.entity.ProgramStatutoryBody;
import com.nic.aishe.master.repo.ProgramStatuaryBodyRepo;
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
public class ProgramStatuaryBodyController {


    @Autowired(required = false)
    private ProgramStatuaryBodyRepo programStatuaryBodyRepo;
/*

    @PostMapping("/program-statuary")
    public ProgramStatutoryBody addProgramStatutoryBodyody(@RequestBody ProgramStatutoryBody ProgramStatutoryBodyody) {
        return programStatuaryBodyRepo.save(ProgramStatutoryBodyody);
    }
*/

    @GetMapping("/program-statuary")
    public List<ProgramStatutoryBody> getAllType(@WithUser UserInfo userInfo) {
        CommonObjectOperation.usernameValidate(userInfo);
        return programStatuaryBodyRepo.findAll();
    }


}

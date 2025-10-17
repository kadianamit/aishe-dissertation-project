package com.nic.aishe.master.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nic.aishe.master.entity.NepProgramType;
import com.nic.aishe.master.entity.ProgramName;
import com.nic.aishe.master.handler.InvalidInputException;
import com.nic.aishe.master.repo.NepProgramTypeRapo;
import com.nic.aishe.master.repo.ProgramNameRepo;
import com.nic.aishe.master.security.UserInfo;
import com.nic.aishe.master.security.WithUser;
import com.nic.aishe.master.util.CommonObjectOperation;

//@CrossOrigin("*")
@RequestMapping("/api")
@RestController
//@CrossOrigin("*")
public class ProgramNameController {


    @Autowired(required = false)
    private ProgramNameRepo programNameRepo;

    @Autowired(required = false)
    private NepProgramTypeRapo  nepProgramTypeRapo;

    /*@PostMapping("/program-name")
    public ProgramName addProgramName(@RequestBody ProgramName ProgramName){
        return programNameRepo.save(ProgramName);
    }
    */
    @GetMapping("/program-name")
	public List<ProgramName> getAllType( @WithUser UserInfo userInfo ) {
         CommonObjectOperation.usernameValidate(userInfo);
        return programNameRepo.findAllProgramme();
    }

    @GetMapping("/program-name/{levelId}")
	public List<ProgramName> getAllCourseLevel(@PathVariable String levelId,@RequestParam(required=false,defaultValue = "false") boolean teacherHighestQualification  , @WithUser UserInfo userInfo ) {
         CommonObjectOperation.usernameValidate(userInfo);
    	if(teacherHighestQualification && levelId!=null) {
            if(CommonObjectOperation.stringValidate(levelId)){
                if(!CommonObjectOperation.threeLength(levelId)){
                    throw new InvalidInputException("Level Id length should be less then 3 ");
                }
            }
    		return programNameRepo.findByTeacherHighestQualificationId(Integer.valueOf(levelId));
    	}
    	
        if(CommonObjectOperation.stringValidate(levelId)){
            if(!CommonObjectOperation.threeLength(levelId)){
                throw new InvalidInputException("Level Id length should be less then 3 ");
            }
        }
        return programNameRepo.findByLevelId(levelId);
    }
    @GetMapping("/nep-program-type")
    public List<NepProgramType> getAllTypeNepProgram(@WithUser UserInfo userInfo) {
         CommonObjectOperation.usernameValidate(userInfo);
        return nepProgramTypeRapo.findAll();
    }
    /*//NEW Aadded
    @GetMapping("/programme-name/{levelId}")
    public List<ProgramName> getAllByCourseLevel(@PathVariable String levelId, @WithUser UserInfo userInfo) {
        return programNameRepo.findByLevelId(levelId);
    }*/


}

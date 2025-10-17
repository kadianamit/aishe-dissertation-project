package com.nic.aishe.master.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.nic.aishe.master.entity.RefBroadDisciplineGroupCategoryProgramMapEO;
import com.nic.aishe.master.handler.InvalidInputException;
import com.nic.aishe.master.repo.RefBroadDisciplineGroupCategoryProgramMapRepo;
import com.nic.aishe.master.util.CommonObjectOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nic.aishe.master.entity.BroadCategory;
import com.nic.aishe.master.entity.ProgramGroupCategory;
import com.nic.aishe.master.entity.WebDcfBroadCategoryEO;
import com.nic.aishe.master.repo.BroadCategoryRepo;
import com.nic.aishe.master.repo.ProgramGroupCategoryRepo;
import com.nic.aishe.master.repo.WebDcfBroadCategoryRepoEO;
import com.nic.aishe.master.security.UserInfo;
import com.nic.aishe.master.security.WithUser;

//@CrossOrigin("*")
@RequestMapping("/api")
@RestController
public class BroadCategoryController {


    @Autowired(required = false)
    private BroadCategoryRepo broadCategoryRepo;
    @Autowired(required = false)
    private ProgramGroupCategoryRepo programGroupCategoryRepo;
    
    @Autowired(required = false)
    private WebDcfBroadCategoryRepoEO webDcfBroadCategoryRepoEO;

    @Autowired(required = false)
    private RefBroadDisciplineGroupCategoryProgramMapRepo programMapRepo;

   /* @PostMapping("/broad-category")
    public BroadCategory addBroadCategory(@RequestBody BroadCategory BroadCategory) {
        return broadCategoryRepo.save(BroadCategory);
    }*/

    @GetMapping("/broad-category")
    public List<BroadCategory> getAllType(@WithUser UserInfo userInfo) {
        CommonObjectOperation.usernameValidate(userInfo);
        return broadCategoryRepo.findAll();
    }

    @GetMapping("/broad-category/program-name")
    public Set<BroadCategory> getAllByCategByProgram(@RequestParam String id//, @WithUser UserInfo userInfo
    		) {
        //CommanObjectOperation.usernameValidate(userInfo);
        if(CommonObjectOperation.stringValidate(id)){
            if(!CommonObjectOperation.fiveLength(id)){
                throw new InvalidInputException("Id length should be less then 5 ");
            }
        }
        List<ProgramGroupCategory> list = programGroupCategoryRepo.findAllDataById(id);
        Set<BroadCategory> broadCategories = new HashSet<BroadCategory>();
        if (list != null) {
            for (ProgramGroupCategory programGroupCategory : list) {
                BroadCategory optional = broadCategoryRepo.findById(programGroupCategory.getBroadCategory()).get();
                broadCategories.add(optional);
            }
            return broadCategories;
        }
        return null;
    }

    @GetMapping("/broad-group-category-mapping")
	public List<RefBroadDisciplineGroupCategoryProgramMapEO> getAllType(
			@RequestParam(required = false) String programId/* , @WithUser UserInfo userInfo */) {
       // CommanObjectOperation.usernameValidate(userInfo);
        if(CommonObjectOperation.stringValidate(programId)){
            if(!CommonObjectOperation.fiveLength(programId)){
                throw new InvalidInputException("Program id length should be less then 5 ");
            }
        }
        return null==programId?programMapRepo.findAllApplicable():programMapRepo.findAllByProgramIdApplicable(programId);
    }


   // @GetMapping("/broad-category-webdcf")
    public List<WebDcfBroadCategoryEO> getAllBroadCategory(@RequestParam(required = false) String id, @WithUser UserInfo userInfo) {
        CommonObjectOperation.usernameValidate(userInfo);
    	List<WebDcfBroadCategoryEO> data =null;
    	if(id!=null) {
            if(CommonObjectOperation.stringValidate(id)){
                if(!CommonObjectOperation.fiveLength(id)){
                    throw new InvalidInputException("Id length should be less then 5 ");
                }
            }
    		 data = webDcfBroadCategoryRepoEO.findAllCategoryByProgramId(id);
    	}else {
    		 data = webDcfBroadCategoryRepoEO.findAll();
    	}
        return data;
    }

}

package com.nic.aishe.master.controller;

import java.util.ArrayList;
import java.util.List;

import com.nic.aishe.master.handler.InvalidInputException;
import com.nic.aishe.master.util.CommonObjectOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nic.aishe.master.entity.BroadName;
import com.nic.aishe.master.entity.ProgramGroupCategory;
import com.nic.aishe.master.entity.WebDcfBroadDisciplineEO;
import com.nic.aishe.master.repo.BroadNameRepo;
import com.nic.aishe.master.repo.ProgramGroupCategoryRepo;
import com.nic.aishe.master.repo.WebDcfBroadGroupRepoEO;
import com.nic.aishe.master.security.UserInfo;
import com.nic.aishe.master.security.WithUser;

//@CrossOrigin("*")
@RequestMapping("/api")
@RestController
public class BroadNameController {


    @Autowired(required = false)
    private BroadNameRepo broadNameRepo;
    @Autowired(required = false)
    private ProgramGroupCategoryRepo programGroupCategoryRepo;

    @Autowired(required = false)
    private WebDcfBroadGroupRepoEO webDcfBroadGroupRepoEO;
 /*   @PostMapping("/broad-name")
    public BroadName addBroadName(@RequestBody BroadName BroadName) {
        return broadNameRepo.save(BroadName);
    }
*/
    @GetMapping("/broad-name")
	public List<BroadName> getAllType(/* @WithUser UserInfo userInfo */) {
       // CommanObjectOperation.usernameValidate(userInfo);
        return broadNameRepo.findAllType();
    }

   // @GetMapping("/broad-name/categ/{categoryId}")//no use now
    public List<BroadName> getAllByCateg(@PathVariable String categoryId, @WithUser UserInfo userInfo) {
        CommonObjectOperation.usernameValidate(userInfo);
        return broadNameRepo.findAllByCategoryIdId(categoryId);
    }
  
    @GetMapping("/broad-name/categ/{categoryId}")
    public List<BroadName> getAllByBroadDiscipline(@PathVariable String categoryId,@RequestParam(required=false) String programmeId//, @WithUser UserInfo userInfo
    		) {
        //CommanObjectOperation.usernameValidate(userInfo);
    	List<String> list =null;
        if(CommonObjectOperation.stringValidate(categoryId)){
            if(!CommonObjectOperation.fiveLength(categoryId)){
                throw new InvalidInputException("Category Id length should be less then 5 ");
            }
        }
        if(CommonObjectOperation.stringValidate(programmeId)){
            if(!CommonObjectOperation.fiveLength(categoryId)){
                throw new InvalidInputException("programme Id length should be less then 5 ");
            }
        }
    	if(categoryId!=null && programmeId==null) {
    	 list = programGroupCategoryRepo.findByCategoryId(categoryId);
    	}else if (programmeId!=null) {
    		list = programGroupCategoryRepo.findByCategoryAndProgrammeId(categoryId,programmeId);
    	}
    	List<BroadName> newlist = new ArrayList<>();
    	for(String data : list) {
    		BroadName broaddata =broadNameRepo.findByCategoryId(data);
    		newlist.add(broaddata);
    	}
    	return newlist;
    }

    @GetMapping("/broad-name/program-name")
	public List<BroadName> getAllByBroadNameByProgram(@RequestParam String id/* , @WithUser UserInfo userInfo */) {

        if(CommonObjectOperation.stringValidate(id)){
            if(!CommonObjectOperation.fiveLength(id)){
                throw new InvalidInputException("Id length should be less then 5 ");
            }
        }
        List<ProgramGroupCategory> list = programGroupCategoryRepo.findAllDataById(id);
        if (list != null) {
            List<BroadName> broadCategories = new ArrayList<BroadName>();
            for (ProgramGroupCategory category : list) {
                BroadName broadName = broadNameRepo.findById(category.getBroadName()).get();
                broadCategories.add(broadName);
            }
            return broadCategories;
        }
        return null;
    }

    @GetMapping("/broad-name/{id}")
    public BroadName getBroadNameById(@PathVariable String id, @WithUser UserInfo userInfo) {
        CommonObjectOperation.usernameValidate(userInfo);
        if(CommonObjectOperation.stringValidate(id)){
            if(!CommonObjectOperation.fiveLength(id)){
                throw new InvalidInputException("Id length should be less then 5 ");
            }
        }
        return broadNameRepo.findById(id).get();
    }

   // @GetMapping("/broad-name-webdcf/categ")
    public List<WebDcfBroadDisciplineEO> getAllBroadDiscipline(@RequestParam(required = false) String categoryId, @WithUser UserInfo userInfo) {
        CommonObjectOperation.usernameValidate(userInfo);
    	List<WebDcfBroadDisciplineEO> data =null;
    	if(categoryId!=null) {
            if(CommonObjectOperation.stringValidate(categoryId)){
                if(!CommonObjectOperation.fiveLength(categoryId)){
                    throw new InvalidInputException("categoryId length should be less then 5 ");
                }
            }
    		 data = webDcfBroadGroupRepoEO.findAllGroupByCategoryId(categoryId);
    	}else {
    		 data = webDcfBroadGroupRepoEO.findAll();
    	}
    	System.out.println(data.size());
        return data;
    }

}

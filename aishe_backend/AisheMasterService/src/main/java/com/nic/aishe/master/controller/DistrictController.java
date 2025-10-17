package com.nic.aishe.master.controller;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.cache.CacheManager;

import com.nic.aishe.master.handler.InvalidInputException;
import com.nic.aishe.master.util.CommonObjectOperation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nic.aishe.master.entity.District;
import com.nic.aishe.master.repo.DistrictRepo;

//@CrossOrigin("*")
@RequestMapping("/api")
@RestController
public class DistrictController {
	@Autowired
	CacheManager cacheManager;
    @Autowired(required = false)
    private DistrictRepo districtRepo;

   /* @PostMapping("/district")
    public District addDistrict(@RequestBody District District) {
        return districtRepo.save(District);
    }
*/
    @GetMapping("/district/{stateCode}")
    public List<District> getDistrictByState(@PathVariable String stateCode) {
        if(CommonObjectOperation.stringValidate(stateCode)){
            if(!CommonObjectOperation.threeLength(stateCode)){
                throw new InvalidInputException("stateCode length should be less then 3 ");
            }
        }
        return districtRepo.findByStateCode(stateCode)
                .stream()
                .sorted(Comparator.comparing(District::getName))
                .collect(Collectors.toList());
    }

    @GetMapping("/district")
    public List<District> getAllDistrict() {
        return districtRepo.findAllDistrict()
                .stream()
                .sorted(Comparator.comparing(District::getName))
                .collect(Collectors.toList());
    }
    
    @GetMapping("/clearcache")
    public void clearcache() {
    	   for(String name:cacheManager.getCacheNames()){
    		   System.out.println(name);
               cacheManager.getCache(name).clear();            // clear cache by name
           }
    }
}

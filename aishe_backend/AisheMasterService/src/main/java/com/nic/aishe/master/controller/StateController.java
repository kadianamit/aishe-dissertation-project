package com.nic.aishe.master.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.nic.aishe.master.util.CommonObjectOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nic.aishe.master.dto.ApplicationTypeNameListVO;
import com.nic.aishe.master.entity.District;
import com.nic.aishe.master.entity.State;
import com.nic.aishe.master.repo.DistrictRepo;
import com.nic.aishe.master.repo.StateRepo;
import com.nic.aishe.master.security.UserInfo;
import com.nic.aishe.master.security.WithUser;

@RequestMapping("/api")
@RestController

public class StateController {

    @Autowired(required = false)
    private StateRepo stateRepo;

    @Autowired(required = false)
    private DistrictRepo districtRepo;
/*
    @PostMapping("/state")
    public State addState(@RequestBody State State) {
        return stateRepo.save(State);
    }
*/

    @GetMapping("/state")
    public List<State> getAllState() {
    	List<State> list = stateRepo.findAllState()
                .stream()
                .sorted(Comparator.comparing(State::getName))
                .collect(Collectors.toList());
    	List<State> newlist = new ArrayList<>();
    	for(State data : list) {
    		
    		List<District> district =districtRepo.findByStateCode(data.getStateCode())
                    .stream()
                    .sorted(Comparator.comparing(District::getName))
                    .collect(Collectors.toList());
    		data.setDistrict(district);
    		newlist.add(data);
    	}
    	
    	
        return newlist;
    }

    @GetMapping("/state-private")
    public List<State> getAllState(@WithUser UserInfo userInfo) {
        CommonObjectOperation.usernameValidate(userInfo);
        List<State> list = stateRepo.findAllState()
                .stream()
                .sorted(Comparator.comparing(State::getName))
                .collect(Collectors.toList());
        List<State> newlist = new ArrayList<>();
        for(State data : list) {

            List<District> district =districtRepo.findByStateCode(data.getStateCode())
                    .stream()
                    .sorted(Comparator.comparing(District::getName))
                    .collect(Collectors.toList());
            data.setDistrict(district);
            newlist.add(data);
        }


        return newlist;
    }
}

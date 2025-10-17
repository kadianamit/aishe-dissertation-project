package com.nic.aishe.master.controller;

import com.nic.aishe.master.entity.StaffGroups;
import com.nic.aishe.master.impl.StaffGroupsService;
import com.nic.aishe.master.security.UserInfo;
import com.nic.aishe.master.security.WithUser;
import com.nic.aishe.master.util.CommonObjectOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@CrossOrigin("*")
@RequestMapping("/api")
@RestController
public class GroupsController {

    @Autowired(required = false)
    private StaffGroupsService groupsService;

  /*  @PostMapping("/group")
    public ResponseEntity<?> addGroup(@RequestBody StaffGroups group) {
        StaffGroups groups = groupsService.addGroup(group);
        return ResponseEntity.ok(groups);
    }*/

    @GetMapping("/group")
    public ResponseEntity<?> getAllGroup(@WithUser UserInfo userInfo) {
        CommonObjectOperation.usernameValidate(userInfo);
        List<StaffGroups> groups = groupsService.getAllGroup();
        if (groups != null && groups.size() > 0) {
            return ResponseEntity.ok(groups);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/group/{id}")
    public ResponseEntity<?> getOneGroup(@PathVariable Integer id, @WithUser UserInfo userInfo) {
        CommonObjectOperation.usernameValidate(userInfo);
        StaffGroups group = groupsService.getOneGroup(id);
        if (group != null) {
            return ResponseEntity.ok(group);
        }
        return ResponseEntity.notFound().build();

    }


}

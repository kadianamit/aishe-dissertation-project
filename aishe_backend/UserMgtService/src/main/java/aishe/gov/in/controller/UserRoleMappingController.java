package aishe.gov.in.controller;

import java.util.List;

import aishe.gov.in.utility.CommanObjectOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import aishe.gov.in.masterseo.UserRoleEO;
import aishe.gov.in.masterseo.UserRoleMappingEO;
import aishe.gov.in.security.UserInfo;
import aishe.gov.in.security.WithUser;
import aishe.gov.in.service.UserRoleService;
import aishe.gov.in.utility.ReturnResponse;

@RestController
public class UserRoleMappingController {
    @Autowired
    private UserRoleService userRoleService;

    @GetMapping(value = "/userrole")
    public List<UserRoleEO> userRoles() {
        List<UserRoleEO> responseVO = userRoleService.userRoles();
        return responseVO;
    }
    @GetMapping(value = "/userrole-private")
    public List<UserRoleEO> userRoles(@WithUser UserInfo userInfo) {
        CommanObjectOperation.usernameValidate(userInfo);
        List<UserRoleEO> responseVO = userRoleService.userRoles();
        return responseVO;
    }

    @GetMapping(value = "/userrolemapping")
    public List<UserRoleMappingEO> userRoles(@RequestParam(required = true) String userId,@WithUser UserInfo userInfo) {
        CommanObjectOperation.usernameValidate(userInfo);
        List<UserRoleMappingEO> responseVO = userRoleService.userRoleMapping(userId);
        return responseVO;
    }

    @PostMapping(value = "/api/user/userrolemapping")
    public ResponseEntity<ReturnResponse> saveuserRoleMapping(@RequestBody List<UserRoleMappingEO> userroleMapping,@WithUser UserInfo userInfo) {
        CommanObjectOperation.usernameValidate(userInfo);
        Boolean requestData = userRoleService.saveuserRoleMapping(userroleMapping, userInfo);
        if (requestData) {
            return new ResponseEntity<>(new ReturnResponse(HttpStatus.OK.value(), "User Role Details has been  Successfully Saved!!"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST);
        }
    }
}

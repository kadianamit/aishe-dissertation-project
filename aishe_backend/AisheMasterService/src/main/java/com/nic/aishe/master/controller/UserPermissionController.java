package com.nic.aishe.master.controller;

import com.nic.aishe.master.dto.RoleDTO;
import com.nic.aishe.master.entity.RefStateBody;
import com.nic.aishe.master.entity.RefUserRoleMaster;
import com.nic.aishe.master.repo.RefStateBodyRapo;
import com.nic.aishe.master.repo.RefUserRoleMasterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api")
@RestController
//@CrossOrigin("*")
public class UserPermissionController {

    @Autowired(required = false)
    private RefUserRoleMasterRepo roleRepo;


    @Autowired(required = false)
    private RefStateBodyRapo refStateBodyRapo;


    @GetMapping("/user-permission")
	public ResponseEntity<?> getUserPermissionById(@RequestParam(required = false) Integer roleId,
			@RequestParam(required = false) Integer levelId,
			@RequestParam(required = false) Boolean approvingAuthority /* , @WithUser UserInfo userInfo */) {
        // CommanObjectOperation.usernameValidate(userInfo);
        List<RefUserRoleMaster> userRole = null;
        approvingAuthority = approvingAuthority != null ? approvingAuthority : false;
        if (null != levelId && null != roleId)
            userRole = setUserRole(roleRepo.getUserRoleByIdandLevel(roleId, levelId));


        else if (null == levelId && null != roleId) {
            userRole = setUserRole(roleRepo.getUserRoleById(roleId));
        } else if (null != levelId && null == roleId) {
            userRole = setUserRole(roleRepo.getUserRoleByLevel(levelId));
        } else {
            if (approvingAuthority) {
                userRole = setUserRole(roleRepo.getRoleByApprovingAuthority());
            } else {
                userRole = setUserRole(Optional.of(roleRepo.findAll()));
            }
        }
        if (!userRole.isEmpty()) {
            return ResponseEntity.ok(userRole);
        }
        return ResponseEntity.notFound().build();
    }


    private List<RefUserRoleMaster> setUserRole(Optional<List<RefUserRoleMaster>> userRole) {
        List<RefUserRoleMaster> refUserRoleMasters = new ArrayList<>();
        if (userRole.isPresent()) {
            for (RefUserRoleMaster roleMaster : userRole.get()) {
                List<Integer> list = roleMaster.getSubroleLevel1();
                if (null != list) {
                    List list1 = new ArrayList();
                    for (Integer integer : list) {
                        RefUserRoleMaster userRoleMaster1 = roleRepo.getRoleById(integer);
                        if (null != userRoleMaster1) {
                            list1.add(new RoleDTO(userRoleMaster1.getRoleId(), userRoleMaster1.getRoleName()));

                        }
                    }
                    roleMaster.setSubRoleLevel1(list1);
                }
                List<Integer> list5 = roleMaster.getSubroleLevel2();
                if (null != list5) {
                    List list2 = new ArrayList();
                    for (Integer integer : list5) {
                        RefUserRoleMaster userRoleMaster2 = roleRepo.getRoleById(integer);
                        if (null != userRoleMaster2) {
                            list2.add(new RoleDTO(userRoleMaster2.getRoleId(), userRoleMaster2.getRoleName()));


                        }
                        roleMaster.setSubRoleLevel2(list2);
                    }
                }
                List<Integer> list4 = roleMaster.getSubroleLevel3();
                if (null != list4) {
                    List list3 = new ArrayList();
                    for (Integer integer : list4) {
                        RefUserRoleMaster userRoleMaster3 = roleRepo.getRoleById(integer);
                        if (null != userRoleMaster3) {
                            list3.add(new RoleDTO(userRoleMaster3.getRoleId(), userRoleMaster3.getRoleName()));

                        }
                    }
                    roleMaster.setSubRoleLevel3(list3);
                }
                if (null != roleMaster.getDeoApplicable()) {
                    if (roleMaster.getDeoApplicable()) {
                        List<Integer> deoList = roleMaster.getDeos();
                        if (null != deoList) {
                            List deoLists = new ArrayList();
                            for (Integer integer : deoList) {
                                RefUserRoleMaster deoRole = roleRepo.getRoleById(integer);
                                if (null != deoRole) {
                                    deoLists.add(new RoleDTO(deoRole.getRoleId(), deoRole.getRoleName()));
                                }
                            }
                            roleMaster.setDeoLevel(deoLists);
                        }
                    }
                }
                if (null != roleMaster.getApprovingAuthorityRole() && roleMaster.getApprovingAuthorityRole()) {
                    if (roleMaster.getDeoApplicable()) {
                        List<Integer> authList = roleMaster.getApprovingAuthority();
                        if (null != authList) {
                            List deoLists = new ArrayList();
                            for (Integer integer : authList) {
                                RefUserRoleMaster deoRole = roleRepo.getRoleById(integer);
                                if (null != deoRole) {
                                    deoLists.add(new RoleDTO(deoRole.getRoleId(), deoRole.getRoleName()));
                                }
                            }
                            roleMaster.setApprovingAuthorities(deoLists);
                        }
                    }
                }

                if (null != roleMaster.getStandaloneType()) {
                    List<RefStateBody> stateBodies = new ArrayList<>();
                    for (Integer stateBody : roleMaster.getStandaloneType()) {
                        stateBodies.add(refStateBodyRapo.findById(stateBody).get());
                    }
                    roleMaster.setStateBodies(stateBodies);
                }

                refUserRoleMasters.add(roleMaster);
            }
        }
        return refUserRoleMasters;
    }
}


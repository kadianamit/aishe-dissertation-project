package aishe.gov.in.dao;

import java.util.List;

import aishe.gov.in.masterseo.UserRoleEO;
import aishe.gov.in.masterseo.UserRoleMappingEO;
import aishe.gov.in.security.UserInfo;

public interface UserRoleDao {
 
	List<UserRoleEO> userRoles();

	List<UserRoleMappingEO> userRoleMapping(String userId);

	Boolean saveuserRoleMapping(List<UserRoleMappingEO> userroleMapping, UserInfo userInfo);
}
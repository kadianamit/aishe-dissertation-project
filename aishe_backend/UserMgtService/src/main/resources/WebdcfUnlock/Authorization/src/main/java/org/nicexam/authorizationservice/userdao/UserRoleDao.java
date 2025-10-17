package org.nicexam.authorizationservice.userdao;

import java.util.List;

import org.nicexam.authorizationservice.usereo.UserMenuPrivilegesEO;
import org.nicexam.authorizationservice.usereo.UserRolesEO;

public interface UserRoleDao {
	/* User role */

	boolean saveUserRoleData(UserRolesEO userRole);

	boolean updateUserRoleData(UserRolesEO userRole);

	boolean changeUserRoleStatus(UserRolesEO roles);

	List<UserRolesEO> fetchAllRoleData();

	UserRolesEO fetchRoleDataById(int id);

	List<UserRolesEO> fetchuserroledata(List<Integer> rolesRecordId);

	List<UserMenuPrivilegesEO> fetchAllPrivligesData();
}

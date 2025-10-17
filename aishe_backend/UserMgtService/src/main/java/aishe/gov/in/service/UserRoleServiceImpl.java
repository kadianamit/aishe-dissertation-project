package aishe.gov.in.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aishe.gov.in.dao.UserRoleDao;
import aishe.gov.in.masterseo.UserRoleEO;
import aishe.gov.in.masterseo.UserRoleMappingEO;
import aishe.gov.in.security.UserInfo;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleDao userRoleDao;
	@Override
	public List<UserRoleEO> userRoles() {
		return userRoleDao.userRoles();
	}
	@Override
	public List<UserRoleMappingEO> userRoleMapping(String userId) {
		return userRoleDao.userRoleMapping(userId);
	}
	@Override
	public Boolean saveuserRoleMapping(List<UserRoleMappingEO> userroleMapping, UserInfo userInfo) {
		return userRoleDao.saveuserRoleMapping(userroleMapping,userInfo);
	}
}
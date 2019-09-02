package com.webservice.service.impl;

import com.webservice.dao.IUserRoleDao;
import com.webservice.entity.User;
import com.webservice.entity.UserRole;
import com.webservice.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements IUserRoleService {

	@Autowired
	IUserRoleDao userRoleDao;


	public void editUserRole(User user, long[] roleIds) {
		// 删除当前用户所有的角色
		List<UserRole> userRoleList = userRoleDao.getUserRoleByUID(user.getId());
		for (UserRole userRole : userRoleList){
			userRoleDao.deleteByPrimaryKey(userRole.getId());
		}

		// 设置新的角色关系
		if (null != roleIds){
			for (long rid : roleIds) {
				UserRole userRole = new UserRole();
				userRole.setRid(rid);
				userRole.setUid(user.getId());
				userRoleDao.insertUserRole(userRole);
			}
		}
	}
}

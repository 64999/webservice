package com.webservice.service.impl;

import com.webservice.dao.IRolePermissionDao;
import com.webservice.entity.Role;
import com.webservice.entity.RolePermission;
import com.webservice.service.IRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolePermissionServiceImpl implements IRolePermissionService {
	@Autowired
	IRolePermissionDao rolePermissionDao;


	public void updateRolePermission(Role role, long[] permissionIds) {
		// 删除当前角色所有的权限
		List<RolePermission> rolePermissionList = rolePermissionDao.selectListRolePermissionByRID(role.getId());
		for (RolePermission rolePermission : rolePermissionList){
			rolePermissionDao.deleteByPrimaryKey(rolePermission.getId());
		}

		// 设置新的权限关系
		if (null != permissionIds){
			for (long pid : permissionIds) {
				RolePermission rolePermission = new RolePermission();
				rolePermission.setPid(pid);
				rolePermission.setRid(role.getId());
				rolePermissionDao.insertRolePermission(rolePermission);
			}
		}

	}
}

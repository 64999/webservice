package com.webservice.service.impl;

import com.webservice.dao.IPermissionDao;
import com.webservice.dao.IRolePermissionDao;
import com.webservice.entity.Permission;
import com.webservice.entity.Role;
import com.webservice.entity.RolePermission;
import com.webservice.service.IPermissionService;
import com.webservice.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PermissionServiceImpl implements IPermissionService {

	@Autowired(required = false)
	IRoleService roleService;
	@Autowired
	IRolePermissionDao rolePermissionDao;
	@Autowired
	IPermissionDao permissionDao;

	public Set<String> listPermissionName(String name) {
		Set<String> result = new HashSet<String>();
		List<Role> roles = roleService.listRoleByName(name);
		List<RolePermission> rolePermissions = new ArrayList<RolePermission>();
		for (Role role : roles) {
			List<RolePermission> rps = rolePermissionDao.selectListRolePermissionByRID(role.getId());
			rolePermissions.addAll(rps);
		}

		for (RolePermission rolePermission : rolePermissions) {
			Permission p = permissionDao.selectByPrimaryKey(rolePermission.getPid());
			result.add(p.getName());
		}

		return result;
	}

	public List<Permission> getPermissionListByRole(Role role) {
		List<Permission> result = new ArrayList<Permission>();

		List<RolePermission> rolePermissionList = rolePermissionDao.selectListRolePermissionByRID(role.getId());

		for (RolePermission rolePermission : rolePermissionList) {
			result.add(permissionDao.selectByPrimaryKey(rolePermission.getPid()));
		}
		return result;
	}

	public List<Permission> getPermissionList() {
		return permissionDao.selectPermissionList();
	}

	public void addPermission(Permission permission) {
		permissionDao.insertPermission(permission);
	}

	public Permission getPermissionByID(long id) {
		return permissionDao.selectByPrimaryKey(id);
	}

	public void updatePermission(Permission permission) {
		permissionDao.updatePermission(permission);
	}

	public void deletePermission(long id) {
		permissionDao.deletePermission(id);
	}

	/**
	 * needInterceptor 表示是否要进行拦截，
	 * 判断依据是如果访问的某个 url,在权限系统里存在，就要进行拦截。 如果不存在，就放行了。
	 * 也可以用这种，即，访问的地址如果不存在于权限系统中，就提示没有拦截。
	 */
	public boolean needInterceptor(String requestURI) {
		List<Permission> ps = getPermissionList();
		for (Permission p : ps) {
			if (p.getUrl().equals(requestURI)){
				return true;
			}
		}
		return false;
	}

	/**
	 * 用来获取某个用户所拥有的权限地址集合
	 */
	public Set<String> listPermissionURLs(String userName) {
		Set<String> result = new HashSet<String>();
		List<Role> roles = roleService.listRoleByName(userName);

		List<RolePermission> rolePermissions = new ArrayList<RolePermission>();

		for (Role role : roles) {
			List<RolePermission> rps = rolePermissionDao.selectListRolePermissionByRID(role.getId());
			rolePermissions.addAll(rps);
		}
		for (RolePermission rolePermission : rolePermissions) {
			Permission p = permissionDao.selectByPrimaryKey(rolePermission.getPid());
			result.add(p.getUrl());
		}
		return result;
	}
}

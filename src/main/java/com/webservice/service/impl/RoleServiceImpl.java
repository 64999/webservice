package com.webservice.service.impl;

import com.webservice.dao.IRoleDao;
import com.webservice.dao.IUserRoleDao;
import com.webservice.entity.Role;
import com.webservice.entity.User;
import com.webservice.entity.UserRole;
import com.webservice.service.IRoleService;
import com.webservice.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	IUserService userService;
	@Autowired
	IUserRoleDao userRoleDao;
	@Autowired
	IRoleDao roleDao;

	/**
	 * 通过 用户名 获取 他拥有的所有角色名
	 */
	public Set<String> listRoleName(String name) {
		Set<String> result = new HashSet<String>();
		List<Role> roles = listRoleByName(name);
		for (Role role : roles) {
			result.add(role.getName());
		}
		return result;
	}

	/**
	 * 通过 用户名 获取 他拥有的所有角色
	 */
	public List<Role> listRoleByName(String name) {
		List<Role> roles = new ArrayList<Role>();
		User user = userService.getUserByName(name);
		if (null == user)
			return roles;

		roles = listRoleByUser(user);
		return roles;
	}

	/**
	 * 通过 用户 获取 他拥有的所有角色
	 */
	public List<Role> listRoleByUser(User user) {
		List<Role> roles = new ArrayList<Role>();

		List<UserRole> userRoles = userRoleDao.getUserRoleByUID(user.getId());

		for (UserRole userRole : userRoles) {
			Role role = roleDao.selectByPrimaryKey(userRole.getRid());
			roles.add(role);
		}
		return roles;
	}

	public List<Role> listRole() {
		return roleDao.selectRoleList();
	}

	public void addRole(Role role) {
		roleDao.insertRole(role);
	}

	public Role getRole(long id) {
		return roleDao.selectByPrimaryKey(id);
	}

	public void updateRole(Role role) {
		roleDao.updateRole(role);
	}

	public void deleteRoleByID(long id) {
		roleDao.deleteRoleByID(id);
	}

}

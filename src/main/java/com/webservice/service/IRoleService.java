package com.webservice.service;



import com.webservice.entity.Role;
import com.webservice.entity.User;

import java.util.List;
import java.util.Set;

public interface IRoleService{
	Set<String> listRoleName(String name);
	List<Role> listRoleByName(String name);

	List<Role> listRoleByUser(User user);

	List<Role> listRole();

	void addRole(Role role);

	Role getRole(long id);

	void updateRole(Role role);

	void deleteRoleByID(long id);
}

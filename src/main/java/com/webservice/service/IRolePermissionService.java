package com.webservice.service;


import com.webservice.entity.Role;

public interface IRolePermissionService {
	void updateRolePermission(Role role, long[] permissionIds);
}

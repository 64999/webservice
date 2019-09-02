package com.webservice.dao;


import com.webservice.entity.RolePermission;

import java.util.List;

public interface IRolePermissionDao {
	List<RolePermission> selectListRolePermissionByRID(Long rid);

	void deleteByPrimaryKey(Long id);

	void insertRolePermission(RolePermission rolePermission);
}

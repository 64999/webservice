package com.webservice.dao;


import com.webservice.entity.UserRole;

import java.util.List;

public interface IUserRoleDao {
	List<UserRole> getUserRoleByUID(Long uid);

	void deleteByPrimaryKey(Long id);

	void insertUserRole(UserRole userRole);
}

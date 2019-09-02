package com.webservice.service;


import com.webservice.entity.User;

public interface IUserRoleService {
	void editUserRole(User user, long[] roleIds);
}

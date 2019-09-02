package com.webservice.dao;


import com.webservice.entity.User;

import java.util.List;

public interface IUserDao {
	User getUserByName(String name);

	List<User> selectUserList();

	void insertUser(User user);

	void deleteUser(long id);

	User getUserByID(long id);

	void updateUser(User user);
}

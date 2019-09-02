package com.webservice.service.impl;

import com.webservice.dao.IUserDao;
import com.webservice.entity.User;
import com.webservice.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	IUserDao userDao;


	public User getUserByName(String name) {
		return userDao.getUserByName(name);
	}

	public List<User> listUser() {
		return userDao.selectUserList();
	}

	public void addUser(User user) {
		userDao.insertUser(user);
	}

	public void deleteUser(long id) {
		userDao.deleteUser(id);
	}

	public User getUserByID(long id) {
		return userDao.getUserByID(id);
	}

	public void updateUser(User user) {
		userDao.updateUser(user);
	}
}

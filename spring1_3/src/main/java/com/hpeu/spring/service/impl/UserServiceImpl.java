package com.hpeu.spring.service.impl;

import java.util.List;

import com.hpeu.spring.dao.UserDao;
import com.hpeu.spring.entity.User;
import com.hpeu.spring.service.UserService;

public class UserServiceImpl implements UserService{

	private UserDao userDao;
	
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	public void add(String sql, User entity) {
		userDao.add(sql, entity);
	}

	public void update(String sql, User entity) {
		userDao.update(sql, entity);
	}

	public void del(String sql, int id) {
		userDao.del(sql, id);
	}

	public User getEntity(String sql, int id) {
		return userDao.getEntity(sql, id);
	}

	public List<User> getAll(String sql) {
		return userDao.getAll(sql);
	}

}

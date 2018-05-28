package com.hpeu.spring.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hpeu.spring.entity.User;
import com.hpeu.spring.service.UserService;

public class UserTest {

	static UserService userService;
	
	static {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		userService = context.getBean(UserService.class);
	}
	
	@Test
	public void add() {
		String sql = "insert into t_user(name, age) values(?, ?)";
		for(int i = 1; i < 20; i++) {
			User u = new User();
			u.setName("狗蛋" + i);
			u.setAge(20 + i);
			userService.add(sql, u);
		}
	}
	
	@Test
	public void update() {
		User u = userService.getEntity("select * from t_user where id = ?", 5);
		if(u != null) {
			String sql = "update t_user set name = ?, age = ? where id=?";
			u.setName("张三");
			userService.update(sql, u);
		}
	}
	
	@Test
	public void get() {
		User u = userService.getEntity("select * from t_user where id = ?", 5);
		System.out.println(u);
	}
	
	@Test
	public void getAll() {
		List<User> users = userService.getAll("select * from t_user");
		for(User u : users) {
			System.out.println(u);
		}
	}
}

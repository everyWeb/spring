package com.hpeu.spring.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.hpeu.spring.dao.UserDao;
import com.hpeu.spring.entity.User;

public class UserDaoImpl implements UserDao{

	private JdbcTemplate jdbcTemplate;
	
	public UserDaoImpl() {}
	
	public UserDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public void add(String sql, User entity) {
		/*参数说明
		 *sql:sql语句 insert into t_user(name, age) values(?,?)
		 *args:需要注入的参数
		 */
//		jdbcTemplate.update(sql, entity.getName(), entity.getAge());
		jdbcTemplate.update(sql, new Object[]{entity.getName(), entity.getAge()});
	}

	public void update(String sql, User entity) {
		jdbcTemplate.update(sql, new Object[]{entity.getName(), entity.getAge(), entity.getId()});
	}

	public void del(String sql, int id) {
		jdbcTemplate.update(sql, new Object[] {id});
	}

	public User getEntity(String sql, int id) {
		User user = jdbcTemplate.queryForObject(sql, new Object[] {id},
				new RowMapper<User>() {
					public User mapRow(ResultSet rs, int rowNum) throws SQLException {
						User u = new User();
						u.setId(rs.getInt("id"));
						u.setName(rs.getString("name"));
						u.setAge(rs.getInt("age"));
						return u;
					}
		});
		return user;
	}

	public List<User> getAll(String sql) {
		List<User> users = jdbcTemplate.query(sql, new RowMapper<User>() {
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User u = new User();
				u.setId(rs.getInt("id"));
				u.setName(rs.getString("name"));
				u.setAge(rs.getInt("age"));
				return u;
			}
			
		});
		return users;
	}

}

/*class rowUser implements RowMapper<User>{

	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		return null;
	}
	
}*/

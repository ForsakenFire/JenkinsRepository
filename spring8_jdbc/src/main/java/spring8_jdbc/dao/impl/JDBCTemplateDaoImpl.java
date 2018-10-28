package spring8_jdbc.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import spring8_jdbc.RowMapper.UserRowMapper;
import spring8_jdbc.dao.JDBCTemplateDao;

@Repository
public class JDBCTemplateDaoImpl implements JDBCTemplateDao{
	
	@Autowired
	private JdbcOperations jdbcOperations;
	
	@Override
	public String getName() {
		String sql = "select * from td_student where id = ?";
		/*
		 使用NamedParameterJdbcTemplate时sql可以写成
		 select * from td_student where id = :id
		 */
		/*  jdk8引入的语法糖：lambda表达式
		jdbcOperations.queryForObject("",(rs,rowNum) -> {
			User user = new User();
			user.setId(rs.getLong("id"));
			user.setAge(rs.getLong("age"));
			user.setPhone(rs.getString("phone"));
			user.setName(rs.getString("name"));
			user.setSex(rs.getLong("sex"));
			return user;
		});
		*/
		return jdbcOperations.queryForObject(sql, new UserRowMapper(),1).getName();
		
	}

	@Override
	public long insertUser() {
		String sql = "insert into td_student(name,sex,age,phone) values(?,?,?,?)";
		/*
		 使用NamedParameterJdbcTemplate时sql可以写成
		 insert into td_student(name,sex,age,phone) values(:name,:sex,:age,:phone)
		 */
		return jdbcOperations.update(sql, "小王",1,21,110);
	}

}

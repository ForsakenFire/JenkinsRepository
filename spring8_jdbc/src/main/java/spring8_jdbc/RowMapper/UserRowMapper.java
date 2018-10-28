package spring8_jdbc.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import spring8_jdbc.bean.User;

/**
 * 完成字段和实体属性映射
 * @author TD
 *
 */
public class UserRowMapper implements RowMapper<User>{

	@Override
	public User mapRow(ResultSet rs, int arg1) throws SQLException {
		User user = new User();
		user.setId(rs.getLong("id"));
		user.setAge(rs.getLong("age"));
		user.setPhone(rs.getString("phone"));
		user.setName(rs.getString("name"));
		user.setSex(rs.getLong("sex"));
		return user;
	}

}

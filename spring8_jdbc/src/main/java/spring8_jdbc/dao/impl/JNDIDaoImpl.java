package spring8_jdbc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import spring8_jdbc.dao.JNDIDao;

@Repository
public class JNDIDaoImpl  implements JNDIDao{

	@Autowired
	private DataSource tomcatDataSource;

	@Override
	public String getName() throws SQLException {
		String strRet = "";
		Connection conn = tomcatDataSource.getConnection();
		//PreparedStatement ps = conn.prepareStatement("select * from td_student where id = 1");
		PreparedStatement ps = conn.prepareStatement("select * from bs_user where id = 11");
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			//strRet = rs.getString("name");
			strRet = rs.getString("username");
		}
		if(rs != null){
			rs.close();
			rs = null;
		}
		if(ps != null){
			ps.close();
			ps = null;
		}
		if(conn != null){
			conn.close();
			conn = null;
		}
		return strRet;
	}
	
}

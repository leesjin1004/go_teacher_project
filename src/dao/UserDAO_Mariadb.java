package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.JDBCUtil;
import vo.UserVo;

public class UserDAO_Mariadb{

public UserVo login(UserVo vo) {
	return login(vo.getId(), vo.getPassword());
}

	public UserVo login(String id, String password) {
		UserVo vo = null;
		String sql = "select * from user where id=? and password=?";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, password);
			
			rs= ps.executeQuery();
			
			while(rs.next()) {
				vo = new UserVo();
				vo.setId(rs.getNString("id"));
				vo.setName(rs.getNString("name"));
				vo.setPassword("password");
			}
		} catch (Exception e) {
			 System.out.println(e);
		} finally {
			JDBCUtil.close(con, ps, rs);
		}
		return vo;
	}
}

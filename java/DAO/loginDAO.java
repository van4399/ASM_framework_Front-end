package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import BEAN.User;
import DB.XJdbcHelper;
import Utils.ManagerUser;

public class loginDAO {
	private final String sqlSelect = "select gmail,passwords from user where role = ?;";
	
	public String Login(User user) {
		ResultSet rs;
		try {
			rs = XJdbcHelper.query(sqlSelect, 0);
			while(rs.next()) {
				if(rs.getString("gmail").trim().compareTo(user.getEmail().trim()) == 0) {
					if(rs.getString("passwords").trim().compareTo(user.getPassword().trim()) == 0) {
						ManagerUser.setUser(user);
						return null;
					}else {
						return "Mật khẩu sai";
					}
				}
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Tài khoảng không tôn tại";
	}
}

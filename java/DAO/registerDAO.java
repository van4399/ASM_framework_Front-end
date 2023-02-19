package DAO;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.http.HttpServletResponse;

import BEAN.Comment;
import BEAN.User;
import DB.XJdbcHelper;

public class registerDAO {
	private final String userInsert = "{CALL insertUser(?,?,?)}";
	private final String checkUser ="select count(gmail) as amount from user where gmail = ?";
	
	public boolean insert(User u,HttpServletResponse response) {
		try {
			return XJdbcHelper.update(userInsert , new Object[] {u.getEmail() , u.getPassword() ,0});
		} catch (SQLIntegrityConstraintViolationException e) {

			try {
				response.getWriter().print("Tài khoảng này đã đăng kí ");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean checkUser(String email) {
		ResultSet rs;
		int out = 1;
		try {
			rs = XJdbcHelper.query(checkUser , new Object[] {email});
			rs.next();
	
				 out = rs.getInt("amount");
					System.out.println(out);
			return out==0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
}

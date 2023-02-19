package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import BEAN.Comment;

import DB.XJdbcHelper;
import Utils.ManagerUser;

public class commentDAO {
	public boolean postComment(String content,String rate , String date , String idPro) {
		final String sql = "insert into cmt (cmt_user , cmt_product ,content ,datecmt , rate) values(?,?,?,?,?);";
		try {
			return XJdbcHelper.update(sql, new Object[] {ManagerUser.getUsers().getEmail() , idPro , content,date,rate});
		} catch (SQLIntegrityConstraintViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return false;
	}
	
	public List<Comment> getComment(String productId){
		final String sql ="select id,cmt_user,cmt_product,content,datecmt,rate from cmt where cmt_product = ? and statuss = 1";
		
		 List<Comment> list = new ArrayList<>();
		ResultSet rs;
		try {
			rs = XJdbcHelper.query(sql , new Object[] {productId});
			while(rs.next()) {
				String  id = rs.getString("id");
				String  content = rs.getString("content");
				String  email = rs.getString("cmt_user");
				String  date = rs.getString("datecmt");
				String  star = rs.getString("rate");
			list.add(new Comment(id,email,date,star,content));
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	
		}

		return null;
	}
	
	public boolean removeCommentById(String id ) {
		final String sql ="update cmt set statuss = 0 where id = ? and cmt_user = ?";
		try {
			return XJdbcHelper.update(sql, new Object[] {  id,ManagerUser.getUsers().getEmail()});
		} catch (SQLIntegrityConstraintViolationException e) {
			ManagerUser.logOut();
			e.printStackTrace();
		}
		return false;
	}
}

package DAO;

import java.sql.SQLIntegrityConstraintViolationException;

import DB.XJdbcHelper;
import Utils.ManagerUser;

public class orderDAO {
	final String  sql = "{call ordersNow(?,?,?)}";
	
	public boolean orderNow(String id , String quantity) {
		try {
			return XJdbcHelper.update(sql, new Object[] {ManagerUser.getUsers().getEmail() , id , quantity});
		} catch (SQLIntegrityConstraintViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}

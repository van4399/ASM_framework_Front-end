package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import BEAN.User;
import BEAN.product;
import BEAN.shoppingCart;
import DB.XJdbcHelper;
import Utils.ManagerUser;

public class shoppingCartDAO {

	private final String sql_add_proc_cart = "{Call addProductToCard (?,?)}";
	private final String sql_getCart = "{call getCartByUser(?)}";
	

	
	public List<product>  renderCart(User u) {
		List<product>  list = new ArrayList<product>();
		try {
	
			ResultSet rs = XJdbcHelper.query(sql_getCart , new Object[] {u.getEmail()});
				while(rs.next()) {
					int id = rs.getInt("id");
					String  name = rs.getString("name");
					float price = rs.getFloat("price");
					String describle = rs.getString("describle");
					String images =rs.getString("images");
					String video = rs.getString("video");
					int amount = rs.getInt("quantity");
					product proc = new product();
					proc.setId(id); proc.setNameString(name);proc.setPrice(price);proc.setDesc(describle);
					proc.setImages(images); proc.setVideo(video);proc.setAmount(amount);
					list.add(proc);
				}
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	public boolean addProductToCart(String id) {
		try {
			return XJdbcHelper.update(sql_add_proc_cart, new Object[] {ManagerUser.getUsers().getEmail() , id});
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			return false;
		}	}
	
	
}

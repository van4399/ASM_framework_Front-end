package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import BEAN.product;
import DB.XJdbcHelper;
import Utils.ManagerUser;

public class productsDao {
	final String sqlRemoveProduct = "\r\n"
			+ " delete from orders where  product_id = ? and cart_id in (select id from cart where user_id = ?);";
	
	public boolean removeProduct(String id) {
		try {
			return XJdbcHelper.update(sqlRemoveProduct, new Object[] {id , ManagerUser.getUsers().getEmail()});
		} catch (SQLIntegrityConstraintViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	public product renderProductById(String ids) {
		final String  sql = "select  id, name ,price, rate,status , describle,images ,video from product where id = " +ids;
		
		try {
			
			ResultSet rs = XJdbcHelper.query(sql);
			
				while(rs.next()) {
					int id = rs.getInt("id");
					String  name = rs.getString("name");
					float price = rs.getFloat("price");
					int rate = rs.getInt("rate");
					boolean status = rs.getBoolean("status");
					String describle = rs.getString("describle");
					String images =rs.getString("images");
					String video = rs.getString("video");
				
					return new product(id ,name , price ,rate , status,describle ,images, video);
				}
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<product>  renderProducts(String category) {
		List<product>  list = new ArrayList<product>();
		String sql = "select  id, name ,price, rate,status , describle,images ,video,category_id from product where category_id = " +category;
		try {
	
			ResultSet rs = XJdbcHelper.query(sql);
			
				while(rs.next()) {
					int id = rs.getInt("id");
					String  name = rs.getString("name");
					float price = rs.getFloat("price");
					int rate = rs.getInt("rate");
					boolean status = rs.getBoolean("status");
					String describle = rs.getString("describle");
					String images =rs.getString("images");
					String video = rs.getString("video");
					int category_id =rs.getInt("category_id");
				
					list.add(new product(id ,name , price ,rate , status,describle ,images, video,category_id));
				}
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public List<product>  renderProducts() {
		List<product>  list = new ArrayList<product>();
		final String  sql_getFavoriteProduct = "select product_id,product.* from orders\r\n"
				+ " inner join product on product.id = orders.product_id\r\n"
				+ " where product.category_id = 1\r\n"
				+ " group by product_id\r\n"
				+ " order by  sum(quantity) desc LIMIT 5;";
		try {
	
			ResultSet rs = XJdbcHelper.query(sql_getFavoriteProduct);
			
				while(rs.next()) {
					int id = rs.getInt("id");
					String  name = rs.getString("name");
					float price = rs.getFloat("price");
					int rate = rs.getInt("rate");
					boolean status = rs.getBoolean("status");
					String describle = rs.getString("describle");
					String images =rs.getString("images");
					String video = rs.getString("video");
					int category_id =rs.getInt("category_id");
				
					list.add(new product(id ,name , price ,rate , status,describle ,images, video,category_id));
				}
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
}

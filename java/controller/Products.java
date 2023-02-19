package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import BEAN.product;
import DAO.productsDao;


@WebServlet(urlPatterns = {"/danh-sach-san-pham.php" , "/get-data.php","/remove-product.php"})
public class Products extends HttpServlet {
	private static final long serialVersionUID = 1L;
	productsDao dao = new productsDao();

    public Products() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURI();
		response.setContentType("application/json;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
	
		if(url.contains("danh-sach-san-pham.php")) {
			request.getRequestDispatcher("View/products.jsp?cache="+Math.random()).forward(request, response);
		}else if(url.contains("get-data.php")) {
			List<product> list = dao.renderProducts("1");
			JSONArray array = new JSONArray();
			for (product item : list) {
				try {
					JSONObject prod = new JSONObject();
					prod.put("id", item.getId());
					prod.put("name", item.getNameString());
					prod.put("price",String.format("%,.2f",  item.getPrice()).
							substring(0,String.format("%,.2f",  item.getPrice()).lastIndexOf(".00") ) );
					prod.put("priceDefault",  item.getPrice());
					prod.put("rate", item.getRate());
					prod.put("status", item.isStatus()==true ? "Còn hàng" :"Hết hàng");
					prod.put("desc", item.getDesc());
					prod.put("imageMain" , item.getImages().split(",").clone()[0]);
					prod.put("video" , item.getVideo());
					array.put(prod);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
        response.getWriter().write(array.toString());
			
		}else if(url.contains("remove-product.php")) {
				String id = request.getParameter("idPro");
				if(dao.removeProduct(id)) {
					response.getWriter().print((true));
				}else {
					response.getWriter().print((false));
				}
			System.out.println(id);
		}
	
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}

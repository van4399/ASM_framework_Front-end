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
import DAO.shoppingCartDAO;
import Utils.ManagerUser;

/**
 * Servlet implementation class shoppingCart
 */
@WebServlet(urlPatterns = {"/Gio-hang.php" ,"/gio-hang-forward.php" , "/get-gio-hang.php","/them-gio-hang.php","/xoa-san-pham.php"})
public class shoppingCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private shoppingCartDAO dao = new shoppingCartDAO();
    public shoppingCartController() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		if (request.getCharacterEncoding() == null) {
//			request.setCharacterEncoding("UTF-8");
//		}
//		
	
		
		String urlString = request.getRequestURI();
		if(urlString.contains("gio-hang-forward.php")) {
			if(!ManagerUser.isLogin(request)) {
				
				response.sendRedirect("./");
				return;
			}
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=UTF-8");
			request.getRequestDispatcher("View/shoppingCart.jsp").forward(request, response);
		}else if (urlString.contains("get-gio-hang.php")) {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset=UTF-8");
			
			
			List<product> list = dao.renderCart(ManagerUser.getUsers());
			JSONArray array = new JSONArray();
			for (product item : list) {
				try {
					JSONObject prod = new JSONObject();
					prod.put("id", item.getId());
					prod.put("name", item.getNameString());
					prod.put("totalPrice" , item.getPrice() * item.getAmount());
					prod.put("desc", item.getDesc());
					prod.put("amount", item.getAmount());
					prod.put("originalPrice", item.getPrice());
					prod.put("imageMain" , item.getImages().split(",").clone()[0]);

					array.put(prod);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
		}
			response.getWriter().write(array.toString());
		}
		
;
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURI();
		if (request.getCharacterEncoding() == null) {
			request.setCharacterEncoding("UTF-8");
		}
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		if(!ManagerUser.isLogin(request)) {
			response.sendRedirect("./");
			return;
		}
		if(url.contains("them-gio-hang.php")) {
			if(ManagerUser.isLogin(request)) {
				String fish = request.getParameter("fishId");
				if(dao.addProductToCart(fish)) {
					response.getWriter().print("true");
				}else {
					response.getWriter().print("false");
				}
			}else {
				response.getWriter().print("false");
			}
		}else if(url.contains("xoa-san-pham.php")) {
			response.getWriter().print("c");
		}
	}

}

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


@WebServlet(urlPatterns = {"/lay-phu-kien-nuoi-ca.php"})
public class getAccessory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	productsDao dao = new productsDao();
    public getAccessory() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String categoryInt = request.getParameter("idCate");
		try {
			if(Integer.parseInt(categoryInt) <= 0 ||  Integer.parseInt(categoryInt) > 4) {
				response.sendRedirect("./");
				return;
			}
		} catch (Exception e) {
			response.sendRedirect("./");
			return;
		}
		
		List<product> list = dao.renderProducts(categoryInt);
		JSONArray array = new JSONArray();
		for (product item : list) {
			try {
				JSONObject prod = new JSONObject();
				prod.put("id", item.getId());
				prod.put("name", item.getNameString());
				prod.put("price", String.format("%,.2f", item.getPrice()).substring(0,
						String.format("%,.2f", item.getPrice()).lastIndexOf(".00")));
				prod.put("priceDefault", item.getPrice());
				prod.put("rate", item.getRate());
				prod.put("status", item.isStatus() == true ? "Còn hàng" : "Hết hàng");
				prod.put("desc", item.getDesc());
				prod.put("imageMain", item.getImages().split(",").clone()[0]);
				prod.put("video", item.getVideo());
				prod.put("category",item.getCategory());
				array.put(prod);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		response.getWriter().write(array.toString());
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

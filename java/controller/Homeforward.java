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




@WebServlet(urlPatterns = {"/Trang-Chu.php" , "/san-pham-duoc-yeu-thich.php"})
public class Homeforward extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	productsDao dao = new productsDao();
    public Homeforward() {

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String url = request.getRequestURI();
		if(url.contains("Trang-Chu.php")) {
			request.getRequestDispatcher("View/home.jsp").forward(request, response);
		}else {
			List<product> list = dao.renderProducts();
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
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

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

@WebServlet(urlPatterns = { "/accessoryCtrl", "/phu-kien-nuoi-ca.php","/get-phu-kien-nuoi-ca.php" })
public class accessoryCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	productsDao dao = new productsDao();

	public accessoryCtrl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getRequestURI();
		if(url.contains("phu-kien-nuoi-ca.php")) {
			request.getRequestDispatcher("accessory.jsp").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

}

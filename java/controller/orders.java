package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.product;
import DAO.orderDAO;
import DAO.productsDao;
import Utils.ManagerUser;


@WebServlet(urlPatterns = {"/orders","/dat-hang-ngay.php"})
public class orders extends HttpServlet {
	private static final long serialVersionUID = 1L;


	orderDAO dao = new orderDAO();
    public orders() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String quantity =request.getParameter("quantity");
		String id = request.getParameter("id");
		if(!ManagerUser.isLogin(request)) {
			response.sendRedirect("./");
			return;
		}
		if(dao.orderNow(id, quantity)) {
			request.setAttribute("agreeTurnOn", id);

			request.getRequestDispatcher("View/shoppingCart.jsp").forward(request, response);
		}else {
			response.getWriter().print("err");
//			request.getRequestDispatcher("View/page404.jsp").forward(request, response);
		}
		
	}


}

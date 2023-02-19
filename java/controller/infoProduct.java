package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.product;
import DAO.productsDao;


@WebServlet(urlPatterns = {"/thong-tin-san-pham.php","/get-thong-tin-san-pham.php"})
public class infoProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	productsDao dao = new productsDao();

    public infoProduct() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURI();
		if(url.contains("thong-tin-san-pham.php")) {
			getInfoProduct(request, response);
			request.getRequestDispatcher("View/infoProduct.jsp").forward(request, response);
		}else if(url.contains("get-thong-tin-san-pham.php")) {
			
		}

	}
private void getInfoProduct(HttpServletRequest request, HttpServletResponse response) {
	String id = request.getParameter("xnxx");
	product proc = dao.renderProductById(id);
	request.setAttribute("product", proc);
}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		getInfoProduct(request, response);
		request.getRequestDispatcher("View/infoProduct.jsp").forward(request, response);

	}

}

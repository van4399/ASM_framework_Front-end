package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.User;
import DAO.loginDAO;
import Utils.ManagerUser;


@WebServlet(urlPatterns = {"/Login" , "/dang-ki.php" , "/dang-xuat.php"})
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	loginDAO dao = new loginDAO();
    public Login() {
        super();

    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession(false).removeAttribute("sessionUserLogin");
		ManagerUser.logOut();
		response.setHeader("Cache-Control","no-cache");
		response.setHeader("Cache-Control","no-store");
		response.setHeader("Pragma","no-cache");
		response.setDateHeader ("Expires", 0);
		response.sendRedirect("./");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getCharacterEncoding() == null) {
			request.setCharacterEncoding("UTF-8");
		}
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		
		String email = request.getParameter("email").trim();
		String password = request.getParameter("password").trim();
		User u = new User();
		u.setEmail(email);
		u.setPassword(password);
		String isLogin = dao.Login(u) ;
		if(isLogin == null) {
			int last = u.getEmail().lastIndexOf("@");
			 request.getSession(true).setAttribute("sessionUserLogin", u.getEmail().substring(0, last));
			response.getWriter().print("true");
			
			response.setHeader("Cache-Control","no-cache");
			response.setHeader("Cache-Control","no-store");
			response.setHeader("Pragma","no-cache");
			response.setDateHeader ("Expires", 0);
			
		}else {
			response.getWriter().print(isLogin);
		}
		
	}

}

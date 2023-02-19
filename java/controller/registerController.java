package controller;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BEAN.MyEmail;
import BEAN.User;
import DAO.registerDAO;

@WebServlet(urlPatterns = { "/register.php", "/get-code.php","/Dang-ki-tai-khoang.php","/dang-ki-tai-khoan-google.php" })
public class registerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private registerDAO dao = new registerDAO();
	public registerController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getCharacterEncoding() == null) {
			request.setCharacterEncoding("UTF-8");
		}
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		
		String url = request.getRequestURI();
		if (url.contains("get-code.php")) {
			String to = request.getParameter("email").trim() + "@gmail.com";
			String keyString =  this.createKey();
			MyEmail email = new MyEmail(keyString);
			this.send(to, "Mã xác nhận của bạn", email.getTagHtmlCode(), email.getUser(), email.getPassword());
			response.getWriter().println("Đã gữi mã , hãy vào check email của bạn");
			User u = new User();
			u.setEmail(to);
			u.setKey(keyString);
			if (request.getSession(false).getAttribute("sessionUser")== null) {
				HttpSession sessionUser = request.getSession(true);
				sessionUser.setAttribute("sessionUser", null);
			}
			request.getSession(false).setAttribute("sessionUser", u.toString());
			
		}else if(url.contains("Dang-ki-tai-khoang.php")) {
			
			if(request.getSession(false).getAttribute("sessionUser")== null) {
				response.getWriter().print("Lấy mã xác nhận gmail đi bạn!!");
			}else {
				String password  = request.getParameter("password");
				String email = request.getParameter("email")+"@gmail.com";
				String key = request.getParameter("key");
				String[] data = request.getSession(false).getAttribute("sessionUser").toString().split(",");
				if(email.compareTo(data[0]) != 0) {
					response.getWriter().print("Gmail nhận mã khác với ban đầu");
				}else {
					if(key.compareTo(data[1]) != 0) {
						response.getWriter().print("Không đúng mã xác nhận");
					}else {
						if(dao.insert(new User(data[0] , password , null ,null),response)) {
							response.getWriter().print("true");
							request.getSession(false).removeAttribute("sessionUser");
						}else {
							response.getWriter().print("ERROR SERVER");
						}
					}
					
				}
			}
		}else if(url.contains("dang-ki-tai-khoan-google.php")) {
			String password = request.getParameter("password");
			String confirmpassword = request.getParameter("confirmpassword");
			String email = String.valueOf(request.getSession(false).getAttribute("UserEmail"));
			
			if(password.trim().compareTo(confirmpassword.trim()) == 0) {

				User user = new User();
				user.setEmail(email.trim());
				user.setPassword(password.trim());
				if(dao.insert(user,response)) {
					request.setAttribute("message", "Đăng kí thành công");
				}else {
					request.setAttribute("message", "Đăng kí thất bại , hãy thử lại sau!");
				}
			}else {
				request.setAttribute("message", "Đăng kí thất bại , hãy thử lại sau!");
			}
			request.getRequestDispatcher("View\\successPage.jsp").forward(request, response);
		}
	}


	private String createKey() {
		int b = (int)(Math.random()*(99999-10000+1)+10000);  
		return String.valueOf(b);
	}
	private void send(String to, String sub, String msg, final String user, final String pass) {
		Properties p = new Properties();
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.starttls.enable", "true");

		p.setProperty("mail.transport.protocol", "smtp");

		p.put("mail.smtp.host", "smtp.gmail.com");
		p.put("mail.starttls.port", "587");

		p.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		Session ss = Session.getInstance(p, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, pass);
			}

		});

		try {
			Message message = new MimeMessage(ss);
			message.setFrom(new InternetAddress(user));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(sub);
			message.setContent(msg, "text/html; charset=utf-8");
			Transport.send(message);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}

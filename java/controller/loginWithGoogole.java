package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.gson.Gson;
import com.google.gson.JsonObject;



import BEAN.Constants;
import BEAN.UserGoogleDto;
import DAO.registerDAO;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Form;




@WebServlet(urlPatterns = {"/loginWithGoogole"})
public class loginWithGoogole extends HttpServlet {
	private static final long serialVersionUID = 1L;
       	private registerDAO dao =new registerDAO();
    public loginWithGoogole() {
        super();

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String code = request.getParameter("code");
		String accessToken = getToken(code);
		UserGoogleDto user = getUserInfo(accessToken);
		request.getSession(true).setAttribute("UserTemporary", user);
		request.getSession(true).setAttribute("UserEmail", user.getEmail());
	}

	public static String getToken(String code) throws ClientProtocolException, IOException {
		// call api to get token
		String response = Request.Post(Constants.GOOGLE_LINK_GET_TOKEN)
				.bodyForm(Form.form().add("client_id", Constants.GOOGLE_CLIENT_ID)
						.add("client_secret", Constants.GOOGLE_CLIENT_SECRET)
						.add("redirect_uri", Constants.GOOGLE_REDIRECT_URI).add("code", code)
						.add("grant_type", Constants.GOOGLE_GRANT_TYPE).build())
				.execute().returnContent().asString();

		JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
		String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
		return accessToken;
	}

	public static UserGoogleDto getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
		String link = Constants.GOOGLE_LINK_GET_USER_INFO + accessToken;
		String response = Request.Get(link).execute().returnContent().asString();

		UserGoogleDto googlePojo = new Gson().fromJson(response, UserGoogleDto.class);

		return googlePojo;
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
		String email = String.valueOf(request.getSession(false).getAttribute("UserEmail"));
		if(dao.checkUser(email)) {
			response.getWriter().print("cc");
			request.getRequestDispatcher("View/createPasswordForClient.jsp").forward(request, response);
		}else {
			request.setAttribute("message", "Tài khoản đã đăng ký gòi");
			request.getRequestDispatcher("View\\successPage.jsp").forward(request, response);
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
		String email = String.valueOf(request.getSession(false).getAttribute("UserEmail"));
		if(dao.checkUser(email)) {
			response.getWriter().print("cc");
			request.getRequestDispatcher("View/createPasswordForClient.jsp").forward(request, response);
		}else {
			request.setAttribute("message", "Tài khoản đã đăng ký gòi");
			request.getRequestDispatcher("View\\successPage.jsp").forward(request, response);
		}
		
	}

}

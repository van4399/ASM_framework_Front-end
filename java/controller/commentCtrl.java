package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import BEAN.Comment;
import BEAN.product;
import DAO.commentDAO;
import Utils.ManagerUser;

/**
 * Servlet implementation class commentCtrl
 */
@WebServlet(urlPatterns = { "/binh-luan.php","/get-comment.php","/remove-comment.php"})
public class commentCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  commentDAO dao = new commentDAO();
    public commentCtrl() {
        super();

    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!ManagerUser.isLogin(request)) {
			response.sendRedirect("./");
			return;
		}
		String url =request.getRequestURI();
		if(url.contains("binh-luan.php")) {
			String content =request.getParameter("content");
			String date = request.getParameter("date");
			String rate = request.getParameter("star");
			String idProd = request.getParameter("idPro");
			if(dao.postComment(content, rate, date, idProd)) {
				response.getWriter().print(true);
			}else {
				response.getWriter().print(false);
			}
		}
		
		else if(url.contains("remove-comment.php")) {
			String id = request.getParameter("id");
			if(dao.removeCommentById(id)) {
				response.getWriter().print(true);
			}else {
				response.getWriter().print(false);
			}
		}
		
		else {
			response.setContentType("application/json;charset=UTF-8");
			request.setCharacterEncoding("utf-8");
			String id =request.getParameter("id");
			List<Comment> list =dao.getComment(id);
			if(list == null) {
				return;
			}
			JSONArray array = new JSONArray();
			for (Comment item : list) {
				int last = item.getEmail().lastIndexOf("@");
				try {
					JSONObject prod = new JSONObject();
					prod.put("id", item.getId());
					prod.put("email", item.getEmail().substring(0, last));
					prod.put("star", item.getStar());
					prod.put("date", item.getDate());
					prod.put("content", item.getContent());
					prod.put("emailDefault", item.getEmail());
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

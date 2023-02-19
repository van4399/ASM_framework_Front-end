package Utils;

import javax.servlet.http.HttpServletRequest;

import BEAN.User;

public class ManagerUser {
	private static User users;
	public static void setUser( User user) {
		users  = user;
	}
	public static User getUsers() {
		return users;
	}
	
	public static boolean isLogin(HttpServletRequest request) {
		if(users != null && request.getSession(false).getAttribute("sessionUserLogin") != null) {
			return true;
		}
		if(request.getSession(false).getAttribute("sessionUserLogin") != null) {
			request.getSession(false).removeAttribute("sessionUserLogin");
		}
		return false;
	}
	public static void logOut() {
		users = null;
	}

}

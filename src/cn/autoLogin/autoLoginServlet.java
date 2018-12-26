package cn.autoLogin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.C3P0.MD5Util;

/**
 * Servlet implementation class autoLoginServlet
 */
@WebServlet("/autoLoginServlet")
public class autoLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		//password = MD5Util.md5(password); //md5����
		UserService us = new UserService();
		User3 user = us.findUser(userName,password);
		if(user!=null) {
			String autologin = request.getParameter("autologin");
			Cookie cookie = new Cookie("user",user.getUserName()+"&"+user.getPassword());
			cookie.setPath("/");
			if(autologin!=null) {//���û���Ϣ���浽cookie
				cookie.setMaxAge(3600*24);
			}else {//Ҫ���cookie���������
				cookie.setMaxAge(0);
			}
			response.addCookie(cookie);//��cookie���浽�ͻ���
			request.getSession().setAttribute("user", user);
			request.getRequestDispatcher("/autohome.jsp").forward(request, response);
		}else {
			request.setAttribute("msg", "�û������������");
			request.getRequestDispatcher("/AutoLogin.jsp").forward(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package cn.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ��ס�û���¼�û�������
 */
@WebServlet("/CookieDemo3")
public class CookieDemo3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String userName = "";
		String checked = "";
		//�õ��ͻ��˱����cookie����
		Cookie[] cookies = request.getCookies();
		for (int i = 0;cookies!=null && i < cookies.length; i++) {
			if("userName".equals(cookies[i].getName())) {
				userName = cookies[i].getValue();
				checked = "checked='checked'";
			}
		}
		out.write("<form action='"+request.getContextPath()+"/CookieDemo4' method='post'>");
		out.write("�û�����<input type='text' name='userName' value='"+userName+"'/><br/>");
		out.write("���룺<input type='password' name='pwd'/><br/>");
		out.write("<input type='checkbox' name='remember' "+checked+" />��ס�û���<br/>");
		out.write("<input type='submit' value='��¼'/><br/>");
		out.write("</form>");	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

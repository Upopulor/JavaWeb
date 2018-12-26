package cn.cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ʹ��Cookie��¼�ϴε�¼��ʱ��
 */
@WebServlet("/CookieDemo1")
public class CookieDemo1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		//��ȡ�ͻ��˱����������ʱ��
		Cookie[] cookies = request.getCookies();
		for (int i = 0; (cookies!=null)&&(i < cookies.length); i++) {
			if("lastAccessTime".equals(cookies[i].getName())) {
				long l = Long.parseLong(cookies[i].getValue());
				out.write("���������ʱ��Ϊ"+new Date(l).toLocaleString());
			}
		}
		out.print("<a href='"+request.getContextPath()+"/CookieDemo2'>clear</a>");
		//����Cookie
		Cookie cookie = new Cookie("lastAccessTime",System.currentTimeMillis()+"");
		//����Cookie����Чʱ�䵥λ����
		cookie.setMaxAge(60*5);
//		cookie.setPath("/Servlet1");
//		cookie.setPath(request.getContextPath());
		cookie.setPath("/");
		//������Ϣд�ص��ͻ���
		response.addCookie(cookie);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}

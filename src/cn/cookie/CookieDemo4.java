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
 * doLOgin
 */
@WebServlet("/CookieDemo4")
public class CookieDemo4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		//��ȡ������
		String userName = request.getParameter("userName");
		String pwd = request.getParameter("pwd");
		Cookie cookie = new Cookie("userName",userName);
		String rem = request.getParameter("remember");
		//����ҵ���߼�
		//�ַ�ת��
		cookie.setPath("/");
		if("tom".equals(userName)&&"123".equals(pwd)) {
			if(rem!=null) {
				cookie.setMaxAge(Integer.MAX_VALUE);
			}else {
				cookie.setMaxAge(0);
			}
			response.addCookie(cookie);
			out.write("��¼�ɹ�");
		}else {
			out.write("��¼ʧ��");
			//����2����������µ�¼
			response.setHeader("refresh", "2;url="+request.getContextPath()+"/CookieDemo3");		
		}	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

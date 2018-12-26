package cn.wyc;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServletContext4")
public class ServletContext4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡWEB-INF�µ��ļ�
		String realPath = this.getServletContext().getRealPath("/WEB-INF/b.properties"); //����һ����б�ܿ�ͷ
		String realPath2 = this.getServletContext().getRealPath("/WEB-INF/classes/a.properties"); 		
		String realPath3 = this.getServletContext().getRealPath("/WEB-INF/classes/cn/wyc/c.properties"); 		
		
		//����һ��properties
		Properties properties = new Properties();
		properties.load(new FileInputStream(realPath));
		System.out.println(properties.getProperty("key"));
		//����һ��properties
		Properties properties2 = new Properties();
		properties2.load(new FileInputStream(realPath2));
		System.out.println(properties2.getProperty("key"));
		//����һ��properties
		Properties properties3 = new Properties();
		properties3.load(new FileInputStream(realPath3));
		System.out.println(properties3.getProperty("key"));	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

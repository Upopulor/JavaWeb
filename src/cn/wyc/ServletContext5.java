package cn.wyc;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletContext5
 */
@WebServlet("/ServletContext5")
public class ServletContext5 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("�Ѿ����ܵ���Ϣ��ת������");
		ServletContext application = this.getServletContext();
//		RequestDispatcher rd = application.getRequestDispatcher("/Servlet1/ServletContext52");
//		rd.forward(request, response);
		//���������´���
		application.getRequestDispatcher("/ServletContext52").forward(request, response);
		System.out.println("������");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

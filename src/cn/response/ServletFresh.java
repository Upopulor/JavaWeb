package cn.response;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletFresh
 */
@WebServlet("/ServletFresh")
public class ServletFresh extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		//����1��ˢ��һ��
//		response.setIntHeader("refresh", 1); 
//		Random random = new Random();
//		response.getWriter().write(random.nextInt()+"");
		response.getWriter().write("ע��ɹ���3s����ת");
		response.setHeader("refresh", "3;url=/Servlet1/Servletyanzhenma");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}

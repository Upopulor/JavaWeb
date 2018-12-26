package cn.request;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletDome4
 */
@WebServlet("/ServletDome4")
public class ServletDome4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String sky = "非表单数据";
//		System.out.println("A-我要办事");
//		System.out.println("B-我办不了，我找C给你办");
		request.setAttribute("s", sky);
//		request.getRequestDispatcher("/ServletDemo5").forward(request, response);
//		System.out.println("B-转发完毕");
		//请求包含
		request.getRequestDispatcher("/ServletDemo5").include(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}

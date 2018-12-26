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
		String sky = "�Ǳ�����";
//		System.out.println("A-��Ҫ����");
//		System.out.println("B-�Ұ첻�ˣ�����C�����");
		request.setAttribute("s", sky);
//		request.getRequestDispatcher("/ServletDemo5").forward(request, response);
//		System.out.println("B-ת�����");
		//�������
		request.getRequestDispatcher("/ServletDemo5").include(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}

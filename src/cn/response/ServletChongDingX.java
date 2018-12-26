package cn.response;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletChongDingX
 */
@WebServlet("/ServletChongDingX")
public class ServletChongDingX extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("A:need FF");
		System.out.println("Servlet:I dont have FF,but i can tellU WhoHAD");
		response.setStatus(302);//告诉客户端重定向资源
		response.setHeader("location", "/Servlet1/Servlet1");
		
		System.out.println("A: go");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

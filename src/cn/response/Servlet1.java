package cn.response;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet1
 */
@WebServlet("/Servlet1")
public class Servlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//服务器中默认的编码是ISO-8859-1，不支持中文，tomcat规定的，所以默认可能会乱码
		//所以，添加编码方式,告诉服务器使用utf-8解码
	//	response.setCharacterEncoding("UTF-8");
		//此时还可能会乱码，原因就是浏览器的编码方式不确定
		//所以告诉客户端使用什么编码
	//	response.setHeader("content-type", "text/html;charset=UTF-8");
		
		//上面两句可以使用下面一句代替
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();//得到一个字符输出流
		out.write("你好hello");//向客户端响应内容
		System.out.println("FF is Here");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

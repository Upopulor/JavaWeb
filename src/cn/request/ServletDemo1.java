package cn.request;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * getMethod(); 获得请求方式
***getRequestURL();返回客户端发出请求时的完整URL。
***getRequestURI(); 返回请求行中的资源名部分。
*****getContextPath(); 当前应用的虚拟目录 /day09_01_request
getQueryString() ; 返回请求行中的参数部分。
 */
@WebServlet("/ServletDemo1")
public class ServletDemo1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(response); //org.apache.catalina.connector.ResponseFacade@3b59dd7e
		System.out.println(request); //org.apache.catalina.connector.RequestFacade@56aa7b9b
		System.out.println(response.getClass().getName()); //org.apache.catalina.connector.ResponseFacade
		System.out.println(request.getClass().getName()); //org.apache.catalina.connector.RequestFacade
		System.out.println(request.getMethod()); //GET
		System.out.println(request.getRequestURL()); // http://localhost:8080/Servlet1/ServletDemo1
		System.out.println(request.getRequestURI()); // /Servlet1/ServletDemo1
		System.out.println(request.getContextPath()); // /Servlet1
		System.out.println(request.getQueryString()); // username=Tom
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}

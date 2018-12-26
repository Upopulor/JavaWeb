package cn.request;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * getMethod(); �������ʽ
***getRequestURL();���ؿͻ��˷�������ʱ������URL��
***getRequestURI(); �����������е���Դ�����֡�
*****getContextPath(); ��ǰӦ�õ�����Ŀ¼ /day09_01_request
getQueryString() ; �����������еĲ������֡�
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

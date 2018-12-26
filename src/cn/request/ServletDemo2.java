package cn.request;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 ** String   getHeader(String name)  ����ͷ���Ƶõ�ͷ��Ϣֵ
 Enumeration   getHeaderNames()  �õ�����ͷ��Ϣname
 Enumeration   getHeaders(String name)  ����ͷ���Ƶõ���ͬ����ͷ��Ϣֵ
 */
@WebServlet("/ServletDemo2")
public class ServletDemo2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1���������Ϣͷ����Ϣ
		String header = request.getHeader("User-Agent");
		System.out.println(header);
		if(header.toLowerCase().contains("msie")) {
			System.out.println("��ʹ�õ���IE�����");
		}else if(header.toLowerCase().contains("firefox")) {
			System.out.println("��ʹ�õ��ǻ�������");
		}else if(header.toLowerCase().contains("chrome")) {
			System.out.println("��ʹ�õ��ǹȸ������");
		}else {
			System.out.println("��ʹ�õ������������");
		}
		System.out.println("---------------");
		//2 �������������Ϣͷ��name
		Enumeration<String> headerNames = request.getHeaderNames();
		while(headerNames.hasMoreElements()) {
			String nextElement = headerNames.nextElement();
			System.out.println(nextElement+":"+request.getHeader(nextElement));
		}
		System.out.println("---------------");
		//3 ��ȡ��ͬname����Ϣͷ
		Enumeration<String> headers = request.getHeaders("accept-language");
		while(headers.hasMoreElements()) {
			String nextElement2 = headers.nextElement();
			System.out.println(nextElement2+":"+request.getHeader(nextElement2));
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

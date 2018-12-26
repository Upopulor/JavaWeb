package cn.request;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 ** String   getHeader(String name)  根据头名称得到头信息值
 Enumeration   getHeaderNames()  得到所有头信息name
 Enumeration   getHeaders(String name)  根据头名称得到相同名称头信息值
 */
@WebServlet("/ServletDemo2")
public class ServletDemo2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1获得请求消息头的信息
		String header = request.getHeader("User-Agent");
		System.out.println(header);
		if(header.toLowerCase().contains("msie")) {
			System.out.println("你使用的是IE浏览器");
		}else if(header.toLowerCase().contains("firefox")) {
			System.out.println("你使用的是火狐浏览器");
		}else if(header.toLowerCase().contains("chrome")) {
			System.out.println("你使用的是谷歌浏览器");
		}else {
			System.out.println("你使用的是其他浏览器");
		}
		System.out.println("---------------");
		//2 获得所有请求消息头的name
		Enumeration<String> headerNames = request.getHeaderNames();
		while(headerNames.hasMoreElements()) {
			String nextElement = headerNames.nextElement();
			System.out.println(nextElement+":"+request.getHeader(nextElement));
		}
		System.out.println("---------------");
		//3 获取相同name的消息头
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

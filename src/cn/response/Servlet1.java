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
		//��������Ĭ�ϵı�����ISO-8859-1����֧�����ģ�tomcat�涨�ģ�����Ĭ�Ͽ��ܻ�����
		//���ԣ���ӱ��뷽ʽ,���߷�����ʹ��utf-8����
	//	response.setCharacterEncoding("UTF-8");
		//��ʱ�����ܻ����룬ԭ�����������ı��뷽ʽ��ȷ��
		//���Ը��߿ͻ���ʹ��ʲô����
	//	response.setHeader("content-type", "text/html;charset=UTF-8");
		
		//�����������ʹ������һ�����
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();//�õ�һ���ַ������
		out.write("���hello");//��ͻ�����Ӧ����
		System.out.println("FF is Here");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

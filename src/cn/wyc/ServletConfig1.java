package cn.wyc;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
		displayName = "myServlet", //����
		name = "ServletConfig1", //servlet����
		urlPatterns = {"/demo5"}, //url
		loadOnStartup = 2, //�������ȼ�
		initParams = {@WebInitParam(name="encoding",value="GBK")}  //��ʼ������
		)
public class ServletConfig1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletConfig config;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		this.config = config;
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1ʹ�ó�ʼ�������ظ���ServletConfig���󣬴˶����ɷ���������
		//String initParameter = config.getInitParameter("encoding");
		//2ͨ���̳и���ķ����õ�ServletConfig����
		//String initParameter = this.getServletConfig().getInitParameter("encoding");
		//3
		String initParameter = super.getInitParameter("encoding");
		System.out.println(initParameter);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

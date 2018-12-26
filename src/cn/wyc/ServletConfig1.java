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
		displayName = "myServlet", //描述
		name = "ServletConfig1", //servlet名称
		urlPatterns = {"/demo5"}, //url
		loadOnStartup = 2, //启动优先级
		initParams = {@WebInitParam(name="encoding",value="GBK")}  //初始化参数
		)
public class ServletConfig1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletConfig config;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		this.config = config;
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1使用初始化方法回复到ServletConfig对象，此对象由服务器创建
		//String initParameter = config.getInitParameter("encoding");
		//2通过继承父类的方法得到ServletConfig对象
		//String initParameter = this.getServletConfig().getInitParameter("encoding");
		//3
		String initParameter = super.getInitParameter("encoding");
		System.out.println(initParameter);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

package cn.autoLogin;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebFilter(
		filterName = "test3",
		urlPatterns= {"/*"}
		//initParams = {@WebInitParam(name="encoding",value="UTF-8")}
	)
public class LoginFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//1 ת��������HttpServletRequest  HttpServletResponse
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		String requestURI = req.getRequestURI(); // /Servlet1/AutoLogin.jsp
		String contextPath = req.getContextPath(); // /Servlet1
		String path = requestURI.substring(contextPath.length()+1);
		if(!("AutoLogin.jsp".equals(path)||"autoLoginServlet".equals(path))) {
			User3 ur = (User3) req.getSession().getAttribute("user");
			//����û�û�е�¼������ִ���Զ���¼
			if(ur == null) {
	
				//2 ����ҵ��
				//�õ�cookies
				String userName = "";
				String password = "";
				Cookie[] cookies = req.getCookies();
				//���������ҵ���Ҫ��user�������Ϣ
				Cookie cookie = null;
				for (int i = 0; i < cookies.length && cookies!=null; i++) {
					if("user".equals(cookies[i].getName())) {
						String value = cookies[i].getValue();//tom&123
						String[] strings = value.split("&");
						userName = strings[0];
						password = strings[1];
					}
				}
				
				UserService us = new UserService();
				User3 user3 = us.findUser(userName, password);
				if(user3!=null) {//�����¼�ɹ����û���Ϣ�浽session
					req.getSession().setAttribute("user", user3);			
		}
		}
		}
		//3 ����
		chain.doFilter(request, response);	
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}
}

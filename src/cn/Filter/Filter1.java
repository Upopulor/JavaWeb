package cn.Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
//@WebFilter(
//		filterName = "test",
//		urlPatterns= {"/*"}
//		)
public class Filter1 implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("����ִ����");
		//����,��request��response�������´��ݣ���Ȼ�����filter������ִ��ҳ��Ķ����������body��
		chain.doFilter(request, response);
		System.out.println("���ؽ�����");
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}
}

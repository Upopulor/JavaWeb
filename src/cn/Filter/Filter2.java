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
//		filterName = "test2",
//		urlPatterns= {"/*"}
//		)
public class Filter2 implements Filter{
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub	
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("����ִ����2");
		//����,��request��response�������´��ݣ���Ȼ�����filter������ִ��ҳ��Ķ����������body��
		chain.doFilter(request, response);
		System.out.println("���ؽ�����2");	
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub		
	}
}

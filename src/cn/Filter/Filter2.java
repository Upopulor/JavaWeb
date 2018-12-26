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
		System.out.println("拦截执行了2");
		//放行,把request和response继续往下传递，不然会堵在filter，不会执行页面的东西（如标题body）
		chain.doFilter(request, response);
		System.out.println("拦截结束了2");	
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub		
	}
}

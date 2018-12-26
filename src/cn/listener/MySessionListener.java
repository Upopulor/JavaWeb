package cn.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
//关闭了--@WebListener
public class MySessionListener implements HttpSessionListener {
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		//得到application对象中的list集合
		ServletContext application = session.getServletContext();
		List<HttpSession> list = (List<HttpSession>) application.getAttribute("session");
		//得到session对象并放入到list集合中
		list.add(session);
		System.out.println("添加了"+session.getId());
	}
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
	}
}

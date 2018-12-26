package cn.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
//�ر���--@WebListener
public class MySessionListener implements HttpSessionListener {
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		//�õ�application�����е�list����
		ServletContext application = session.getServletContext();
		List<HttpSession> list = (List<HttpSession>) application.getAttribute("session");
		//�õ�session���󲢷��뵽list������
		list.add(session);
		System.out.println("�����"+session.getId());
	}
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
	}
}

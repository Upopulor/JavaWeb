package cn.listener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
//�ر���--@WebListener
public class MyServletContextListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//ͨ���¼�Դ����õ��¼�Դ��ServletContext��
		ServletContext application = sce.getServletContext();
		//����һ���������ڴ������е�session����
		List<HttpSession> list = Collections.synchronizedList(new ArrayList<HttpSession>());
		//�Ѽ��Ϸŵ�application����
		application.setAttribute("session", list);
		//����һ����ʱ������
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("��ʼɨ��");
//				for (HttpSession session : list) {
//					long l = System.currentTimeMillis()-session.getLastAccessedTime();
//					if(l>5000) {//���ʱ�����5�룬��session�����Ƴ�
//						session.invalidate();
//						list.remove(session);
//					}
//				}
				for (Iterator iterator = list.iterator(); iterator.hasNext();) {
					HttpSession session = (HttpSession) iterator.next();
					long l = System.currentTimeMillis()-session.getLastAccessedTime();
					if(l>5000) {//���ʱ�����5�룬��session�����Ƴ�
						System.out.println("�Ƴ���"+session.getId());
						session.invalidate();
						iterator.remove();
					}
				}
			}
		}, 2000, 5000);
	}
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("servletContext����������");		
	}
}

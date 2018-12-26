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
//关闭了--@WebListener
public class MyServletContextListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//通过事件源对象得到事件源（ServletContext）
		ServletContext application = sce.getServletContext();
		//创建一个集合用于储存所有的session对象
		List<HttpSession> list = Collections.synchronizedList(new ArrayList<HttpSession>());
		//把集合放到application域中
		application.setAttribute("session", list);
		//创建一个计时器对象
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("开始扫描");
//				for (HttpSession session : list) {
//					long l = System.currentTimeMillis()-session.getLastAccessedTime();
//					if(l>5000) {//如果时间大于5秒，把session销毁移出
//						session.invalidate();
//						list.remove(session);
//					}
//				}
				for (Iterator iterator = list.iterator(); iterator.hasNext();) {
					HttpSession session = (HttpSession) iterator.next();
					long l = System.currentTimeMillis()-session.getLastAccessedTime();
					if(l>5000) {//如果时间大于5秒，把session销毁移出
						System.out.println("移出了"+session.getId());
						session.invalidate();
						iterator.remove();
					}
				}
			}
		}, 2000, 5000);
	}
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("servletContext对象销毁了");		
	}
}

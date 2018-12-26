package cn.listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.annotation.WebListener;
//�ر���--@WebListener
public class MyServletRequestListenr implements ServletRequestAttributeListener {
	@Override
	public void attributeAdded(ServletRequestAttributeEvent srae) {
		System.out.println("ServletRequest���������");
	}
	@Override
	public void attributeRemoved(ServletRequestAttributeEvent srae) {
		System.out.println("ServletRequest�Ƴ�������");
	}
	@Override
	public void attributeReplaced(ServletRequestAttributeEvent srae) {
		System.out.println("ServletRequest�滻������");
		System.out.println(srae.getName()+"--"+srae.getValue());
	}
}

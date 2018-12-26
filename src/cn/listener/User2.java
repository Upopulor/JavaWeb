package cn.listener;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class User2 implements HttpSessionBindingListener{
	private String name;
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		System.out.println("User2对象被绑定了");
		
	}
	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		System.out.println("User2对象解除绑定了");	
	}	
}

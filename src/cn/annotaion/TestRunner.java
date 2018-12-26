package cn.annotaion;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestRunner {
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		//查看测试类中的方法which有@MyTest就运行WhichOne
		//得到要执行的类的Class
		Class clazz = DaoImpl.class;
		//得到当前类中的方法,包括本类及父类中的公共方法
		Method[] methods = clazz.getMethods();
		for (Method method : methods) {
			//判断当前方法上是否有@MyTest注解
			if(method.isAnnotationPresent(MyTest.class)) {
				method.invoke(clazz.newInstance(), null);
			}
		}
	}
}

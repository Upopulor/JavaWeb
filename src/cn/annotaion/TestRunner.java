package cn.annotaion;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestRunner {
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		//�鿴�������еķ���which��@MyTest������WhichOne
		//�õ�Ҫִ�е����Class
		Class clazz = DaoImpl.class;
		//�õ���ǰ���еķ���,�������༰�����еĹ�������
		Method[] methods = clazz.getMethods();
		for (Method method : methods) {
			//�жϵ�ǰ�������Ƿ���@MyTestע��
			if(method.isAnnotationPresent(MyTest.class)) {
				method.invoke(clazz.newInstance(), null);
			}
		}
	}
}

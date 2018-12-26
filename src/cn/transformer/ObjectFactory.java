package cn.transformer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ObjectFactory {
	//����һ���������
	public static AccountService getAccountService() {
		AccountService as = new AccountServiceImpl();
		AccountService proxy = (AccountService) Proxy.newProxyInstance(as.getClass().getClassLoader(), as.getClass().getInterfaces(), 
				new InvocationHandler() {
			        
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						Object invoke = null;
						try {
							ManagerThreadLocal.startTransaction();//��ʼ
							//ִ�е�����ʵ�����ת�˷���
							invoke = method.invoke(as, args);
							//int i = 10/0;//�������䣬ֻ��ִ��ǰһ�룬ÿ����һ��update�����и��µ�Connection
							//������ͳһ�������
							
							ManagerThreadLocal.commit();//�ύ����
						} catch (Exception e) {
							try {
								ManagerThreadLocal.rollback();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}//�ع�����
						}finally {
							try {
								ManagerThreadLocal.close();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
						return invoke;
					}
				});
		return proxy;
	}
}

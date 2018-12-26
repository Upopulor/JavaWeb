package cn.transformer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ObjectFactory {
	//返回一个代理对象
	public static AccountService getAccountService() {
		AccountService as = new AccountServiceImpl();
		AccountService proxy = (AccountService) Proxy.newProxyInstance(as.getClass().getClassLoader(), as.getClass().getInterfaces(), 
				new InvocationHandler() {
			        
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						Object invoke = null;
						try {
							ManagerThreadLocal.startTransaction();//开始
							//执行的是真实对象的转账方法
							invoke = method.invoke(as, args);
							//int i = 10/0;//如果有这句，只会执行前一半，每调用一个update都会有个新的Connection
							//两个不统一会出问题
							
							ManagerThreadLocal.commit();//提交事务
						} catch (Exception e) {
							try {
								ManagerThreadLocal.rollback();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}//回滚事务
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

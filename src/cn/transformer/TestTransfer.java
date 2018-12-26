package cn.transformer;

public class TestTransfer {
	public static void main(String[] args) throws Exception {
		//AccountService as = new AccountServiceImpl();
		//代理对象
		AccountService as =  ObjectFactory.getAccountService();
		//代理对象执行方法调用真实对象的方法
		as.updateAccount("aaa", "bbb", 100);
	}
}

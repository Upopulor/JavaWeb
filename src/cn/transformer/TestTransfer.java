package cn.transformer;

public class TestTransfer {
	public static void main(String[] args) throws Exception {
		//AccountService as = new AccountServiceImpl();
		//�������
		AccountService as =  ObjectFactory.getAccountService();
		//�������ִ�з���������ʵ����ķ���
		as.updateAccount("aaa", "bbb", 100);
	}
}

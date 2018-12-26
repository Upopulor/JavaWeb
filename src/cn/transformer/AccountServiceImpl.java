package cn.transformer;

public class AccountServiceImpl implements AccountService {
	@Override
	public void updateAccount(String fromUser, String toUser, double money) {
		try {
			//ad.transfer(fromUser, toUser, money);
			AccountDaoImpl ad = new AccountDaoImpl();
			//�ֱ�õ�ת����ת����˻�����
			Account fromUser1 = ad.findAccountByName(fromUser);
			Account toUser1 = ad.findAccountByName(toUser);
			//�޸��˻����ԵĽ��
			fromUser1.setMoney(fromUser1.getMoney()-money);
			toUser1.setMoney(toUser1.getMoney()+money);
			//���ת��
			ad.updateAccount(fromUser1);
			ad.updateAccount(toUser1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

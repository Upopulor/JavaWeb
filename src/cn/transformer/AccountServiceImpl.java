package cn.transformer;

public class AccountServiceImpl implements AccountService {
	@Override
	public void updateAccount(String fromUser, String toUser, double money) {
		try {
			//ad.transfer(fromUser, toUser, money);
			AccountDaoImpl ad = new AccountDaoImpl();
			//分别得到转出和转入的账户对象
			Account fromUser1 = ad.findAccountByName(fromUser);
			Account toUser1 = ad.findAccountByName(toUser);
			//修改账户各自的金额
			fromUser1.setMoney(fromUser1.getMoney()-money);
			toUser1.setMoney(toUser1.getMoney()+money);
			//完成转账
			ad.updateAccount(fromUser1);
			ad.updateAccount(toUser1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

package cn.transformer;

public interface AccountDao {
	/**
	 * ת��
	 * @param fromUser
	 * @param toUser
	 * @param money
	 */
	@Deprecated //��ע��ʱ
	public void transfer(String fromUser,String toUser,double money) throws Exception;
	/**
	 * �����˻���Ϣ�޸Ľ��
	 * @param account
	 */
	public void updateAccount(Account account) throws Exception;
	/**
	 *  �����û��������˺���Ϣ
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public Account findAccountByName(String name) throws Exception;
}

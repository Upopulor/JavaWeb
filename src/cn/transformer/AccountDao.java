package cn.transformer;

public interface AccountDao {
	/**
	 * 转账
	 * @param fromUser
	 * @param toUser
	 * @param money
	 */
	@Deprecated //标注过时
	public void transfer(String fromUser,String toUser,double money) throws Exception;
	/**
	 * 根据账户信息修改金额
	 * @param account
	 */
	public void updateAccount(Account account) throws Exception;
	/**
	 *  根据用户名查找账号信息
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public Account findAccountByName(String name) throws Exception;
}

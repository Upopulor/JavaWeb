package cn.autoLogin;

import java.sql.SQLException;

public class UserService {
	User3Dao ud = new User3Dao();
	public User3 findUser(String userName, String password) {
		try {
			return ud.findUser(userName,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}

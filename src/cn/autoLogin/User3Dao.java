package cn.autoLogin;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.C3P0.C3P0Util;

public class User3Dao {
	public User3 findUser(String userName, String password) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		return qr.query("select * from user where username=? and PASSWORD=?", new BeanHandler<User3>(User3.class),userName,password);
	}

}

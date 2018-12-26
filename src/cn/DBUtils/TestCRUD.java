package cn.DBUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import cn.C3P0.C3P0Util;


public class TestCRUD {
	@Test
	public void TestSelect() throws SQLException {
		//����һ��QueryRunner����
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		List<User> list = qr.query("select * from users", new ResultSetHandler<List<User>>() {
			//��select�����ִ�н�����᷵��һ����������˼����Բ�������ʽ����ResultSetHandler
			@Override
			public List<User> handle(ResultSet rs) throws SQLException {
				List<User> list = new ArrayList<User>();
				while(rs.next()) {
					User u  = new User();
					u.setId(rs.getInt(1));
					u.setName(rs.getString(2));
					u.setPassword(rs.getString(3));
					u.setEmail(rs.getString(4));
					u.setBirthday(rs.getDate(5));
					list.add(u);
				}
				return list;
			}	
		});
		for (User u : list) {
			System.out.println(u);
		}
	}
	@Test
	public void TestSelect2() throws SQLException {
		//����һ��QueryRunner����
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		//ִ�в�ѯ���
		List<User> list = qr.query("select * from users where id = ?", new BeanListHandler<User>(User.class),222); 
		for (User u : list) {
			System.out.println(u);
		}
		QueryRunner qr2 = new QueryRunner(C3P0Util.getDataSource());
		//ִ�в�ѯ���
		List<User> list2 = qr2.query("select * from users where id = ? and name = ?", new BeanListHandler<User>(User.class),8,"tom"); 
		for (User u1 : list2) {
			System.out.println(u1);
		}
	}
}

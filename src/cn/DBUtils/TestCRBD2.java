package cn.DBUtils;

import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;

import cn.C3P0.C3P0Util;

public class TestCRBD2 {
	@Test
	public void testInsert() throws SQLException {
		//����һ��QueryRunner����
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		qr.update("insert into users(name,password,email,birthday)values(?,?,?,?)", "huanggg","sss","c10@163.com","2012-12-21");
	}
	@Test
	public void testUpdate() throws SQLException {
		//����һ��QueryRunner����
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		qr.update("update users set name = ?,password=? where id=?","�ܽ���","mimanima",2);
	}
	@Test
	public void testDelete() throws SQLException {
		//����һ��QueryRunner����
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		qr.update("delete from users where id >= ?",225);
	}
	@Test//ֻ��ִ����ͬ�����
	public void testBatch() throws SQLException {
		//����һ��QueryRunner����
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		Object[][] params = new Object[10][];
		for(int i = 0 ; i < params.length ; i++) {
			params[i]=new Object[] {"��10"+i,"123","cads@ss.com",new Date()};
		}
		qr.batch("insert into users(name,password,email,birthday)values(?,?,?,?)", params);
	}
}

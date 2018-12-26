package cn.transformer;

import java.sql.Connection;
import java.sql.SQLException;

import cn.C3P0.C3P0Util;

public class ManagerThreadLocal {
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	//得到一个连接
	public static Connection getConnection() {
		Connection conn = tl.get();//从当前线程对象取出一个连接
		if(conn == null) {
			conn = C3P0Util.getConnection();
			tl.set(conn);//把conn放入到当前线程对象中
		}
		return conn;
	}
	//开始事务
	public static void startTransaction() {
		try {
			getConnection().setAutoCommit(false);//从当前线程取出的连接，并开始事务
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//提交事务
	public static void commit() {
		try {
			getConnection().commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//回滚事务
	public static void rollback() {
		try {
			getConnection().rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//把连接放回池中
	public static void close() {
		try {
			getConnection().close();
			tl.remove();//把当前线程对象中的conn移出
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

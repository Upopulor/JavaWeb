package cn.transformer;

import java.sql.Connection;
import java.sql.SQLException;

import cn.C3P0.C3P0Util;

public class ManagerThreadLocal {
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	//�õ�һ������
	public static Connection getConnection() {
		Connection conn = tl.get();//�ӵ�ǰ�̶߳���ȡ��һ������
		if(conn == null) {
			conn = C3P0Util.getConnection();
			tl.set(conn);//��conn���뵽��ǰ�̶߳�����
		}
		return conn;
	}
	//��ʼ����
	public static void startTransaction() {
		try {
			getConnection().setAutoCommit(false);//�ӵ�ǰ�߳�ȡ�������ӣ�����ʼ����
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//�ύ����
	public static void commit() {
		try {
			getConnection().commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//�ع�����
	public static void rollback() {
		try {
			getConnection().rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//�����ӷŻس���
	public static void close() {
		try {
			getConnection().close();
			tl.remove();//�ѵ�ǰ�̶߳����е�conn�Ƴ�
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

package cn.DBCP;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class DBCPUtil {
	private static DataSource ds = null;
	static {
		Properties prop = new Properties();//��һ��hashtable������
		try {
			/**
			 * DBCPUtil.class �õ��ֽ����ļ�
			 * .getClassLoader() ���ֽ������Ϣ��װ��һ��������ŵ��ڴ�����
			 * ���������ļ�
			 */
			prop.load(DBCPUtil.class.getClassLoader().getResourceAsStream("dbcpconfig.properties"));
			//�õ�һ������Դ
			ds = BasicDataSourceFactory.createDataSource(prop);
		} catch (Exception e) {
			throw new ExceptionInInitializerError("��ʼ���������������ļ�"); 
		}	
	}
	public static Connection getConnection() {
		try {
			return ds.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException("�޷� getConnection");
		}
	}
	//�ر���Դ�ķ���
		public static void closeAll(ResultSet rs,Statement stmt,Connection connection) {
			//6 �ر���Դ ȡ���������������û���㹻�����ӣ��Ͳ���رգ����ǷŻ�ȥ
			//  ������ӳ��㹻���ͻ�رգ�Apache�Ѿ��ڵײ������ˣ�����ֱ�ӵ���close��ok
			if(rs!=null) {
				try { //�����˿��ܻ����쳣�����Լ�trycatch
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				rs=null;
			}
			if(stmt!=null) {
				try {
					stmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				stmt = null;
			}
			if(connection!=null) {
				try {
					connection.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				connection = null;
			}
		}
}

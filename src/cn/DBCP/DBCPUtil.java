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
		Properties prop = new Properties();//是一个hashtable的子类
		try {
			/**
			 * DBCPUtil.class 得到字节码文件
			 * .getClassLoader() 把字节码的信息封装到一个对象里，放到内存里面
			 * 加载配置文件
			 */
			prop.load(DBCPUtil.class.getClassLoader().getResourceAsStream("dbcpconfig.properties"));
			//得到一个数据源
			ds = BasicDataSourceFactory.createDataSource(prop);
		} catch (Exception e) {
			throw new ExceptionInInitializerError("初始化错误，请检查配置文件"); 
		}	
	}
	public static Connection getConnection() {
		try {
			return ds.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException("无法 getConnection");
		}
	}
	//关闭资源的方法
		public static void closeAll(ResultSet rs,Statement stmt,Connection connection) {
			//6 关闭资源 取决于条件，如果是没有足够的连接，就不会关闭，而是放回去
			//  如果连接池足够，就会关闭，Apache已经在底层做好了，这里直接调用close就ok
			if(rs!=null) {
				try { //里面了可能还有异常，所以加trycatch
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

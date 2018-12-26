package cn.DBUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.KeyedHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import cn.C3P0.C3P0Util;

public class TestResultSetHandler {
	@Test//ArrayHandler:适合取1条记录。把该条记录的每列值封装到一个数组中Object[]
	public void Test1() throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		Object[] query = qr.query("select * from users where id = ?", new ArrayHandler(),5);
		for (Object object : query) {
			System.out.println(object);
		}
	}
	@Test//ArrayListHandler:适合取多条记录。把每条记录的每列值封装到一个数组中Object[]，把数组封装到一个List中
	public void Test2() throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		List<Object[]> query = qr.query("select * from users", new ArrayListHandler());
		for (Object[] objects : query) {
			for (Object object : objects) {
				System.out.println(object);
			}
			System.out.println("-----");
		}
	}
	@Test//ColumnListHandler:取某一列的数据。封装到List中。
	public void Test3() throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		//后面的 1 2 取决于前面的*。如果前面只有两个，2就是第二个。
		List<Object> query = qr.query("select * from users", new ColumnListHandler(2));
		for (Object object : query) {
			System.out.println(object);
		}
	}
	@Test//KeyedHandler:取多条记录，每一条记录封装到一个Map中，再把这个Map封装到另外一个Map中，key为指定的字段值。
	public void Test4() throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		//后面的1，就是外面大map的key...里面小map的key是列的列名是String，而外面的key是表中的某列，是Object
		Map<Object, Map<String, Object>> query = qr.query("select * from users", new KeyedHandler(1));
		for (Map.Entry<Object, Map<String,Object>> ii : query.entrySet()) {
			for (Map.Entry<String, Object> it : ii.getValue().entrySet()) {
				System.out.println(it.getKey()+"\t"+it.getValue());
			}
			System.out.println("---------");//输出无序
		}
	}
	@Test//MapHandler:适合取1条记录。把当前记录的列名和列值放到一个Map中
	public void Test5() throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		Map<String, Object> query = qr.query("select * from users where id = ?", new MapHandler(),2);
		for (Map.Entry<String, Object> qq : query.entrySet()) {
			System.out.println(qq.getKey()+"--"+qq.getValue());
		}
	}
	@Test//MapListHandler:适合取多条记录。把每条记录封装到一个Map中，再把Map封装到List中
	public void Test6() throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		List<Map<String, Object>> query = qr.query("select * from users", new MapListHandler());
		for (Map<String, Object> map : query) {
			for (Map.Entry<String, Object> m : map.entrySet()) {
				System.out.println(m.getKey()+"--"+m.getValue());
			}
			System.out.println("---");//这次输出是有序的
		}
	}
	@Test//ScalarHandler:适合取单行单列数据
	public void Test7() throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		Object query = qr.query("select count(*) from users", new ScalarHandler(1));
		System.out.println(query);
		System.out.println(query.getClass().getName());//每次的类型不一样
	}
	@Test//BeanHandler
	public void Test8() throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		User query = qr.query("select * from users where id = ?", new BeanHandler<User>(User.class),2);
		System.out.println(query);
	}
	@Test//BeanListHandler
	public void Test9() throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		List<User> list =  qr.query("select * from users where id = ?", new BeanListHandler<User>(User.class),2);
		for (User user : list) {
			System.out.println(user);
		}
	}
}

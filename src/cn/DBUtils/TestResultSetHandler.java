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
	@Test//ArrayHandler:�ʺ�ȡ1����¼���Ѹ�����¼��ÿ��ֵ��װ��һ��������Object[]
	public void Test1() throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		Object[] query = qr.query("select * from users where id = ?", new ArrayHandler(),5);
		for (Object object : query) {
			System.out.println(object);
		}
	}
	@Test//ArrayListHandler:�ʺ�ȡ������¼����ÿ����¼��ÿ��ֵ��װ��һ��������Object[]���������װ��һ��List��
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
	@Test//ColumnListHandler:ȡĳһ�е����ݡ���װ��List�С�
	public void Test3() throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		//����� 1 2 ȡ����ǰ���*�����ǰ��ֻ��������2���ǵڶ�����
		List<Object> query = qr.query("select * from users", new ColumnListHandler(2));
		for (Object object : query) {
			System.out.println(object);
		}
	}
	@Test//KeyedHandler:ȡ������¼��ÿһ����¼��װ��һ��Map�У��ٰ����Map��װ������һ��Map�У�keyΪָ�����ֶ�ֵ��
	public void Test4() throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		//�����1�����������map��key...����Сmap��key���е�������String���������key�Ǳ��е�ĳ�У���Object
		Map<Object, Map<String, Object>> query = qr.query("select * from users", new KeyedHandler(1));
		for (Map.Entry<Object, Map<String,Object>> ii : query.entrySet()) {
			for (Map.Entry<String, Object> it : ii.getValue().entrySet()) {
				System.out.println(it.getKey()+"\t"+it.getValue());
			}
			System.out.println("---------");//�������
		}
	}
	@Test//MapHandler:�ʺ�ȡ1����¼���ѵ�ǰ��¼����������ֵ�ŵ�һ��Map��
	public void Test5() throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		Map<String, Object> query = qr.query("select * from users where id = ?", new MapHandler(),2);
		for (Map.Entry<String, Object> qq : query.entrySet()) {
			System.out.println(qq.getKey()+"--"+qq.getValue());
		}
	}
	@Test//MapListHandler:�ʺ�ȡ������¼����ÿ����¼��װ��һ��Map�У��ٰ�Map��װ��List��
	public void Test6() throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		List<Map<String, Object>> query = qr.query("select * from users", new MapListHandler());
		for (Map<String, Object> map : query) {
			for (Map.Entry<String, Object> m : map.entrySet()) {
				System.out.println(m.getKey()+"--"+m.getValue());
			}
			System.out.println("---");//�������������
		}
	}
	@Test//ScalarHandler:�ʺ�ȡ���е�������
	public void Test7() throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		Object query = qr.query("select count(*) from users", new ScalarHandler(1));
		System.out.println(query);
		System.out.println(query.getClass().getName());//ÿ�ε����Ͳ�һ��
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

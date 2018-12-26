package cn.annotaion;

public class DaoImpl {
	@MyTest
	public void testAdd() {
		System.out.println("add方法执行了");
	}
	@MyTest
	public void testUpdate() {
		System.out.println("Update方法执行了");
	}
}

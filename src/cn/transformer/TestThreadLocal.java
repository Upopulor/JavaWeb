package cn.transformer;

public class TestThreadLocal {
	public static void main(String[] args) {
		ThreadLocal tl = new ThreadLocal();
		tl.set("oo");
		MyThread myThread = new MyThread(tl);
		myThread.start();
		System.out.println(tl.get()+"Ö÷Ïß³Ì");
	}
}

package cn.transformer;

public class MyThread extends Thread{
	private ThreadLocal tl;
	public MyThread(ThreadLocal tl) {
		this.tl = tl;
	}
	@Override
	public void run() {
		System.out.println(tl.get()+"非主线程的get");
	}
	
}

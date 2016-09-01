package com.atguigu.multiThread;
/**
 * 2 创建两个子线程，让其中一个输出1-100之间的偶数，另一个输出1-100之间的奇数。
 * @author SYQ
 *
 */
public class Test02 {

	public static void main(String[] args) {
		Thread01 thread01 = new Thread01();
		Thread02 thread02 = new Thread02();
		Thread thread1 = new Thread(thread01);
		Thread thread2 = new Thread(thread02);
		thread1.start();
		thread2.start();
	}

}

class Thread01 implements Runnable{

	@Override
	public void run() {
		for(int i=1; i<=100; i++){
			if(i%2 == 0){
				System.out.println("Thread01 print double:"+i);
			}
		}
	}
	
}

class Thread02 implements Runnable{

	@Override
	public void run() {
		for(int i=1;i <= 100;i++){
			if(i%2 != 0){
				System.out.println("Thread02 print single:"+i);
			}
		}
	}
	
}
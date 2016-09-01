package com.atguigu.multiThread;
/**
 * 1 创建一个子线程，在线程中输出1-100之间的偶数，主线程输出1-100之间的奇数。
 * @author SYQ
 *
 */
public class Test01 implements Runnable{
	
	public static void main(String[] args) {
		Test01 t1 = new Test01();
		Thread thread = new Thread(t1);
		thread.start();
		for(int i = 1; i<=100; i++){
			if(i%2!=0){
				System.out.println("main thread:"+i);
			}
		}
		
	}

	@Override
	public void run() {
		for(int i =1; i<=100; i++){
			if(i%2==0){
				System.out.println("subThread:"+i);
			}
		}
	}
}

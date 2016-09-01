package com.atguigu.multiThread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * 另一种实现线程锁定的方法Lock。
 * @author SYQ
 *
 */
public class Test05_senior_Lock {

	public static void main(String[] args) {
		final Ticket ticket = new Ticket();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=0;i<40;i++){
					ticket.sale();
				}
			}
		}, "AA").start();
		
		new Thread(new Runnable() {
		
			@Override
			public void run() {
				for(int i=0;i<40;i++){
					ticket.sale();
				}
			}
		}, "BB").start();

		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=0;i<40;i++){
					ticket.sale();
				}
			}
		}, "CC").start();
	}

}

class Ticket {
	private int ticketNum = 30;
	Lock lock = new ReentrantLock();
	public void sale(){
		lock.lock();
		try {
			if(ticketNum > 0){
				Thread.sleep(300);
				System.out.println(Thread.currentThread().getName()+"卖出了第"+ticketNum--+"张票，还剩"+ticketNum+"张票。");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			lock.unlock();
		}
	}
}

package com.atguigu.multiThread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多个线程，按顺序调用，就跟呼啦圈一样，A->B->C->A....
 * @author SYQ
 * 备注：多线程之间按顺序调用，实现A-B-C
 * 三个线程启动，要求如下：
 * 
 * AA打印5次，BB打印10次，CC打印15次
 * 接着
 * AA打印5次，BB打印10次，CC打印15次
 * 共计来20轮
 */
public class Test10_senior_ThreadSequence {

	public static void main(String[] args) {
		final DataSource10 ds = new DataSource10();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=0;i<10;i++){
					ds.syso();
				}
			}
		}, "AA").start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=0;i<10;i++){
					ds.incr();
				}
			}
		},"BB").start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=0;i<10;i++){
					ds.decr();
				}
			}
		}, "CC").start();
	}

}

class DataSource10{
	private int num = 1;
	Lock lock = new ReentrantLock();
	Condition condition1 = lock.newCondition();
	Condition condition2 = lock.newCondition();
	Condition condition3 = lock.newCondition();
	
	public void incr(){
		lock.lock();
		try {
			while(num != 2){
				condition2.await();
			}
			for(int i=0;i<10;i++){
				System.out.println(Thread.currentThread().getName()+"---->"+num);
			}
			num = 3;
			condition3.signal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			lock.unlock();
		}
	}
	
	public void decr(){
		lock.lock();
		try {
			while(num != 3){
				condition3.await();
			}
			for(int i=0;i<15;i++){
				System.out.println(Thread.currentThread().getName()+"---->"+num);
			}
			num = 1;
			condition1.signal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			lock.unlock();
		}
	}
	
	public void syso(){
		lock.lock();
		try {
			while(num != 1){
				condition1.await();
			}
			for(int i=0;i<5;i++){
				System.out.println(Thread.currentThread().getName()+"------"+num);
			}
			num = 2;
			condition2.signal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			lock.unlock();
		}
	}
}
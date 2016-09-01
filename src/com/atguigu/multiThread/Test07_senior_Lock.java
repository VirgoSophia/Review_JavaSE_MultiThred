package com.atguigu.multiThread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目：现在两个线程，可以操作同一个变量，实现一个线程对该变量加1，一个线程对该变量减1，
 * 实现交替，来5轮，变量初始值为零。
 * @author SYQ
 * 第二种，lock + condition + await + signalall 组合。
 */
public class Test07_senior_Lock {

	public static void main(String[] args) {
		final DataSource2 ds2 = new DataSource2();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=0;i<50;i++){
					
					ds2.incrNum();
				}
			}
		}, "AA").start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=0;i<50;i++){
					
					ds2.decrNum();
				}
			}
		}, "BB").start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=0;i<50;i++){
					
					ds2.incrNum();
				}
			}
		}, "CC").start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=0;i<50;i++){
						
					ds2.decrNum();
				}
			}
		},"DD").start();
	}

}

class DataSource2 {
	private int num = 0;
	Lock lock = new ReentrantLock();
	Condition condition = lock.newCondition();
	
	public void incrNum(){
		lock.lock();
		try {
			while(num != 0){
				condition.await();
			}
			num++;
			System.out.println(Thread.currentThread().getName()+"\t"+num);
			condition.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			lock.unlock();
		}
	}
	
	public void decrNum(){
		lock.lock();
		try {
			while(num == 0){
				condition.await();
			}
			num--;
			System.out.println(Thread.currentThread().getName()+"\t"+num);
			condition.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			lock.unlock();
		}
	}
}
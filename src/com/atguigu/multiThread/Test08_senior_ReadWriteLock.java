package com.atguigu.multiThread;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/**
 * 一个线程写，100个线程可读，尝试构建该类。
 * @author SYQ
 *
 */
public class Test08_senior_ReadWriteLock {
	public static void main(String[] args) {
		final MyQueue myQueue = new MyQueue();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				myQueue.set(new Random().nextInt(100));	
			}
		}, "AA").start();
		
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for(int i =0; i<100;i++){
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					myQueue.get();
				}
			}, String.valueOf(i)).start();
		}
	}
}

class MyQueue{
	private Object obj;
	ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	
	//读
	public void get(){
		readWriteLock.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName()+"正在读取\t"+obj);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			readWriteLock.readLock().unlock();
		}
	}
	
	//写
	public void set(Object obj){
		readWriteLock.writeLock().lock();
		try {
			this.obj = obj;
			System.out.println(Thread.currentThread().getName()+"正在写入\t"+obj);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			readWriteLock.writeLock().unlock();
		}
	}
}
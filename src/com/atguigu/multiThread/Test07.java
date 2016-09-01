package com.atguigu.multiThread;
/**
 * 题目：现在两个线程，可以操作同一个变量，实现一个线程对该变量加1，一个线程对该变量减1，
 * 实现交替，来5轮，变量初始值为零。
 * @author SYQ
 * 第一种，最常见的synchronized + wait + notifyall 组合
 */
public class Test07 {

	public static void main(String[] args) {
		final DataSource dataSource = new DataSource();
		
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						for(int i=0;i<10;i++){
							dataSource.incrNum();
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}, "AA").start();
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						for(int i=0;i<10;i++){
							
							dataSource.decrNum();
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}, "BB").start();
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						for(int i=0;i<10;i++){
							
							dataSource.incrNum();
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}, "CC").start();
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						for(int i=0;i<10;i++){
							
							dataSource.decrNum();
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}, "DD").start();
		}

}

class DataSource {
	private int num = 0;
	
	public synchronized void incrNum() throws InterruptedException{
		while(num != 0){
			this.wait();
		}
		num++;
		System.out.println(Thread.currentThread().getName()+"\t"+num);
		notifyAll();
	}
	
	public synchronized void decrNum() throws InterruptedException{
		while(num != 1){
			this.wait();
		}
		num--;
		System.out.println(Thread.currentThread().getName()+"\t"+num);
		notifyAll();
	}
}
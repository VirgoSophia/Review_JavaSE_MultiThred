package com.atguigu.multiThread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
/**
 * 除了继承Thread类和实现Runnable接口之外，第三种实现多线程的方法：
 * 实现java.util.concurrent包下的Callable接口。
 * 如何把Callable接口的子类传给Thread的构造方法？
 * 利用java.util.concurrent包下的FutureTask类，这个类可以包装Runnable和Callable接口。
 * @author SYQ
 *
 */
public class Test06_senior_Callable {

	public static void main(String[] args) {
		
		FutureTask<Object> ft = new FutureTask<>(new Thread61());
		new Thread(ft, "AA").start();
	}

}

class Thread61 implements Callable<Object>{

	@Override
	public Object call() throws Exception {
		System.out.println("--------->call");
		return "hahaha";
	}
	
}
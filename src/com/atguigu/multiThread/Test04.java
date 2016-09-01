package com.atguigu.multiThread;
/**
 * 1.编写程序，在main方法中创建一个线程。线程每隔一定时间（200ms以内的随机时间）产生一个0-100之间的随机整数，打印后将该整数放到集合中；
 * 2.共产生100个整数，全部产生后，睡眠30秒，然后将集合内容打印输出；
 * 3.在main线程中，唤醒上述睡眠的线程，使其尽快打印集合内容
 * @author SYQ
 *
 */
public class Test04 {

	public static void main(String[] args) {

	}

}
class thread41 implements Runnable{

	@Override
	public void run() {
		
	}
	
}
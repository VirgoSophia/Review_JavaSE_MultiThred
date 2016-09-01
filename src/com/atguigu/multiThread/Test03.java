package com.atguigu.multiThread;

import java.util.Scanner;

public class Test03 {
	
	public static void main(String[] args) throws InterruptedException {
		Scanner sc = new Scanner(System.in);
		Thread31 thread31 = new Thread31();
		Thread thread1 = new Thread(thread31);
		thread1.start();
		
		String nextLine = sc.nextLine();
		char charAt = nextLine.charAt(0);
		while(true){
			if(charAt == 'q'){
				thread31.setFlag(false);
				break;
			}
			nextLine = sc.nextLine();
			charAt = nextLine.charAt(0);
		}
		sc.close();
	}
}

class Thread31 implements Runnable {

	public boolean flag = true;

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public Thread31() {
		super();
	}

	@Override
	public void run() {
		@SuppressWarnings("unused")
		Thread thread = Thread.currentThread();
		while(flag){
			int rndNum = (int)(Math.random()*101);
			System.out.println(rndNum);
		}
	}
}

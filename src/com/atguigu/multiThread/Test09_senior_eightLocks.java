package com.atguigu.multiThread;
/**
 * 阳哥讲的线程八锁。
 */
public class Test09_senior_eightLocks {

	public static void main(String[] args) {
		final Phone phone = new Phone();
		final Phone phone2 = new Phone();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				phone.getIOS();
			}
		}, "AA").start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				phone.getAndroid();
//				phone.getHello();
			}
		}, "BB").start();
	}

}

/**
 * 一：两个线程访问Phone，请说出先打印IOS还是Android？
 * @author SYQ
 * 结论：先IOS后Android。
 * 原因：main方法中getIOS方法写在getAndroid方法前面。
 */
/*class Phone {
	public synchronized void getIOS(){
		System.out.println("-------->IOS");
	}
	public synchronized void getAndroid(){
		System.out.println("-------->Android");
	}
}*/

/**
 * 二：给getIOS方法加入sleep方法，问先打印IOS还是Android？
 * @author SYQ
 * 结论：先IOS后Android。
 * 原因：一个对象里面如果有多个对象是synchronized方法，只要某一时间有一个线程去访问了其中一个synchronized方法，
 * 		那么其他所有线程都必须等待。
 */
/*class Phone {
	public synchronized void getIOS(){
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("-------->IOS");
	}
	public synchronized void getAndroid(){
		System.out.println("-------->Android");
	}
}*/

/**
 * 三：增加Hello方法，先打印IOS方法还是Hello？
 * @author SYQ
 * 结论：先Hello后IOS。
 * 原因：加入的普通方法和同步锁半毛钱关系都没有。
 */
/*class Phone {
	public synchronized void getIOS(){
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("-------->IOS");
	}
	public synchronized void getAndroid(){
		System.out.println("-------->Android");
	}
	public void getHello(){
		System.out.println("-------->Hello");
	}
}*/

/**
 * 四：有两部手机，先打印IOS还是Android？
 * @author SYQ
 * 结论：先Android后IOS。
 * 原因：两个对象，你锁你的，我锁我的，没有关系。IOS睡着呢...
 */
//class Phone {
//	public synchronized void getIOS(){
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		System.out.println("-------->IOS");
//	}
//	public synchronized void getAndroid(){
//		System.out.println("-------->Android");
//	}
//}

/**
 * 五：都改为静态同步方法，只有一部手机，请问先打印IOS还是Android？
 * @author SYQ
 * 结论：先IOS后Android。
 * 原因：所有的静态同步方法用的也是同一把锁——类对象本身，一旦一个静态同步方法获取锁后，
 * 		其他的静态同步方法都必须等待该方法释放锁后才能获取锁，而不管是同一个实例对象的静态同步方法之间，
 * 		还是不同的实例对象的静态同步方法之间，只要它们是同一个类的实例对象！
 */
/*class Phone {
	public static synchronized void getIOS(){
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("-------->IOS");
	}
	public static synchronized void getAndroid(){
		System.out.println("-------->Android");
	}
}*/

/**
 * 六：两个静态同步方法，有两部手机，问先打印IOS还是Android？
 * @author SYQ
 * 结论：先IOS后Android。
 * 原因：所有的静态同步方法用的也是同一把锁——类对象本身，一旦一个静态同步方法获取锁后，
 * 		其他的静态同步方法都必须等待该方法释放锁后才能获取锁，而不管是同一个实例对象的静态同步方法之间，
 * 		还是不同的实例对象的静态同步方法之间，只要它们是同一个类的实例对象！
 */
/*class Phone {
	public static synchronized void getIOS(){
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("-------->IOS");
	}
	public static synchronized void getAndroid(){
		System.out.println("-------->Android");
	}
}*/

/**
 * 七：一个静态同步方法，一个普通同步方法，只有一个对象，问先打印IOS还是Android？
 * @author SYQ
 * 结论：先Android后IOS。
 * 原因：所有的非静态同步方法用的都是同一把锁——实例对象本身，也就是说如果一个实例对象的非静态同步方法获取锁后，
 * 		该实例对象的其他非静态同步方法必须等待获取锁的方法释放锁后才能获取锁。
 * 		可是别的实例对象的非静态同步方法因为跟该实例对象的非静态同步方法用的是不同的锁，
 * 		所以毋须等待该实例对象已获取锁的非静态同步方法释放锁就可以获取他们自己的锁。
 * 
 *     所有的静态同步方法用的也是同一把锁——类对象本身，这两把锁是两个不同的对象，
 *     所以静态同步方法与非静态同步方法之间是不会有竞态条件的。
 */
/*class Phone {
	public static synchronized void getIOS(){
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("-------->IOS");
	}
	public synchronized void getAndroid(){
		System.out.println("-------->Android");
	}
}*/

/**
 * 八：一个静态同步方法，一个普通同步方法，有两个对象，问先打印IOS还是Android？
 * @author SYQ
 * 结论：先Android后IOS。
 * 原因：俩对象，俩种锁，所以没关系，IOS睡着，就先打印Android。
 */
class Phone {
	public static synchronized void getIOS(){
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("-------->IOS");
	}
	public synchronized void getAndroid(){
		System.out.println("-------->Android");
	}
}

















































package com.pandora.basic.thread.basis;

import java.util.Date;

/**
 * 使用runnable 接口创建多线程
 * @author Administrator
 *
 */
public class CreateThreadImplements implements Runnable{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CreateThreadImplements tt = new CreateThreadImplements();
		tt.test1();
	}

	//测试随机sleep时间 线程是否正常关闭
	public void test1(){
		CreateThreadImplements tt = new CreateThreadImplements();
		for(int i = 0 ; i<10 ; i++){
			Thread t = new Thread(tt,i+"");
			t.start();
		}
	}

	public void run() {
		Date date = new Date();
		String str = "";
		synchronized (str) {
			for(int i = 0 ; i<300 ; i++){
				System.out.print(Thread.currentThread().getName() + "  ");
			}
			System.out.println();
		}
	}
}

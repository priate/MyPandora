package com.pandora.thread.basis;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 最简单的创建多线程
 * 第一种方式 继承
 * @author Administrator
 *
 */
public class CreateThreadExtends extends Thread{
	
	public static void main(String[] args) {
		CreateThreadExtends threadTest = new CreateThreadExtends();
		threadTest.oneThread();
		threadTest.twoThread();
		try {
			threadTest.threeThread();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 继承Thread方法 不推荐
	 */
	public void oneThread(){
		Thread t = new Thread(){
			public void run(){
				System.out.println("Thread 1");
			}
		};
		t.start();
	}
	
	/**
	 * 实现Runnable接口 执行run方法
	 */
	public void twoThread(){
		Thread t = new Thread(new Runnable() {
			
			public void run() {
				System.out.println("Thread 2");
			}
		});
		t.run();
	}
	
	/**
	 * 实现Runnable升级版 可以拥有返回值
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public void threeThread() throws InterruptedException, ExecutionException{
		FutureTask<String> ft = new FutureTask<String>(new Callable<String>() {

			public String call() throws Exception {
				System.out.println("new Thread3");
				return "Call";
			}
		});
		
		Thread t = new Thread(ft);
		t.start();
		String result = ft.get();
		System.out.println(result);
	}
	
}

package com.pandora.basic.thread.basis;

public class WorkerThread implements Runnable {

	private String command ;
	
	public WorkerThread(String command) {
		this.command = command;
	}
	
	@Override
	public void run() {
		try {
			System.out.println(Thread.currentThread().getName()+" Start. Command = "+command);
			Thread.sleep((long)Math.random()*10000);
			System.out.println(Thread.currentThread().getName()+" End ");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

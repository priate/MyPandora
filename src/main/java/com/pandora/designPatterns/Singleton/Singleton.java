package com.pandora.designPatterns.Singleton;

/**
 * 单例模式 目前采用的勤快的方式 主动进行加载
 * @author C.H
 * Create Date: 2016-1-20 上午2:26:29
 */
public class Singleton {
	
	public final static Singleton getInstance = new Singleton();
	private Singleton(){super();}
	
	private int index = 0 ;
	public int getIndex(int num){
		return index+=num;
	}
}

/**
 * 单例模式 懒加载模式
 * @author C.H
 * Create Date: 2016-1-20 上午2:26:29
 */
class SingletonLazy {
	
	private static SingletonLazy singletonLazy;
	private SingletonLazy(){super();}
	
	public static SingletonLazy getInstance(){
		if(singletonLazy==null)
			singletonLazy = new SingletonLazy();
		return singletonLazy;
	}
	
	private int index = 0 ;
	public int getIndex(int num){
		return index+=num;
	}
}

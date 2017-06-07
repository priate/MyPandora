package com.pandora.basic.jdk8.newFeatures;

import java.util.Arrays;
import java.util.List;

public class TryLambda {

	static String sign = ",";
	
	static List<String> testList = Arrays.asList("1","10","2","21","1111");
	
	public static void main(String[] args) {
		sort();
//		easyTry(); 
	}
	
	public static void sort(){
		testList.forEach( e -> System.out.print(e + ","));
		System.out.println();
		testList.sort((a, b) -> a.compareTo(b));
		testList.forEach( e -> System.out.print(e + ","));
		System.out.println("\n");
		

		testList.forEach( e -> System.out.print(e + ","));
		System.out.println();
		testList.sort((a, b) -> {
			return Integer.parseInt(a) -Integer.parseInt(b) ;
//			return a.compareTo(b)*-1;
		});
		testList.forEach( e -> System.out.print(e + ","));
		System.out.println();
		
	}
	
	/**
	 * 最简单的使用方式
	 */
	public static void easyTry(){
		//最简单的方式
		testList.forEach( e -> System.out.print(e));
		System.out.println();
		
		//可以指定类型
		testList.forEach( (String e) -> System.out.print(e));
		System.out.println();
		
		//这货还可以引用成员变量和局部变量 但是 如果这些变量不是final的话 会被强转为final 
		//这里有个坑 就是 lambda内报错 不会往外抛
		testList.forEach( (String e) -> {
			try {
				System.out.print(Integer.parseInt(e) + sign);
//				coefficient = 10;
			} catch (Exception e1) {
				System.err.print(e + sign);
			}
		});
		
	}
}

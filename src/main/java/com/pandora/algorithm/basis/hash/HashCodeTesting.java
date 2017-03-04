package com.pandora.algorithm.basis.hash;

import java.util.HashMap;
import java.util.Objects;

public class HashCodeTesting {
	static HashMap map = new HashMap();
	
	private static char startChar = 'A';
	private static char endChar = 'z';
	
	private static int offset = endChar - startChar + 1;
	
	private static long pow = 0 ;
	private static long now = 0 ;
	
	private static int dup = 0;
	private static long time = System.currentTimeMillis();

	public static void main(String[] args) {
		int len = 4;
		char[] chars = new char[len];
		pow = (long) Math.pow(offset, len);
		
		System.out.println("预期对比值总量：\t\t" + pow);
		tryBit(chars, len);
		System.out.println("对比值总量：\t" + pow);
		System.out.println("有重复数量：\t" + (dup));
		System.out.println("无重复数量：\t" + (pow-dup));
	}
	
	private static void easyHashcode(){
		System.out.println(Objects.hashCode("BBAA"));
		System.out.println(Objects.hashCode("AaAA"));
	}

	private static void tryBit(char[] chars, int i) {
		for (char j = startChar; j <= endChar; j++) {
			chars[i - 1] = j;
			if (i > 1)
				tryBit(chars, i - 1);
			else
				test(chars);
		}
	}

	private static void test(char[] chars) {
		
		String str = new String(chars).replaceAll("[^a-zA-Z_]", "").toUpperCase();// 195112:0
//		String str = new String(chars).toLowerCase();//195112:6612
//		String str = new String(chars).toUpperCase();//195112:6612
//		String str = new String(chars).replaceAll("[^a-zA-Z0-9:-_]","");//195112:122500
//		String str = new String(chars);// 195112:138510
		int hash = Objects.hashCode(str);
		now++;
		if (map.containsKey(hash)) {
			String s = (String) map.get(hash);
			if (!s.equals(str)) {
				if( dup++ %1000000 == 11 ){
					long nowTime = System.currentTimeMillis()-time;
					System.out.print("now ["+now+"] Consume Time["+nowTime+"] Repetitions["+dup+"] --> ");
					System.out.println(s+":"+str);
				}
			}
		} else {
			map.put(hash, str);
			// System.out.println(str);
		}
	}
}

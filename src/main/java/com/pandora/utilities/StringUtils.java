package com.pandora.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	/**
	 * 将InputStream类型转换为String类型 按行读取方式
	 * @param inputStream
	 * @return
	 */
	public static String InputStreamToString(InputStream inputStream){
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		StringBuilder sb = new StringBuilder();
		String line = null ;
		try {
			while((line = reader.readLine()) != null){
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	/**
	 * 使用正则进行匹配值
	 * @param str	需要匹配的值
	 * @param regex	正则表达式
	 * @param index	group的序号 取全部用0
	 * @return
	 */
	public static String getStringByRegex(String str, String regex, int index){
		try {
			Matcher m = Pattern.compile(regex).matcher(str);
			if(m.find()){
				if(index == 0){
					return m.group();
				}
				return m.group(index);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(str + " : " + regex);
		}
		return "";
	}
	
	/**
	 * 获取批量数据
	 * @param str	需要匹配的值
	 * @param regex	正则表达式
	 * @param index	group的序号 取全部用0
	 * @return
	 */
	public static List<String> getStringsByRegex(String str, String regex, int index){
		List<String> list = new ArrayList<String>();
		try {
			Matcher m = Pattern.compile(regex).matcher(str);
			while(m.find()){
				if(index==0)
					list.add(m.group());
				else
					list.add(m.group(index));
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(str + " : " + regex);
		}
		return list;
	}

	/**
	 * 判断字符串是否含有中文 Yes, Return Ture, other Return false
	 * @param content
	 * @return
	 */
	public static boolean judgeIsChinese(String content){
		Matcher matcherLanguage = Pattern.compile("[\u4e00-\u9fa5]").matcher(content);
		if(matcherLanguage.find())
			return true;
		return false;
	}
}

package com.test.jsoup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pandora.tools.operation.OperationFile;

public class MvFile {

	public static void main(String[] args) {
//		Map<String, String> filterMap = new HashMap<String, String>();
//		filterMap.put("x", "小视屏");
//		filterMap.put("X", "小视屏");
		
		String readPath = "I:/BT/电影2/";
		List<String> allFilesByPath = OperationFile.getFilesByPath(readPath, false);
		for (String string : allFilesByPath) {
			
			String path = string;
			string = string.substring(string.lastIndexOf("/")+1, string.length());
			String[] split = string.split(" - ");
			try {
				System.out.println(split[0]+" : " + split[1]);
				OperationFile.mv(path, readPath+"Temp/"+split[0] +"/");
			} catch (Exception e) {
				System.err.println(string);
			}
		}
	}
}

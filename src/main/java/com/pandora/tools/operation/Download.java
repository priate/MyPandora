package com.pandora.tools.operation;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;

public class Download {
	static final String downloadURL = "F:\\Work\\Olympics\\";
	static final String encoding = new String(Base64.encodeBase64("kuyun:k&y&n1738".getBytes()));

	public void downLoadZip(String webPage, String saveFile) {
		long time = System.currentTimeMillis();
		int bytesum = 0;
		int byteread = 0;

		InputStream inStream = null;
		FileOutputStream fs = null;
		try {
			URL url = new URL(webPage);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(50000);
			conn.setRequestProperty("Authorization", "Basic " + encoding);
			conn.setRequestMethod("GET");
			
			inStream = conn.getInputStream();
			fs = new FileOutputStream(saveFile);
			byte[] buffer = new byte[4028];
			while ((byteread = inStream.read(buffer)) != -1) {
				bytesum += byteread;
				fs.write(buffer, 0, byteread);
			}
			System.out.println("文件["+webPage+"]下载成功  bytesum["+bytesum+"] 耗时["+(System.currentTimeMillis()-time)+"]");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (inStream != null) {
					inStream.close();
				}
			} catch (IOException e) {
				inStream = null;
			}
			try {
				if (fs != null) {
					fs.close();
				}
			} catch (IOException e) {
				fs = null;
			}
		}
	}
	
	public static void main(String[] args) {
//		String fileName = "olympics_20160806_2145.zip"; 
		String webPage = "http://downloads.stats.com/kuyun_archive/";
		
		for(String fileName : fileList())
			new Download().downLoadZip(webPage + fileName, downloadURL + fileName);
	}
	
	public static String[] fileList(){
		return new String []{
//				"olympics_20160805_0004.zip",
//				"olympics_20160805_0017.zip",
//				"olympics_20160805_0032.zip",
//				"olympics_20160805_0048.zip",
//				"olympics_20160805_0103.zip",
//				"olympics_20160805_0434.zip",
//				"olympics_20160805_0447.zip",
//				"olympics_20160805_0503.zip",
//				"olympics_20160805_0532.zip",
//				"olympics_20160805_0547.zip",
//				"olympics_20160805_0603.zip",
//				"olympics_20160805_0617.zip",
//				"olympics_20160805_0632.zip",
//				"olympics_20160805_0647.zip",
//				"olympics_20160805_0703.zip",
//				"olympics_20160805_0718.zip",
//				"olympics_20160805_0732.zip",
//				"olympics_20160805_0747.zip",
//				"olympics_20160805_0803.zip",
//				"olympics_20160805_0817.zip",
//				"olympics_20160805_0833.zip",
//				"olympics_20160805_0848.zip",
//				"olympics_20160805_0903.zip",
//				"olympics_20160805_0917.zip",
//				"olympics_20160805_0932.zip",
//				"olympics_20160805_0947.zip",
//				"olympics_20160805_1004.zip",
//				"olympics_20160805_1017.zip",
//				"olympics_20160805_1032.zip",
//				"olympics_20160805_1047.zip",
//				"olympics_20160805_1104.zip",
//				"olympics_20160805_1117.zip",
//				"olympics_20160805_1132.zip",
//				"olympics_20160805_1147.zip",
//				"olympics_20160805_1203.zip",
//				"olympics_20160805_1217.zip",
//				"olympics_20160805_1232.zip",
//				"olympics_20160805_1247.zip",
//				"olympics_20160805_1302.zip",
//				"olympics_20160805_1318.zip",
//				"olympics_20160805_1332.zip",
//				"olympics_20160805_1348.zip",
//				"olympics_20160805_1404.zip",
//				"olympics_20160805_1417.zip",
//				"olympics_20160805_1433.zip",
//				"olympics_20160805_1447.zip",
//				"olympics_20160805_1503.zip",
//				"olympics_20160805_1934.zip",
//				"olympics_20160805_1957.zip",
//				"olympics_20160805_2019.zip",
//				"olympics_20160805_2032.zip",
//				"olympics_20160805_2047.zip",
//				"olympics_20160805_2103.zip",
//				"olympics_20160805_2118.zip",
//				"olympics_20160805_2132.zip",
//				"olympics_20160805_2147.zip",
//				"olympics_20160805_2204.zip",
//				"olympics_20160805_2217.zip",
//				"olympics_20160805_2233.zip",
//				"olympics_20160805_2248.zip",
//				"olympics_20160805_2304.zip",
//				"olympics_20160805_2318.zip",
//				"olympics_20160806_0006.zip",
//				"olympics_20160806_0017.zip",
//				"olympics_20160806_0102.zip",
//				"olympics_20160806_0404.zip",
//				"olympics_20160806_0503.zip",
//				"olympics_20160806_0517.zip",
//				"olympics_20160806_0532.zip",
//				"olympics_20160806_0547.zip",
//				"olympics_20160806_0604.zip",
//				"olympics_20160806_0617.zip",
//				"olympics_20160806_0634.zip",
//				"olympics_20160806_0648.zip",
//				"olympics_20160806_0706.zip",
//				"olympics_20160806_0724.zip",
//				"olympics_20160806_0750.zip",
//				"olympics_20160806_0857.zip",
//				"olympics_20160806_1123.zip",
//				"olympics_20160806_1239.zip",
//				"olympics_20160806_1315.zip",
//				"olympics_20160806_1330.zip",
//				"olympics_20160806_1345.zip",
//				"olympics_20160806_1400.zip",
//				"olympics_20160806_1415.zip",
//				"olympics_20160806_1430.zip",
//				"olympics_20160806_1445.zip",
//				"olympics_20160806_1500.zip",
//				"olympics_20160806_1515.zip",
//				"olympics_20160806_1530.zip",
//				"olympics_20160806_1545.zip",
//				"olympics_20160806_1600.zip",
//				"olympics_20160806_1615.zip",
//				"olympics_20160806_1630.zip",
//				"olympics_20160806_1645.zip",
//				"olympics_20160806_1700.zip",
//				"olympics_20160806_1715.zip",
//				"olympics_20160806_1730.zip",
//				"olympics_20160806_1745.zip",
//				"olympics_20160806_1800.zip",
//				"olympics_20160806_1815.zip",
//				"olympics_20160806_1845.zip",
//				"olympics_20160806_1900.zip",
//				"olympics_20160806_1915.zip",
//				"olympics_20160806_1930.zip",
//				"olympics_20160806_1945.zip",
//				"olympics_20160806_2000.zip",
//				"olympics_20160806_2015.zip",
//				"olympics_20160806_2130.zip",
//				"olympics_20160806_2145.zip",
//				"olympics_20160806_2200.zip",
//				"olympics_20160806_2215.zip",
//				"olympics_20160806_2230.zip",
//				"olympics_20160806_2245.zip",
//				"olympics_20160806_2300.zip",
//				"olympics_20160806_2315.zip",
//				"olympics_20160806_2330.zip",
//				"olympics_20160806_2345.zip",
//				"olympics_20160807_0000.zip",
//				"olympics_20160807_0015.zip",
//				"olympics_20160807_0030.zip",
//				"olympics_20160807_0045.zip",
//				"olympics_20160807_0100.zip",
//				"olympics_20160807_0200.zip"
				
//				"olympics_20160807_0430.zip",
//				"olympics_20160807_0500.zip",
//				"olympics_20160807_0515.zip",
//				"olympics_20160807_0530.zip",
//				"olympics_20160807_0545.zip",
//				"olympics_20160807_0600.zip",
//				"olympics_20160807_0615.zip",
//				"olympics_20160807_0630.zip",
//				"olympics_20160807_0645.zip",
//				"olympics_20160807_0700.zip",
//				"olympics_20160807_0715.zip",
//				"olympics_20160807_0730.zip",
//				"olympics_20160807_0745.zip",
//				"olympics_20160807_0800.zip",
//				"olympics_20160807_0815.zip",
//				"olympics_20160807_0830.zip",
//				"olympics_20160807_0845.zip",
//				"olympics_20160807_0900.zip",
//				"olympics_20160807_0915.zip"
				
				"olympics_20160807_0930.zip",
				"olympics_20160807_0945.zip",
				"olympics_20160807_1000.zip",
				"olympics_20160807_1015.zip",
				"olympics_20160807_1030.zip",
				"olympics_20160807_1045.zip",
				"olympics_20160807_1100.zip",
				"olympics_20160807_1115.zip",
				"olympics_20160807_1130.zip",
				"olympics_20160807_1145.zip",
				"olympics_20160807_1200.zip",
				"olympics_20160807_1215.zip"
		};
	}
}

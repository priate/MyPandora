package com.pandora.tools.internet.http;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;

import com.pandora.utilities.StringUtils;

/**
 * 这个验证机制可以针对FTP HTTP 直接要求用户名密码登陆那种页面
 * 从理论上来说 这个东东一般用不上
 * 传说中还可以用另一种方式解决[Authenticator] 但是没有验证
 * @author C.H.
 * @CreateTime 2016年6月15日 上午1:35:42
 */
public class HttpBasicAuthenticate {

	private static final String userName = "kuyun";
	private static final String password = "k&y&n1738";
	
	public static void main(String[] args) {
		String url = "http://downloads.stats.com/kuyun/EURO_BOXSCORE_GAME$2016061310237.XML";
		try {
			System.out.println(loadDataByUrl(url));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String loadDataByUrl(String url) throws IOException{
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		 
		// 设置认证头部
		String nameAndPass = userName + ":" + password;
		String encoding = new String(Base64.encodeBase64(nameAndPass.getBytes()));
		con.setRequestProperty("Authorization", "Basic " + encoding);
		con.setRequestMethod("GET");
		return StringUtils.InputStreamToString(con.getInputStream());
	}
}

package com.test.jsoup;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.pandora.tools.OperationFile;

public class JsoupTest {

	public static void main(String[] args) throws IOException {
		String filePath = System.getProperty("user.dir")+"/temp/baiduWebSite.html";
		
		Document doc = Jsoup.connect("http://www.cnblogs.com/skyme/p/3564391.html").post();
		OperationFile.writeText(filePath, doc.toString());
		
//		String html = OperationFile.readText(filePath);
//		Document doc = Jsoup.parse(html);
	
		
		Element content = doc.getElementById("content_left");
		Elements links = content.getElementsByTag("h3");
		for (Element link : links) {
		  String linkHref = link.getElementsByTag("a").attr("href");
		  String linkText = link.text();
		  
		  System.out.println(linkText + " - " + linkHref);
		}
	}
}
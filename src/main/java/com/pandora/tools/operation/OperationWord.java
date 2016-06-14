package com.pandora.tools.operation;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

/**
 * 对Word文件进行操作
 * 
 * @author Mordor
 * 
 */
public class OperationWord {
	
	/**
	 * 根据路径 获取文档内容 内置判断DOC格式还是DOCX
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static String readWordString(String fileName) throws IOException{
		if(fileName.endsWith(".doc"))
			return readWordExtractor(fileName).getText();
		else if(fileName.endsWith(".docx"))
			return readXWPFWordExtractor(fileName).getText();
		return "";
	}
	
	/**
	 * 读取Word文件 doc格式
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static WordExtractor readWordExtractor(String fileName) throws IOException{
		ByteArrayInputStream in = new ByteArrayInputStream(IOUtils.toByteArray(new FileInputStream(fileName)));
		WordExtractor extractor = new WordExtractor(in);
		closeInputStream(in);
		return extractor ;
	}

	/**
	 * 读取Word文件 docx格式
	 * @param fileName
	 * @return
	 * @throws IOException 
	 */
	public static XWPFWordExtractor readXWPFWordExtractor(String fileName) throws IOException{
	    XWPFDocument readXWPFDocument = readXWPFDocument(fileName);
		XWPFWordExtractor extractor = new XWPFWordExtractor(readXWPFDocument);
	    return extractor;
	}
	
	/**
	 * 读取Word文件 DOCX格式 (返回Document)
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static XWPFDocument readXWPFDocument(String fileName) throws IOException {
		InputStream is = new FileInputStream(fileName);
	    XWPFDocument doc = new XWPFDocument(is);
		closeInputStream(is);
	    return doc;
	}
	
	/**
	 * 关闭输入流
	 * 
	 * @param is
	 */
	private static void closeInputStream(InputStream is) {
		if (is != null) {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	
	/**
	 * 清除Word中超链接
	 * @param content
	 * @return
	 */
	public static String clearHtml(String content){//
		if(content==null || content.equals(""))
			return "";
		return Pattern.compile("HYPERLINK.*?_blank\"").matcher(content).replaceAll("");
	}

	public static void main(String[] args) {
		String fileName = "E:\\词条\\load\\儿科词条1--(90)\\儿童矮身材.docx";
		OperationWord word = new OperationWord();
		try {
			XWPFDocument document = word.readXWPFDocument(fileName);
			List<XWPFParagraph> paraList = document.getParagraphs();
			String title = "";
			String abs = "";
			boolean bool = false;
			for(int i = 0, length=paraList.size() ; i < length ; i++){
				XWPFParagraph para = paraList.get(i);
				String data = para.getText();
				if(i==0){
					title = data;
					continue ;
				}
				if(data.startsWith("二、")){
					break ;
				}
				if(bool){
					abs += data;
				}
				if(data.startsWith("一、")){
					bool = true;
				}
			}
			System.out.println("title : " + title);
			System.out.println("abs : " + abs);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

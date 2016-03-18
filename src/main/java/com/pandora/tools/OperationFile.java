package com.pandora.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mordor
 *
 */
public class OperationFile {
	/**
	 * 保存文本文件
	 * @param fileName	保存路径
	 * @param text		文本内容
	 */
	public static void writeText(String fileName, String text) {
		FileWriter fw = null;
		try {
			File file = new File(fileName.substring(0,fileName.lastIndexOf("/")));
			if(!file.exists())
				file.mkdirs();
			fw = new FileWriter(fileName);
			fw.write(text);
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 保存文本文件
	 * @param fileName
	 * @param texts
	 */
	public static void writeText(String fileName, List<String> texts){
		FileWriter fw = null;
		try {
			fw = new FileWriter(fileName);
			for(String text : texts)
				fw.write(text);
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 根据路径读取文件(最好限于文本文件)
	 * @param file	读取路径
	 * @return
	 */
	public static List<String> readText(String file){
		List<String> list = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(file)));
		    String brStr = null;
		    while((brStr = br.readLine()) != null){
		    	list.add(brStr);
		    }
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	

	private static List<String> filePathList = new ArrayList<String>();
	/**
	 * 根据父目录 获取该目录下所有子集目录和文件名
	 * @param fileName
	 * @return
	 */
	public static List<String> getAllFilesByPath(String fileName, boolean isDirectory){
		System.out.println("search folder : " + fileName);
		File file = new File(fileName);
		for(String files : file.list()){
			files = fileName + "/" + files;
			file = new File(files);
			if(file.isDirectory()){
				getAllFilesByPath(files, isDirectory);
				if(isDirectory)
					filePathList.add(files);
			}else{
				filePathList.add(files);
			}
		}
		return filePathList;
	}
	

	/**
	 * 根据父目录 获取该目录下目录和文件名
	 * @param fileName
	 * @return
	 */
	public static List<String> getFilesByPath(String fileName, boolean isDirectory){
		System.out.println("search folder : " + fileName);
		File file = new File(fileName);
		for(String files : file.list()){
			files = fileName + "/" + files;
			file = new File(files);
			if(file.isDirectory()){
				if(isDirectory)
					filePathList.add(files);
			}else{
				filePathList.add(files);
			}
		}
		return filePathList;
	}
	
	/**
	 * 将文件从一个目录移动到另一个目录
	 * @param form
	 * @param to
	 * @return
	 */
	public static boolean mv(String form, String to ){
		File fold = new File(form);//某路径下的文件
		String strNewPath = to;//新路径
		File fnewpath = new File(strNewPath);
		if(!fnewpath.exists())
			fnewpath.mkdirs();
		File fnew = new File(strNewPath+fold.getName());
		return fold.renameTo(fnew);
	}
	
}

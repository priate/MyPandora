package com.pandora.tools.operation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mordor
 *
 */
public class OperationFile {

	public static void main(String[] args) {
		try {
			writeTextAdd("/Users/mordor/Documents/Temp/temp.log", "omg");
			writeTextAdd("/Users/mordor/Documents/Temp/temp.log", "omg1");
//			writeText("/Users/mordor/Documents/Temp/temp.log", "omg2");
//			writeText("/Users/mordor/Documents/Temp/temp.log", "omg3");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 保存文本文件 JDK1.7及之前的用法
	 * @param fileName	保存路径
	 * @param text		文本内容
	 */
	@Deprecated
	public static void writeTextOld(String fileName, String text) {
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
		try (FileWriter fw = new FileWriter(fileName)){
			for(String text : texts)
				fw.write(text + "\n");
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 保存文本文件
	 * @param fileName	保存路径
	 * @param text		文本内容
	 */
	public static void writeText(String fileName, String text) throws IOException {
		// 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
		try (FileWriter writer = new FileWriter(fileName)){
			writer.write(text);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 追加文本
	 * @param fileName	保存路径
	 * @param text		文本内容
	 */
	public static void writeTextAdd(String fileName, String text) throws IOException {
		File file = new File(fileName.substring(0,fileName.lastIndexOf("/")));
		if(!file.exists())
			file.mkdirs();
		// 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
		try (FileWriter writer = new FileWriter(fileName, true)){
			writer.write(text);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据路径读取文件(最好限于文本文件) JDK1.7及之前的用法
	 * @param file	读取路径
	 * @return
	 */
	public static List<String> readTextOld(String file){
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

	/**
	 * 根据路径读取文件(最好限于文本文件)
	 * @param file	读取路径
	 * @return
	 */
	public static List<String> readText(String file) throws IOException {
		return Files.readAllLines(Paths.get(file));
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

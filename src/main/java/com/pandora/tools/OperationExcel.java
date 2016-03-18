package com.pandora.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 对Excel的各种操作
 * @author Mordor
 *
 */
public class OperationExcel {
	
	/**
	 * 解析xls 默认读取sheet0
	 * @param fileName 文件路径
	 * @return
	 */
	public static String[][] readExcel(String fileName){
		return readExcel(fileName, 0);
	}
	
	/**
	 * 解析Xls 默认从第二行开始读取内容
	 * @param fileName 文件路径
	 * @param sheetNum 选择读取sheet 从0开始
	 */
	public static String[][] readExcel(String fileName, int sheetNum){
		System.out.println("reading "+ fileName + " ...");
		//读取文件
		Sheet sheet = readExcelSheet(fileName, sheetNum);
		int rowNumber = sheet.getLastRowNum();
		int cellNumber = sheet.getRow(0).getLastCellNum();
		String dataString[][] = new String[rowNumber][cellNumber];
		//遍历文件
		for(int i = 1 ; i<rowNumber+1 ; i++){
			Row row = sheet.getRow(i);
			
			for (int j = 0 ; j<cellNumber ; j++){
				if(row==null) continue ;
				Cell rowFiled = row.getCell(j);
				//为空判断
				if(rowFiled == null || "".equals(rowFiled.toString())){
					dataString[i-1][j] = "";
				}else{
					dataString[i-1][j] = rowFiled.toString();
				}
			}
		}
		System.out.println("success. read End.");
		return dataString;
	}
	
	/**
	 * 获取List格式Excel数据 默认读取sheet0 从第一行开始读取 
	 * @param fileName 文件路径
	 * @return
	 */
	public static List<String[]> readExcelList(String fileName){
		return readExcelList(fileName, 0, 0);
	}
	/**
	 * 获取List格式Excel数据 默认读取sheet0
	 * @param fileName 文件路径
	 * @param index 从数字行读取内容
	 * @return
	 */
	public static List<String[]> readExcelList(String fileName, int index){
		return readExcelList(fileName, index, 0);
	}	
	/**
	 * 	 * 获取List格式Excel数据
	 * @param fileName 文件路径
	 * @param startIndex从该行开始读取内容
	 * @param endIndex从该行结束读取内容
	 * @param sheetNum sheet序号
	 * @return
	 */
	public static List<String[]> readExcelList(String fileName, int startIndex, int endIndex,int sheetNum){
		Sheet sheet = readExcelSheet(fileName,sheetNum);
		int rowNumber = sheet.getLastRowNum()+1;
		int cellNumber = sheet.getRow(0).getLastCellNum();
		
		if(endIndex>rowNumber)
		{endIndex=rowNumber;}
		List<String[]> dataList = new ArrayList<String[]>();
		System.out.println("Start analyze Excel. loading...");
		long time = System.currentTimeMillis();
		for(int i = startIndex ; i<endIndex ; i++){
			Row row = sheet.getRow(i);
			String[] datas = new String[cellNumber]; 
			if(row==null)
				continue ;
			for(int j = 0 ; j<cellNumber ; j++){
				datas[j] = row.getCell(j)==null?"" : row.getCell(j).toString();
			}
			dataList.add(datas);
		}
		System.out.println("analyze End. Time consuming ：" + (System.currentTimeMillis() - time) + " MS");
		return dataList;
	}
	/**
	 * 获取List格式Excel数据
	 * @param fileName 文件路径
	 * @param index	从该行开始读取内容
	 * @param sheetNum sheet序号
	 * @return
	 */
	public static List<String[]> readExcelList(String fileName, int index, int sheetNum){
		Sheet sheet = readExcelSheet(fileName,sheetNum);
		int rowNumber = sheet.getLastRowNum()+1;
		int cellNumber = sheet.getRow(0).getLastCellNum();
		
		List<String[]> dataList = new ArrayList<String[]>();
		System.out.println("Start analyze Excel. loading...");
		long time = System.currentTimeMillis();
		for(int i = index ; i<rowNumber ; i++){
			Row row = sheet.getRow(i);
			String[] datas = new String[cellNumber]; 
			if(row==null)
				continue ;
			for(int j = 0 ; j<cellNumber ; j++){
				datas[j] = row.getCell(j)==null?"" : row.getCell(j).toString();
			}
			dataList.add(datas);
		}
		System.out.println("analyze End. Time consuming ：" + (System.currentTimeMillis() - time) + " MS");
		return dataList;
	}
	
	/**
	 * 根据路径 获取xls内容
	 * @param fileName	路径
	 * @param sheetNum	sheet序号
	 * @return
	 */
	public static Sheet readExcelSheet(String fileName, int sheetNum){
		Workbook wb  = null;
		if(fileName.toUpperCase().endsWith(".XLS")){
			InputStream is;
			try {
				is = new FileInputStream(new File(fileName));
				wb = (Workbook) new HSSFWorkbook(is);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			try {
				wb = (Workbook) new XSSFWorkbook(fileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Sheet sheet = wb.getSheetAt(sheetNum);	
		return sheet ; 
	}
	
	/**
	 * 读取CSV格式内容
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static List<List<String>> readCSVFile(String filePath) throws IOException {  
		InputStreamReader fr = new InputStreamReader(new FileInputStream(filePath));
		BufferedReader br = new BufferedReader(fr);  
        List<List<String>> listFile = new ArrayList<List<String>>();  
        try {  
            // 读取一行  
            String rec = null;
            while ((rec = br.readLine()) != null) {  
                Matcher mCells = Pattern.compile("(\"[^\"]*(\"{2})*[^\"]*\")*[^,]*,").matcher(rec);  
                List<String> cells = new ArrayList<String>();// 每行记录一个list  
                // 读取每个单元格  
                while (mCells.find()) {  
                	String str = mCells.group();  
                    str = str.replaceAll("(?sm)\"?([^\"]*(\"{2})*[^\"]*)\"?.*,", "$1");  
                    str = str.replaceAll("(?sm)(\"(\"))", "$2");  
                    cells.add(str);  
                }  
                listFile.add(cells);  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            if (fr != null) fr.close();   
            if (br != null) br.close(); 
        }  
        return listFile;  
    }  
	
	/**
	 * 文件生成 无可维护性 用List情况更多
	 * @param fileName
	 * @param dataString
	 * @throws IOException
	 */
	@Deprecated
	public static void writeExcel(String fileName,String[][] dataString) throws IOException{
		//创建Excel
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		
		int index = dataString.length ; 
		//遍历行
		for(int i = 0 ; i< index ; i++){
			HSSFRow row = sheet.createRow(i);
			setRow(row, dataString[i]);
		}
		writeExcel(workbook, fileName);
	}
	
	/**
	 * 文件生成 默认以最大65536(xls最大上限)生成文件
	 * @param fileName	文件生成路径
	 * @param dataList	需要生成的内容
	 * @throws IOException
	 */
	public static void writeExcel(String fileName,List<String[]> dataList) throws IOException{
		writeExcel(fileName, dataList, 65536);
	}
	
	/**
	 * 文件生成 xls最大65536行 xlsx最大1048576行 目前是分文件生成(sheet页多了打开太慢)
	 * @param fileName	文件生成路径
	 * @param dataList	需要生成的内容
	 * @param maxRowNum	最大行数
	 * @throws IOException
	 */
	public static void writeExcel(String fileName,List<String[]> dataList,int maxRowNum) throws IOException{
		int index = dataList.size();
		Workbook workbook = createWork(fileName);
		if(index < maxRowNum){
			//创建Excel
			Sheet sheet = workbook.createSheet();
			//遍历行
			for(int i = 0 ; i< index ; i++){
				Row row = sheet.createRow(i);
				setRow(row, dataList.get(i));
			}
			writeExcel(workbook, fileName);
		}else{
			int runNum = index%maxRowNum==0? index%maxRowNum : index/maxRowNum+1;
			for(int fileNum = 0; fileNum < runNum ; fileNum++){
				workbook = createWork(fileName);
				//创建Excel
				Sheet sheet = workbook.createSheet();
				int length = runNum-1==fileNum ? index%maxRowNum : maxRowNum ;
				//遍历行
				for(int i = 0 ; i< length ; i++){
					Row row = sheet.createRow(i);
					setRow(row, dataList.get(i+(maxRowNum*fileNum)));
				}
				String writeFileName = fileName.substring(0,fileName.lastIndexOf("."))+fileNum + fileName.substring(fileName.lastIndexOf("."));
				writeExcel(workbook, writeFileName);
			}
		}
	}
	
	/**
	 * 文件生成
	 * @param fileName
	 * @param dataString
	 * @throws IOException
	 */
	public static void writeExcel(Workbook workbook, String fileName) throws IOException{
		FileOutputStream fOut = new FileOutputStream(fileName);
		workbook.write(fOut);
		fOut.flush();
		fOut.close();
		
		System.out.println("文件生成...");
	}
	
	/**
	 * 创建行级模块
	 * @param row
	 * @param dataString
	 */
	public static void setRow(Row row,String[] dataString){
		int length = dataString.length;
		//遍历列
		for(int j = 0 ; j < length ; j++){
			Cell cell = row.createCell(j);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(dataString[j]);
		}
	}
	
	/**
	 * 根据文件判断解析格式
	 * @param fileName
	 * @return
	 */
	public static Workbook createWork(String fileName){
		Workbook workbook = null;
		if(fileName.toUpperCase().endsWith(".XLS")){
			workbook = new HSSFWorkbook();
		}else if(fileName.toUpperCase().endsWith(".XLSX")){
			workbook = new XSSFWorkbook();
		}else{
			System.out.println("格式有问题");
			return null;
		}
		return workbook;
	}
	
	/**
	 * MAIN
	 * @param args
	 */
	public static void main(String[] args) {
		OperationExcel operationExcel = new OperationExcel();
		List<String[]> readExcelList = operationExcel.readExcelList("E:/DocumentsAndProject/scopus.xls");
		for (String[] strings : readExcelList) {
			for (String string : strings) {
				System.out.print(string + " ");
			}
			System.out.println();
		}
	}

}

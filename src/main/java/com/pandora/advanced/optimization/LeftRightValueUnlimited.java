package com.pandora.advanced.optimization;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.pandora.jdbc.db.proxool.JdbcBaseUtil;

/**
 * 传说中的 左右值无限分类算法
 * 优势在于生成树比想象中快捷 其他都是缺点
 * 并没有什么卵用
 * @author C.H
 * Create Date: 2016-1-10 下午10:57:06
 */
public class LeftRightValueUnlimited {

	JdbcBaseUtil jdbc = JdbcBaseUtil.getInstance();
	
	public static void main(String[] args) {
		LeftRightValueUnlimited menuTest = new LeftRightValueUnlimited();
		menuTest.addNode(1, "Orange");
		menuTest.lefright();
	}
	
	/**
	 * 左右值树展示部分
	 */
	public void lefright(){

		try {
			List<Map<String, Object>> results = jdbc.queryBySql("SELECT menu_name, menu_left, menu_right FROM MENU order by menu_left");
			
			int right = 0 ;
			int level = 0 ;
			
			for(int index = 0 ,length= results.size() ; index<length ; index++){
				
				Map<String, Object> result = results.get(index);
				
				int menuLeft = Integer.parseInt(result.get("menu_left").toString());
				int menuRight = Integer.parseInt(result.get("menu_right").toString());
				
				if(menuLeft - right > 1)
					level -= menuLeft - right - 1;
				tree(level);
				System.out.println(menuLeft+" - "+result.get("menu_name") + " - " + menuRight);
				
				right = menuRight;
				//判断是否有子集
				if(menuRight - menuLeft - 1 > 0 ){
					level++;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 向树结构中插入数据
	 * left 新加入的节点的父节点Left值
	 */
	public void addNode(int left,String menuName){
		jdbc.excute("UPDATE MENU SET menu_left=menu_left+2 WHERE menu_left>?", new Object[]{left});
		jdbc.excute("UPDATE MENU SET menu_right=menu_right+2 WHERE menu_right>?", new Object[]{left});
		
		jdbc.excute("INSERT INTO MENU SET menu_left=?, menu_right=?, menu_name=?", new Object[]{left+1, left+2, menuName});
	}
	
	public static void tree(int tabIndex){
		for(int i = 0 ; i<tabIndex ; i++){
			System.out.print("  ");
		}
	}
}



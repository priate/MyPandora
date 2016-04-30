package com.pandora.algorithm;

public class MazeGo {
	public static void main(String[] args) {
		MazeBean mazeBean = new MazeBean();
		int[][] maps = mazeBean.maps;

		
		
	}
}

class MazeBean {
	
	public static int mazeWidth = 15;
	public static int mazeHeight = 10;
	public static int[][] maps = new int[mazeHeight][mazeWidth];
	
	//起点坐标
	public static int startX = 0;
	public static int startY = 2;
	
	//终点坐标
	public static int endX = 14;
	public static int endY = 7;
	

	static int [] array = 
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
			1,0,1,0,0,0,0,0,1,1,1,0,0,0,1,
			5,0,0,0,0,0,0,0,1,1,1,0,0,0,1,
			1,0,0,0,1,1,1,0,0,1,0,0,0,0,1,
			1,0,0,0,1,1,1,0,0,0,0,0,1,0,1,
			1,1,0,0,1,1,1,0,0,0,0,0,1,0,1,
			1,0,0,0,0,1,0,0,0,0,1,1,1,0,1,
			1,0,1,1,0,0,0,1,0,0,0,0,0,0,8,
			1,0,1,1,0,0,0,1,0,0,0,0,0,0,1,
			1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
	
	static {
		int index = 0;
		for ( int i = 0 ; i < mazeHeight ; i++){
			for(int j =0 ; j < mazeWidth ; j++){
				maps[i][j] = array[index++];
			}
		}
	}
}


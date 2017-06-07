package com.pandora.algorithm;

public class MazeGo {
	public static void main(String[] args) {
		new Job(MazeBean.getNextAll()).run();
	}
}

/**
 * 任务 多线程
 * @author C.H.
 * @CreateTime 2016年5月1日 下午6:37:12
 */
class Job implements Runnable{

	private String walking;
	
	public Job(String walking){
		this.walking = walking;
	}

	public void run() {
		int index = 0 ;
		while(true){
			String nexts = goJob();
			index++;
			if(nexts.startsWith("success")){
				System.out.println(nexts);
				MazeShow.ShowMaps(nexts.replace("success:", ""));
				break ;
			}else if(index%100==0)
				MazeShow.ShowMaps(nexts.replace("success:", ""));
			walking = MazeBean.getNextAll();
		}
		System.out.println(index);
	}
	
	public String goJob(){
		int[][] maps = MazeBean.maps;
		int nowX = MazeBean.startX;
		int nowY = MazeBean.startY;
		
		StringBuilder recording = new StringBuilder();  
		
		for(String direction : walking.split(",")){
			
			int next = 0;
			try {
				next = Integer.parseInt(direction);
			} catch (NumberFormatException e) {
				continue ;
			}
			
			int nextX = nowX;
			int nextY = nowY;
			
			// 1上  3 下  2左  4右
			if(next == 1)
				nextX = nowX - 1 ;
			if(next == 3)
				nextX = nowX + 1 ;
			if(next == 2)
				nextY = nowY - 1 ;
			if(next == 4)
				nextY = nowY + 1 ;
			
			if(nextX<0 || nextY<0 )
				continue ;
			
			int zuobiao = maps[nextX][nextY];
			if(zuobiao == 1)
				continue ;
			recording.append(direction + ",");
			if(zuobiao == 8){
				return "success:" + recording.toString();
			}
			if(zuobiao == 0){
				nowX = nextX;
				nowY = nextY;
			}
		}
		return recording.toString();
	}
	
}


/**
 * 地图
 * 起点终点坐标
 * 随机数步数计算器
 * @author C.H.
 * @CreateTime 2016年5月1日 下午6:35:20
 */
class MazeBean {
	
	public static int mazeWidth = 15;
	public static int mazeHeight = 10;
	public static int[][] maps = new int[mazeHeight][mazeWidth];
	
	//起点坐标
	public static int startX = 2;
	public static int startY = 0;
	
	//终点坐标
	public static int endX = 7;
	public static int endY = 14;
	

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
	
	// 1上  3 下  2左  4右
	private static int[] direction = {1,2,3,4};
	
	public static int getNext(){
		return direction[(int) ((Math.random()*100 + Math.random()*100) % 4)];
	}
	
	public static String getNextAll(){
		StringBuilder sb = new StringBuilder();
		sb.append(4);
		for(int i = 0 ; i <500 ; i++)
			sb.append("," + getNext());
		return sb.toString();
	}
}

class MazeShow{
	public static void ShowMaps(String nexts){
		int[][] maps = MazeBean.maps;
		if(nexts.equals("")){
			System.out.println("憋死了");
			return ;
		}
		
		int nowX = MazeBean.startX;
		int nowY = MazeBean.startY;
		for(String nextStr : nexts.split(",") ){
			int next = Integer.parseInt(nextStr);
			
			// 1上  3 下  2左  4右
			if(next == 1)
				nowX = nowX - 1 ;
			if(next == 3)
				nowX = nowX + 1 ;
			if(next == 2)
				nowY = nowY - 1 ;
			if(next == 4)
				nowY = nowY + 1 ;

			if(nowX<0 || nowY<0 )
				continue ;
			
			maps[nowX][nowY] = 2;
		}
		
		for(int[] mapx : maps){
			for(int map : mapx){
				System.out.print(map + " ");
			}
			System.out.println();
		}
	}
}


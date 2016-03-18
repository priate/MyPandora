package com.pandora.algorithm;

import java.util.Arrays;  
import java.util.Random;

public class Sampling {

	/** 
     * 题目：给定一个数据流，其中包含无穷尽的搜索关键字（比如，人们在谷歌搜索时不断输入的关键字）。 
     * 如何才能从这个无穷尽的流中随机的选取1000个关键字？ 
     * Reservoir Sampling 
     * I read some proof on the internet,but I found they are hard to understand except this: 
     * http://www.cnblogs.com/HappyAngel/archive/2011/02/07/1949762.html 
     * It's excellent. 
     */  
	public static void main(String[] args) {  
        int k=10;  
        int n=100000;  
        int[] data=new int[n];  
        for(int i=0;i<n;i++){  
            data[i]=i;  
        }  
        int[] sample=reservoirSampling(data,k);  
        System.out.println(Arrays.toString(sample));  
    }  
	
	 public static int[] reservoirSampling(int[] data,int k){  
	        if(data==null){  
	            return new int[0];//In <Effective Java>,it advises to return int[0] instead of null.Am i doing right in this case?  
	        }  
	        if(data.length<k){  
	            return new int[0];  
	        }
	        int[] sample=new int[k];  
	        
	        int n=data.length;  
	        for(int i=0;i<n;i++){  
	            if(i<k){  
	                sample[i]=data[i];  
	            }else{  
	            	int j=new Random().nextInt(i+1);
	                if(j<k){  
	                    sample[j]=data[i];  
	                }  
	            }
	        }  
	        return sample;  
	    }
}
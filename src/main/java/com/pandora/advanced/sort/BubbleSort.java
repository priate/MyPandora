package com.pandora.advanced.sort;

/**
 * 一个简单的冒泡排序
 * Created by MyMom on 17/02/23.
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] random = new int[20];
        for(int i = 0 ; i < 20 ; i++){
            random[i] = (int)(Math.random()*100);
        }

        for(int i = 0 ; i < random.length ; i ++){
            for(int j = 1 ; j < random.length-i ; j++){
                if(random[j-1] > random[j]){
                    int x = random[j-1] ;
                    random[j-1] = random[j];
                    random[j] = x ;
                }
            }
        }

        for(int i = 0 ; i < 20 ; i++){
            System.out.println(random[i]);
        }
    }

}

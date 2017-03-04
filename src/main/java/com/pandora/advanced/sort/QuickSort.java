package com.pandora.advanced.sort;

/**
 * Created by MyMom on 17/02/23.
 */
public class QuickSort {

    static int index = 0 ;

    public static void main(String[] args) {
        int[] list = new int[200];
        for(int i = 0 ; i < list.length-1 ; i++){
            list[i] = (int)(Math.random()*100);
        }

        quick(list, 0, list.length-1);

        System.out.println("");
        System.out.println("result");
        for(int i = 0 ; i < list.length-1 ; i++){
            System.out.print(list[i] + ", ");
        }
    }

    private static void quick(int[] list, int low, int high){
        if(high > low){
            System.out.print(index + " - " );
            for(int i = 0 ; i < list.length-1 ; i++){
                System.out.print(list[i] + ", ");
            }
            System.out.println();
            index++;
            int middle = middle(list, low, high);
            quick(list, low, middle-1 );
            quick(list, middle+1, high);
        }
    }

    private static int middle(int[] list, int low, int high){
        if(low<0)return 0;
        int temp = list[low];
        while(high>low){
            while(low < high && list[high] >= temp){
                high--;
            }
            list[low] = list[high];
            while(low < high && list[low] <= temp ){
                low++;
            }
            list[high] = list[low];

            System.out.print(index + " - " );
            for(int i = 0 ; i < list.length-1 ; i++){
                System.out.print(list[i] + ", ");
            }
            System.out.println();
        }
        list[low] = temp;
        return low;
    }


}

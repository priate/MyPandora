package com.pandora.advanced.maths;/** * 一个用递归方式简述的斐波那契数列 * Created by mordor on 17/6/28. */public class Fibonacci {    public static void main(String[] args) {        System.out.print(1 + ", " + 1);        System.out.println(", " + fibonacci(1, 1));    }    public static int fibonacci(int num, int num2){        int i = num + num2;        if(9999 < i){           return i;        }        System.out.print(", " + i);        return fibonacci(num2, i);    }}
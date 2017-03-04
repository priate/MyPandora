package com.pandora.tryEveryting.basis.sign;

/**
 * 测试一下最基本的各种符号 感觉这种东东最不受重视了
 * Created by MyMom on 17/02/05.
 */
public class SignAnd {

    public static void main(String[] args) {

        System.out.println("Start &&");
        if(a() && b()){
            System.out.println("TRUE");
        }

        System.out.println("Start &");
        if(!a() & !b()){
            System.out.println("TRUE");
        }

        System.out.println("Start ||");
        if(a() || b()){
            System.out.println("TRUE");
        }

        System.out.println("Start |");
        if(a() | !b()){
            System.out.println("TRUE");
        }
    }

    public static boolean a(){
        System.out.println("A");
        return Boolean.TRUE;
    }

    public static boolean b(){
        System.out.println("B");
        return Boolean.TRUE;
    }

}

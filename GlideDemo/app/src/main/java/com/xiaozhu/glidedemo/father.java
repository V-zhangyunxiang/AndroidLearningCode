package com.xiaozhu.glidedemo;

public class father {
    /*int a = 1;
    static int b = 3;

    public void test() {
        System.out.print("Im father");
    }

    public static void text(){
        System.out.println("Im static father");
        System.out.println(b);
    }
*/

    int r;
    static int b;

    father() {
        r = 4;
        b = 5;
    }

    void printname() {
        System.out.println("I'm father");
    }

    static void printStatic() {
        System.out.println("I'm static father");
    }
}


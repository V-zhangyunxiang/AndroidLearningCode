package com.xiaozhu.glidedemo;

public class son extends father {
    /*
        int a = 2;
        static int b = 4;

        public void test() {
    */
/*        System.out.println("Im son");
        System.out.println(super.a);
        super.test();*//*

    }

    public static void text(){
        System.out.println("Im static son");
        System.out.println(b);
    }
*/
    int r;
    static int b;

    son() {
        r = 5;
        b = 6;
    }

    void print() {
        printname();
        printStatic();
        System.out.println("I'm son");
    }

    static void printStatic() {

        System.out.println("I'm static son");
    }

    public static void main(String args[]) {
        //son son1 = new son();
        /*System.out.println(son1.a);
        son1.test();*/
        //System.out.println(son1.b);
        //son1.text();
        /*father obj = new son();
        System.out.println(obj.r);
        System.out.println(obj.b);
        System.out.println(((son) obj).b);
        obj.printname();
        obj.prit();*/
        son son = new son();
        son.print();
    }

}

package com.songdan.demo.generics;


public class GenericMethods {
    public <T> void fun(T t){
        System.out.println(t.getClass().getName());
    }
    
    public <X,Y,Z> void fun(X x,Y y,Z z){
        System.out.println(x.getClass().getName());
        System.out.println(y.getClass().getName());
        System.out.println(z.getClass().getName());
    }
    /**
     * 就近原则
     * @param x
     * @param y
     * @param z
     */
    public <X,Y,Z> void fun(X x,Y y,String z){
        System.out.println(z);
        System.out.println(x.getClass().getName());
        System.out.println(y.getClass().getName());
        System.out.println(z.getClass().getName());
    }
    
    public static void main(String[] args) {
        GenericMethods gm = new GenericMethods();
        gm.fun("hello world");
        gm.fun(1);
//        gm.fun("a",'a',true);
        gm.fun("a",'a',"hello world");
    }
}

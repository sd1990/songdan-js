package com.songdan.demo.inherit.base;

import com.songdan.demo.inherit.Person;

public class BaseClass {
    private Person p = new Person("father");

    public static void fun(){
        System.out.println("hello i'm father");
    }


    public BaseClass() {
        super();
        System.out.println(this);
        System.out.println("base object construct...");
    }
    
    public void fun(int i){
        System.out.println("int run ()");
    }
    
    public void fun(String str){
        System.out.println("string run ()");
    }
    
    @Override
    public String toString() {
        return "super";
    }
}

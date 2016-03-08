package com.songdan.demo;


public class SwitchDemo {
    
    private static final char C='a';
    private static final int I=10;
    private static final boolean B=true;
    
    
    public static void main(String[] args) {
        char c= 'A';
        //只能使用可转化为int的值，字符串和枚举
        switch(c){
            case 'A':
                System.out.println("A");
            case 'B':
                System.out.println("B");
                break;
            case 'C':
                break;
            default:
                System.out.println("default");
        }
        
        /*if(judge1()&&judge2()||judge3()){
            System.out.println("okk");
        }*/
        for (int i = 0; i < 100; i++) {
            
            int value = randomInt();
            System.out.println(i+":"+value);
            if(10==value){
                return;
            }
        }
    }

    private static boolean judge3() {
        return true;
    }

    private static boolean judge2() {
        return false;
    }

    private static boolean judge1() {
        return false;
    }
    
    private static int randomInt(){
        return (int)Math.ceil(Math.random());
    }
    
}

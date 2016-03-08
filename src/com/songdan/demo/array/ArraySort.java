package com.songdan.demo.array;

import java.util.Random;

public class ArraySort {

    public static void bubble(int[] arr){
        //控制内层遍历的次数
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 0; j < arr.length-1; j++) {
                if(arr[j]>arr[j+1]){
                    swap(arr,j,j+1);
                }
            }
            System.out.println(arr[i]);
        }
    }

    public static void select(int[] arr){
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if(arr[i]>arr[j]){
                    swap(arr, i, j);
                }
            }
            System.out.println(arr[i]);
        }
    }
    
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }
    
    public static int[] buildArray(int length,boolean random){
        int[] arr = new int[length];
        if(random){
            for (int i = 0; i < arr.length; i++) {
                arr[i]=(int)(Math.random()*10);
            }
        }else{
            for (int i = 0; i < arr.length; i++) {
                arr[i]=i-new Random().nextInt(length);
            }
        }
        return arr;
    }
    
    public static String toString(int[] arr){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            sb.append("--");
        }
        return sb.toString();
    }
    
    public static void main(String[] args) {
        int[] arr = buildArray(10, true);
        System.out.println(toString(arr));
//        bubble(arr);
        select(arr);
        System.out.println(toString(arr));
    }
}

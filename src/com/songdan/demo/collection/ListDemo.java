package com.songdan.demo.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class ListDemo {
    
    
    
    public ListDemo() {
        super();
        System.out.println("list demo create ...");
    }

    public static void main(String[] args) {
        String[] strs = new String[]{"a","b","c","d"};
        ArrayList<String> list = new ArrayList<>(Arrays.asList(strs));
        for (Iterator<String> iterator = list.iterator(); iterator.hasNext();) {
            String string = (String) iterator.next();
            iterator.remove();
            System.out.println(string);
        }
    }
}

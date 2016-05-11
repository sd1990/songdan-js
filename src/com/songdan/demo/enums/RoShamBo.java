package com.songdan.demo.enums;

import org.apache.poi.ss.formula.functions.T;

/**
 * Created by PC on 2016/4/9.
 */
public class RoShamBo {

    public <T extends Completor<T>> void match(T a, T b){
        System.out.println(a+" VS "+b+":"+a.complete(b));
    }

    public <T extends Enum<T> & Completor<T>> void play(Class<T> rebClass, int size) {
        for (int i = 0; i < size; i++) {
            match(Enums.random(rebClass),Enums.random(rebClass));
        }
    }

    public static void main(String[] args) {
        RoShamBo roShamBo = new RoShamBo();
        roShamBo.play(RoshmBo2.class,10);
    }

}

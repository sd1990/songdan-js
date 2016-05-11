package com.songdan.demo.enums;

/**
 * Created by PC on 2016/4/9.
 */
public interface Completor<T> {

    OutCome complete(T t);
}

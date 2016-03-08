package com.songdan.demo.generics;

/**
 * 泛型接口
 * @author SONGDAN
 *
 * @param <T>
 */
public interface Generator<T> {
    T next();
}

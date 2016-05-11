package com.songdan.demo.effective_java.item16;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 在jdk提供的Set的添加方法添加了 自创建以来添加过多少元素的功能
 * Created by PC on 2016/5/7.
 */
public class InstrumentedSet<E> extends ForwardingSet<E> {

    public InstrumentedSet(Set<E> set) {
        super(set);
    }

    private AtomicInteger count = new AtomicInteger(0);

    @Override
    public boolean add(E e) {
        count.incrementAndGet();
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        count.addAndGet(c.size());
        return super.addAll(c);
    }

    public int getAddCount() {
        return count.get();
    }
}

package com.songdan.demo.effective_java.item16;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;

public class InstrumentedSetTest {

    @Test
    public void testGetAddCount() throws Exception {
        InstrumentedSet<String> instrumentedSet = new InstrumentedSet<>(new HashSet<String>());
        instrumentedSet.add("hello");
        instrumentedSet.add("world");
        instrumentedSet.add("!");
        Assert.assertEquals(3, instrumentedSet.getAddCount());
        instrumentedSet.addAll(Arrays.asList("hello", "lily", "!"));
        Assert.assertEquals(6, instrumentedSet.getAddCount());

    }
}
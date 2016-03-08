package com.songdan.demo.generics;

import java.util.EnumSet;
import static com.songdan.demo.generics.WaterColors.*;
public class WaterColorSets {
    public static void main(String[] args) {
        EnumSet<WaterColors> set1 = EnumSet.range(ZINC, DEEP_YELLOW);
        EnumSet<WaterColors> set2 = EnumSet.range(MEDIUM_YELLOW, CRIMSON);
        System.out.println("set1 intersection set2 is : "+Sets.intersection(set1, set2));
    }
}

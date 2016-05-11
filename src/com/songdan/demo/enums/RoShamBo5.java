package com.songdan.demo.enums;

import java.util.EnumMap;

/**
 * Created by PC on 2016/4/10.
 */
public enum RoShamBo5 implements Completor<RoShamBo5> {

    PAPER,SCISSOR,ROCK;

    private static EnumMap<RoShamBo5, EnumMap<RoShamBo5, OutCome>> table =
            new EnumMap<RoShamBo5, EnumMap<RoShamBo5, OutCome>>(RoShamBo5.class);

    static {
        for (RoShamBo5 roShamBo5 : RoShamBo5.values()) {
            table.put(roShamBo5, new EnumMap<RoShamBo5, OutCome>(RoShamBo5.class));
        }
        initRow(PAPER, OutCome.EQUALS, OutCome.LOSE, OutCome.WIN);
        initRow(SCISSOR, OutCome.WIN, OutCome.EQUALS, OutCome.LOSE);
        initRow(ROCK, OutCome.LOSE, OutCome.WIN, OutCome.EQUALS);
    }

    private static void initRow(RoShamBo5 roShamBo5, OutCome vPaper, OutCome vScissor, OutCome vRock) {
        EnumMap<RoShamBo5, OutCome> bo5OutComeEnumMap = table.get(roShamBo5);
        bo5OutComeEnumMap.put(PAPER, vPaper);
        bo5OutComeEnumMap.put(SCISSOR, vScissor);
        bo5OutComeEnumMap.put(ROCK, vRock);
    }

    @Override
    public OutCome complete(RoShamBo5 roShamBo5) {
        return table.get(this).get(roShamBo5);
    }

    public static void main(String[] args) {
        RoShamBo roShamBo = new RoShamBo();
        roShamBo.play(RoShamBo5.class,10);
    }
}

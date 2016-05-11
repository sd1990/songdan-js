package com.songdan.demo.enums;

import java.util.Random;

/**
 * 自动售货机的输入
 * Created by PC on 2016/4/6.
 */
public enum Input {
    NICKEL(5),DIME(10),QUARTER(25),DOLLAR(100),TOOTHPHASTE(200),CHIPS(75),SODA(100),SOAP(100),ABORT_TRANSACTION{
        @Override
        int amount() {
            throw new RuntimeException("ABORT.amount()");
        }
    },
    STOP{
        @Override
        int amount() {
            throw new RuntimeException("SHUT_DOWN.amount()");
        }
    };

    private int value;

    Input() {
    }

    Input(int value) {
        this.value = value;
    }

    int amount(){
        return value;
    }

    static Random random = new Random(47);

    public static Input randomSelection() {
        return Input.values()[random.nextInt(values().length - 1)];
    }
}

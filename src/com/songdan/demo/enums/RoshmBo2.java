package com.songdan.demo.enums;

/**
 * Created by PC on 2016/4/9.
 */
public enum RoshmBo2 implements Completor<RoshmBo2>{
    PAPER(OutCome.LOSE,OutCome.WIN,OutCome.EQUALS),
    ROCK(OutCome.WIN,OutCome.EQUALS,OutCome.LOSE),
    SCISSOR(OutCome.EQUALS,OutCome.LOSE,OutCome.WIN),

    ;

    private OutCome scissor,rock,paper;

    RoshmBo2(OutCome scissor, OutCome rock, OutCome paper) {
        this.scissor = scissor;
        this.rock = rock;
        this.paper = paper;
    }


    @Override
    public OutCome complete(RoshmBo2 it) {
        switch (it){
            default:
            case PAPER:
                return this.paper;
            case ROCK:
                return this.rock;
            case SCISSOR:
                return this.scissor;
        }
    }



}

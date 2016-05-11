package com.songdan.demo.enums;

import com.songdan.demo.generics.Generator;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static com.songdan.demo.enums.Input.*;

/**
 * 自动售货机
 * Created by PC on 2016/4/6.
 */
public class VendingMachine {

    private static List<Input> saleItems = new ArrayList<>();

    //交易额
    private static int amount = 0;

    private static Input selection = null;

    private static State state = State.RESTING;

    enum StateDuration{TRANSIENT}

    /**
     * 自动售货机的内部枚举类
     */
    enum State {
        /**
         * 空闲状态
         */
        RESTING{
            @Override
            void next(Input input) {
                switch (Category.categorize(input)) {
                    case MONEY:
                        //收钱
                        amount += input.amount();
                        //切换状态
                        state = ADD_MONEY;
                        break;
                    case SHUT_DOWN:
                        //切换的关机
                        state=TERMINAL;
                    default:
                }
            }
        },
        /**
         * 收银状态
         */
        ADD_MONEY{
            @Override
            void next(Input input) {
                switch (Category.categorize(input)) {
                    case MONEY:
                        //继续收钱，不用切换状态
                        amount += input.amount();
                        break;
                    case ITEM_SELECTION:
                        //选择商品，切换状态至分发状态
                        selection = input;
                        if (selection.amount() > amount) {
                            System.out.println("Insuffcient money for " + input);
                        }
                        else {
                            state = DISPENSING;
                        }
                        break;
                    case QUIT_TRANSACTION:
                        //结束交易找零
                        state = GIVING_CHANGE;
                        break;
                    case SHUT_DOWN:
                        state = TERMINAL;
                    default:
                }
            }
        },
        /**
         * 分发状态
         */
        DISPENSING(StateDuration.TRANSIENT){
            @Override
            void next() {
                System.out.println("Here is your "+selection);
                saleItems.add(selection);
                amount = amount - selection.amount();
                if (amount>0) {
                    //如果有零钱，切换到找零钱状态
                    state = GIVING_CHANGE;
                }
            }
        },
        /**
         * 找零状态
         */
        GIVING_CHANGE(StateDuration.TRANSIENT){
            @Override
            void next() {
                System.out.println("Your change is "+ amount);
                //重新置位
                amount = 0;
                state = RESTING;
            }
        },
        TERMINAL{
            @Override
            void output(){
                System.out.println("over");
            }
        };

        /**
         * 无参构造器
         */
        State() {
        }

        /**
         * 表示瞬时态的状态
         * @param stateDuration
         */
        State(StateDuration stateDuration) {
            isTransient = true;
        }


        private boolean isTransient = false;

        void next(Input input) {
            throw new RuntimeException("only call next(Input input) for non-transient states");
        }
        void next(){
            throw new RuntimeException("only call next(Input input) for transient states");
        }

        void output() {
            System.out.println(amount);
        }
    }

    static void run(Generator<Input> generator) {
        while (state != State.TERMINAL) {
            Input in = generator.next();
            state.next(in);
            while (state.isTransient) {
                state.next();
            }
            state.output();
        }
    }

    public static void main(String[] args) {
        run(new Generator<Input>() {

            @Override
            public Input next() {
                return Input.randomSelection();
            }
        });
    }
}

enum Category {
    MONEY(NICKEL, DIME, QUARTER, DOLLAR),
    ITEM_SELECTION(TOOTHPHASTE, CHIPS, SODA, SOAP),
    QUIT_TRANSACTION(ABORT_TRANSACTION),
    SHUT_DOWN(STOP);

    private Input[] values;
    Category(Input... inputs) {
        values = inputs;
    }

    private static EnumMap<Input, Category> categories = new EnumMap<Input, Category>(Input.class);
    static {
        for (Category category : Category.values()) {
            for (Input value : category.values) {
                categories.put(value, category);
            }
        }
    }

    public static Category categorize(Input input) {
        return categories.get(input);
    }
}

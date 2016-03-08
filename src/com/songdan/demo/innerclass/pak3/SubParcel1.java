package com.songdan.demo.innerclass.pak3;

import com.songdan.demo.innerclass.pak2.Parce1;
import com.songdan.demo.innerclass.parcel.Inter1;
/**
 * 编程思想内部类章节练习6
 * @author SONGDAN
 *
 */
public class SubParcel1 extends Parce1 {
    /**
     * 在子类中获取父类中的protected内部类
     */
    public Inter1 getInter1(){
        return new Inter1Parcel();
    }
}

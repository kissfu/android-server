package com.testerkit.common.watcher.criterias;


import com.testerkit.common.enums.Relation;
import com.testerkit.common.watcher.search.GroupFlag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Arr {


    public Arr(String[] arrPositive) {
        this.arrPositive = arrPositive;
    }

    public Arr(String[] arrPositive, String[] arrNegation) {
        this.arrPositive = arrPositive;
        this.arrNegation = arrNegation;
    }
    public Arr(Relation relation, String[] arrPositive) {
        this.relation = relation;
        this.arrPositive = arrPositive;
    }
    public Arr(Relation relation, String[] arrPositive, String[] arrNegation) {
        this.relation = relation;
        this.arrPositive = arrPositive;
        this.arrNegation = arrNegation;

    }

    //关系 只使用于需要匹配的数组
    protected Relation relation = Relation.OR;
    //排除的数组
    protected String[] arrNegation;
    //需要匹配的数组
    protected String[] arrPositive;

    public Relation getRelation() {
        return relation;
    }

    public String[] getArrNegation() {
        return arrNegation;
    }

    public String[] getArrPositive() {
        return arrPositive;
    }

    public abstract GroupFlag getGroupFlag();

    // region static

    public static <T> T[] concatAll(T[] first, T[]... rest) {
        if(first == null){
            return null;
        }
        int totalLength = first.length;
        if(rest == null){
            return first;
        }
        for (T[] array : rest) {
            if(array == null){
                continue;
            }
            totalLength += array.length;
        }
        T[] result = Arrays.copyOf(first, totalLength);
        int offset = first.length;
        for (T[] array : rest) {
            if(array == null){
                continue;
            }
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }
        return result;
    }

    public static <T> T[] minusAll(T[] all,T[]... remove){
        List<String> results = new ArrayList<String>();


        return (T[])results.toArray();
    }


    // endregion

    public static void main(String[] args) {
        String[] texts = {"1","2"};
        String[] texts2 = {"3","4","1"};

        texts = Arr.concatAll(texts,null);
        System.out.println("1:"+Arrays.toString(texts));

        String[] txts = Arr.concatAll(texts,texts2);
        System.out.println("2:"+Arrays.toString(txts));

    }
}

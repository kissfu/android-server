package com.testerkit.common.utils;

public class NumberUtil {

    /**
     * 四舍五入取整:(2)=2
     * 四舍五入取整:(2.1)=2
     * 四舍五入取整:(2.5)=3
     * 四舍五入取整:(2.9)=3
     * @param number
     * @return
     */
    public static int round(double number){
        return (int)Math.round(number);
    }

    public static void main(String[] args) {
        System.out.println(round(2));
        System.out.println(round(2.1));
        System.out.println(round(2.5));
        System.out.println(round(2.9));
    }
}

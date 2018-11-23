package com.testerkit.uia.utils;

import java.util.regex.Pattern;

public class RegExUtil {


    /**
     * 全部正则匹配的条件
     */
    public final static String REGULAR = "/";

    /**
     * 转义正则特殊字符 （$()+.[]?\^{},|）
     * 星号* 不特殊处理
     * @param str
     * @return
     */
    public static String escapeExprSpecialWord(String str) {
        if(StringUtils.isNullOrEmpty(str)){
            return "";
        }
//        String[] fbsArr = {"\\", "$", "(", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}", "|"};
//        for (String key : fbsArr) {
//            if (keyword.contains(key)) {
//                keyword = keyword.replace(key, "\\" + key);
//            }
//        }
//        return keyword;
        return str.replaceAll("[\\\\$\\(\\)\\+\\.\\[\\]\\?\\^\\{\\}|]", "\\\\" + "$0");
    }

    /**
     * 去除换行符和空格
     * @param str
     * @return
     */
    public static String removeLineSeparatorAndSpace(String str){
        if(StringUtils.isNullOrEmpty(str)){
            return "";
        }
        return str.replaceAll("[\r\n\\t\\s]", "");
    }

    /**
     * 只使用星号（*）作为正则匹配
     * @param criteria 条件
     * @param value
     * @return
     */
    public static boolean isMatchWithStar(String criteria, String value) {
        if (criteria == null) {
            return true;
        }
        criteria = escapeExprSpecialWord(criteria);
        criteria = criteria.replaceAll("\\*", ".*");
        criteria = removeLineSeparatorAndSpace(criteria);
        value = removeLineSeparatorAndSpace(value);
        return isMatch(Pattern.compile(criteria),value);
    }

    public static boolean isMatch(String criteria, String value) {
        if (criteria == null) {
            return true;
        }
        return isMatch(Pattern.compile(criteria),value);    }

    public static boolean isMatch(Pattern criteria, String value) {
        if (criteria == null) {
            return true;
        }
        return criteria.matcher(value != null ? value : "").matches();
    }
}

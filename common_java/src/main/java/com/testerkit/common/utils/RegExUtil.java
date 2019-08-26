package com.testerkit.common.utils;

import com.testerkit.common.model.RectInfo;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExUtil {

    private static final Pattern PATTERN_BOUNDS = Pattern.compile("\\[(-?\\d+),(-?\\d+)\\]\\[(-?\\d+),(-?\\d+)\\]");
    /**
     * 全部正则匹配的条件
     */
    public static final String REGULAR = "/";

    /**
     * 转义正则特殊字符 （$()+.[]?\^{},|）
     * 星号* 不特殊处理
     *
     * @param str
     * @return
     */
    public static String escapeExprSpecialWord(String str) {
        if (StringUtil.isNullOrEmpty(str)) {
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
     *
     * @param str
     * @return
     */
    public static String removeLineSeparatorAndSpace(String str) {
        if (StringUtil.isNullOrEmpty(str)) {
            return "";
        }
        return str.replaceAll("[\r\n\\t\\s]", "");
    }

    /**
     * 只使用星号（*）作为正则匹配
     *
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
        return isMatch(Pattern.compile(criteria), value);
    }

    public static boolean isMatch(String criteria, String value) {
        if (criteria == null) {
            return true;
        }
        return isMatch(Pattern.compile(criteria), value);
    }

    public static boolean isMatch(Pattern criteria, String value) {
        if (criteria == null) {
            return true;
        }
        return criteria.matcher(value != null ? value : "").matches();
    }

    public static RectInfo getRect(String bounds) {
        Matcher m = PATTERN_BOUNDS.matcher(bounds);
        RectInfo rect = null;
        if (m.matches()) {
            int left = Integer.parseInt(m.group(1)),
                    top = Integer.parseInt(m.group(2)),
                    right = Integer.parseInt(m.group(3)),
                    bottom = Integer.parseInt(m.group(4));
            rect = new RectInfo(left, top, right, bottom);
        }

        return rect;
    }


    // 传入正则表达式和字符串匹配指定字符串
    public static String findString(String content, String regex, int flags) {
        // 正则匹配dict字段
        Pattern pattern = Pattern.compile(regex, flags);
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }


    public static String getRegexStr(List<String> list) {
        StringBuilder regex = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            regex.append(String.format("(%s)", list.get(i)));
            if (i < list.size() - 1) {
                regex.append("|");
            }
        }
        return regex.toString();
    }
}

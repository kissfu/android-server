package com.testerkit.common.utils;

public class StringUtil {

    public static boolean isEmpty(String str){
        return isNullOrEmpty(str);
    }
    public static boolean isNotEmpty(String str){
        return !isNullOrEmpty(str);
    }

    public static boolean isNotNullOrEmpty(String str) {
        return isNullOrEmpty(str) == false;
    }

    public static boolean isNullOrEmpty(String str) {
        return (str == null || str.equals(""));
    }

    public static String join(Object[] array, String separator) {
        return array == null ? null : join(array, separator, 0, array.length);
    }

    public static String join(Object[] array, String separator, int startIndex, int endIndex) {
        if (array == null) {
            return null;
        } else {
            if (separator == null) {
                separator = "";
            }

            int bufSize = endIndex - startIndex;
            if (bufSize <= 0) {
                return "";
            } else {
                bufSize *= (array[startIndex] == null ? 16 : array[startIndex].toString().length()) + separator.length();
                StringBuffer buf = new StringBuffer(bufSize);

                for(int i = startIndex; i < endIndex; ++i) {
                    if (i > startIndex) {
                        buf.append(separator);
                    }

                    if (array[i] != null) {
                        buf.append(array[i]);
                    }
                }

                return buf.toString();
            }
        }
    }

}

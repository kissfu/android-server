package com.testerkit.common.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtil {
    public static String getTrace(Throwable t) {
        StringWriter stringWriter= new StringWriter();
        PrintWriter writer= new PrintWriter(stringWriter);
        t.printStackTrace(writer);
        StringBuffer buffer= stringWriter.getBuffer();
        return buffer.toString();
    }

    /**
     * 用于查看方法的调用路径，主要用于debug
     *
     * @return 调用路径
     */
    public static String getCalleeNames() {
        int maxLevel = 20;
        StackTraceElement stack[] = (new Throwable()).getStackTrace();
        if (stack.length <= 1) {
            return "The method call himself!";
        }
        StringBuilder stackStr = new StringBuilder();
        for (int i = 1; i < stack.length && i < maxLevel; i++) {
            stackStr.append(stack[i].getClassName().substring(stack[i].getClassName().lastIndexOf(".") + 1) + "."
                    + stack[i].getMethodName() + "[" + stack[i].getLineNumber() + "]" + " >>");
        }
        if (stack.length > maxLevel) {
            stackStr.append((stack.length - maxLevel) + " more level(s) ...");
        } else {
            stackStr.append("End!");
        }
        return stackStr.toString();
    }
}

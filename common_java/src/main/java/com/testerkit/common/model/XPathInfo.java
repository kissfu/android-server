package com.testerkit.common.model;


import com.testerkit.common.enums.XPathOption;

public class XPathInfo implements Comparable<XPathInfo> {
    private String xpath;
    private XPathOption option = XPathOption.ALL;


    public String getXpath() {
        return xpath;
    }

    public void setXpath(String xpath) {
        this.xpath = xpath;
    }

    public XPathOption getOption() {
        return option;
    }

    public void setOption(XPathOption option) {
        this.option = option;
    }

    @Override
    public String toString() {
        return xpath;
    }


    /**
     * Collections.sort(byXPath.getXpaths()); //升序输出：;
     * @param another
     * @return
     */
    @Override
    public int compareTo(XPathInfo another) {
        if(this.option == null || another.option == null){
            //Logger.error("XPathInfo option is NULL!!!");
            return -1;
        }
        //自定义比较方法，如果认为此实体本身大则返回1，否则返回-1
        if (this.option.getValue() >= another.option.getValue()) {
            return 1;
        }
        return -1;
    }
}

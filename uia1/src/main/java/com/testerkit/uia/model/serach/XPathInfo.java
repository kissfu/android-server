package com.testerkit.uia.model.serach;

import com.testerkit.uia.utils.Logger;

public class XPathInfo implements Comparable<XPathInfo> {
    private String xpath;
    private By.XPathOption option = By.XPathOption.ALL;


    public String getXpath() {
        return xpath;
    }

    public void setXpath(String xpath) {
        this.xpath = xpath;
    }

    public By.XPathOption getOption() {
        return option;
    }

    public void setOption(By.XPathOption option) {
        this.option = option;
    }

    @Override
    public String toString() {
        return xpath;
    }


    /**
     * Collections.sort(byXPath.getXpathes()); //升序输出：;
     * @param another
     * @return
     */
    @Override
    public int compareTo(XPathInfo another) {
        if(this.option == null || another.option == null){
            Logger.error("XPathInfo option is NULL!!!");
            return -1;
        }
        //自定义比较方法，如果认为此实体本身大则返回1，否则返回-1
        if (this.option.getValue() >= another.option.getValue()) {
            return 1;
        }
        return -1;
    }
}

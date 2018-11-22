package com.testerkit.uia.model.serach;

public class XPathInfo {
    private String xpath;
    private By.XPathOption option = By.XPathOption.ALL;


    @Override
    public String toString() {
        return xpath;
    }
}

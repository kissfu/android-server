package com.testerkit.common.watcher.search;

import com.testerkit.common.utils.RegExUtil;
import com.testerkit.common.watcher.criterias.Arr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ControlItem {


    private List<String> texts = new ArrayList<String>();
    private List<String> classes = new ArrayList<String>();
    private List<String> ids = new ArrayList<String>();
    private String regexClasses = "";
    private String regexTexts = "";
    private String regexIds = "";
    private String groupType = "";


    public List<String> getTexts() {
        return texts;
    }

    public List<String> getClasses() {
        return classes;
    }

    public List<String> getIds() {
        return ids;
    }

    public String getRegexClasses() {
        return regexClasses;
    }

    public String getRegexTexts() {
        return regexTexts;
    }

    public String getRegexIds() {
        return regexIds;
    }

    public String getGroupType() {
        return groupType;
    }

    public ControlItem() {
    }

    public ControlItem(Arr texts, Arr classes, Arr ids) {
        this.addToList(this.texts, texts);
        this.addToList(this.classes, classes);
        this.addToList(this.ids, ids);
    }

    /**
     * 获取正面数组和反面数组
     *
     * @param arr
     * @return
     */
    private String[] toStringArrAll(Arr arr) {
        if (arr == null) {
            return null;
        }
        return Arr.concatAll(arr.getArrPositive(), arr.getArrNegation());
    }

    public void addToList(List<String> container, Arr arr) {
        String[] data =  toStringArrAll(arr);
        if (data != null && data.length > 0) {
            this.groupType += arr.getGroupFlag().toString();
            Collections.addAll(container, data);
        }
    }

    public void setRegexClasses() {
        this.regexClasses = RegExUtil.getRegexStr(this.getClasses());
    }

    public void setRegexTexts() {
        this.regexTexts =  RegExUtil.getRegexStr(this.getTexts());
    }

    public void setRegexIds() {
        this.regexIds =  RegExUtil.getRegexStr(this.getIds());;
    }
}

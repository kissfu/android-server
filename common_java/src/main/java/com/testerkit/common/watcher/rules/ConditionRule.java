package com.testerkit.common.watcher.rules;

import com.testerkit.common.log.Logger;
import com.testerkit.common.model.NodeInfo;
import com.testerkit.common.utils.RegExUtil;
import com.testerkit.common.utils.StringUtil;
import com.testerkit.common.watcher.criterias.Arr;
import com.testerkit.common.watcher.criterias.ArrBrand;
import com.testerkit.common.watcher.criterias.ArrCls;
import com.testerkit.common.watcher.criterias.ArrId;
import com.testerkit.common.watcher.criterias.ArrPack;
import com.testerkit.common.watcher.criterias.ArrText;
import com.testerkit.common.watcher.search.ControlItem;

import java.util.ArrayList;
import java.util.List;


public abstract class ConditionRule {

    //region  properties
    protected List<NodeInfo> results = new ArrayList<NodeInfo>();
    protected boolean isTrue = false;
    protected String key = "";

    protected Arr packages;
    protected Arr brands;
    protected Arr classes;
    protected Arr ids;
    protected Arr texts;
    protected boolean isSystemPackage;


    public boolean isTrue() {
        return isTrue;
    }

    public String getKey() {
        return key;
    }

    public List<NodeInfo> getResults() {
        return results;
    }
    //endregion

    public ConditionRule(String key, ArrPack packages, ArrBrand brands, ArrCls classes, ArrId ids, ArrText texts, boolean isSystemPackage) {
        this.key = key;
        this.packages = packages;
        this.brands = brands;
        this.classes = classes;
        this.ids = ids;
        this.texts = texts;
        this.isSystemPackage = isSystemPackage;
    }

    protected boolean isEmptyArr(Arr arr) {
        if (arr == null) {
            return true;
        }
        if (isEmptyArr(arr.getArrPositive()) && isEmptyArr(arr.getArrNegation())) {
            return true;
        }
        return false;
    }

    protected boolean isEmptyArr(String[] arr) {

        return arr == null || arr.length == 0;
    }

    protected String[] toStringArr(Arr arr, Polarity pole) {
        if (arr == null) {
            return null;
        }
        if (pole == Polarity.Negation) {
            return arr.getArrNegation();
        }
        return arr.getArrPositive();
    }

    /**
     * 仅仅获取正面数组
     *
     * @param arr
     * @return
     */
    protected String[] toStringArr(Arr arr) {
        if (arr == null) {
            return null;
        }
        return arr.getArrPositive();
    }



    //region abstract

    public abstract boolean isMeetPreRule();

    public ControlItem getCondition() {

        return new ControlItem(texts, classes, ids);

    }

    public abstract boolean isThis(List<NodeInfo> nodes);


    //endregion


    //region 匹配


    private boolean isMatchText(String regex, String input) {
        boolean isOK = false;
        try {
            isOK = RegExUtil.isMatch(regex, input);
//        if (!Utils.isEmpty(regex) && regex.startsWith(Utils.REGULAR) && regex.endsWith(Utils.REGULAR) && regex.length() > 2) {
//            regex = regex.substring(1, regex.lastIndexOf(Utils.REGULAR));
//            isOK =  Utils.isRegularMatch(regex,input);
//        }else {
//            isOK = regex.equals(input);
//        }
        } catch (Exception e) {

        }
        return isOK;
    }

    /**
     * 仅仅反面面匹配，arr.arrNegation数组没有relation关系，忽略正面面匹配
     *
     * @param arr
     * @param txt
     * @return
     */
    protected boolean isMeetArrNegation(Arr arr, String txt) {
        boolean isOk = false;
        if (isEmptyArr(arr) || isEmptyArr(arr.getArrNegation()) || StringUtil.isEmpty(txt)) {
            //反面没有备匹配条件，也就没有反面匹配成功，反面匹配失败和下面匹配对应
            return false;
        }
        String[] arrN = arr.getArrNegation();
        //并且不能是一下内容
        if (!isEmptyArr(arrN)) {
            for (String s : arrN) {
                boolean isMatch = isMatchText(s, txt);
                Logger.info(String.format("monitor--->CR,isMeetArrNegation,[%s],[%s],[%s]", txt, s, isMatch));
                if (isMatch == true) {
                    isOk = true;
                    return isOk;
                }
            }
        }
        return isOk;
    }

    /**
     * 仅仅正面匹配，arr.arrPositive数组有relation关系，忽略反面匹配
     *
     * @param arr
     * @param txt
     * @return
     */
    protected boolean isMeetArrPositive(Arr arr, String txt) {
        boolean isOk = false;
        if (isEmptyArr(arr) || isEmptyArr(arr.getArrPositive())) {
            //正面匹配如果没有条件约束，说明任何元素都通过，也就是说正面匹配改元素成功。
            return true;
        }
        if (StringUtil.isEmpty(txt)) {
            //有条件，但是实际文本为空
            return false;
        }
        String[] arrP = arr.getArrPositive();
        List<Boolean> res = new ArrayList<Boolean>();
        switch (arr.getRelation()) {
            case AND:
                for (String s : arrP) {
                    boolean isMatch = isMatchText(s, txt);
                    if (isMatch == false) {
                        return false;
                    }
                }
                res.add(true);
                //没有包含一个错误 那就成功
                isOk = (!res.contains(false));
                break;
            case OR:
                for (String s : arrP) {
                    boolean isMatch = isMatchText(s, txt);
                    if (isMatch == true) {
                        return true;
                    }
                }
                res.add(false);
                //没有包含一个错误 那就成功
                isOk = (!res.contains(false));
                break;
        }
        return isOk;
    }

    /**
     * 正面匹配,并且排除反面，arr里面正面数组有relation关系，反面没有关系。
     *
     * @param arr
     * @param txt
     * @return
     */
    protected boolean isMeetArr(Arr arr, String txt) {
        boolean isOk = false;
        if (isEmptyArr(arr)) {
            //忽略该筛选
            return true;
        }
        if (StringUtil.isEmpty(txt)) {
            //有条件，但是实际文本为空
            return false;
        }
        String[] arrP = arr.getArrPositive();
        String[] arrN = arr.getArrNegation();
        List<Boolean> res = new ArrayList<Boolean>();
        //并且不能是一下内容
        if (!isEmptyArr(arrN)) {
            for (String s : arrN) {
                boolean isMatch = isMatchText(s, txt);
                Logger.info(String.format("monitor--->isMeetArr,Negation[%s],expect[%s],isMatch[%s]", s, txt, isMatch));
                if (isMatch == true) {
                    return false;
                }
            }
            res.add(true);
        }
        switch (arr.getRelation()) {
            case AND:
                if (!isEmptyArr(arrP)) {
                    for (String s : arrP) {
                        boolean isMatch = isMatchText(s, txt);
                        if (isMatch == false) {
                            return false;
                        }
                    }
                    res.add(true);
                }
                //没有包含一个错误 那就成功
                isOk = (!res.contains(false));
                break;
            case OR:

                if (!isEmptyArr(arrP)) {
                    for (String s : arrP) {
                        boolean isMatch = isMatchText(s, txt);
                        if (isMatch == true) {
                            return true;
                        }
                    }
                    res.add(false);
                }
                //没有包含一个错误 那就成功
                isOk = (!res.contains(false));
                break;
        }

        return isOk;
    }

    //endregion


    public static void main(String[] args) {


        boolean isMatch = RegExUtil.isMatch("当前网络不稳定[,，]*阿斯顿发", "当前网络不稳阿斯顿发");
        System.out.printf(isMatch + "\n");

    }

}

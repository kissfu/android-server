package com.testerkit.uia.monitor.rules;

import android.os.Build;

import com.testerkit.common.log.Logger;
import com.testerkit.common.model.NodeInfo;
import com.testerkit.common.watcher.criterias.ArrBrand;
import com.testerkit.common.watcher.criterias.ArrCls;
import com.testerkit.common.watcher.criterias.ArrId;
import com.testerkit.common.watcher.criterias.ArrPack;
import com.testerkit.common.watcher.criterias.ArrText;
import com.testerkit.common.watcher.rules.ConditionRule;
import com.testerkit.uia.monitor.WatcherContext;
import com.testerkit.uia.monitor.WatcherManager;

import java.util.List;


public class ConditionRuleAnd extends ConditionRule {


    public ConditionRuleAnd(String key, ArrPack packages, ArrBrand brands, ArrCls classes, ArrId ids, ArrText texts, boolean isSystemPackage) {
        super(key, packages, brands, classes, ids, texts, isSystemPackage);
    }

    @Override
    public boolean isMeetPreRule() {
        //新的一轮开始需要初始化变量
        isTrue = false;
        isMeetPre = false;
        ignoreResult = false;
        WatcherContext context = WatcherManager.getInstance().getContext(); //getContext();
        boolean isSysPack = false, isMeetBrand = false, isMeetPack = false;
        if (isSystemPackage) {
            isSysPack = context.isSysPackage();
            //因为是并的And关系，所有直接返回
            if (isSysPack == false) {
                return isMeetPre;
            }
        } else {
            isSysPack = true;
        }//忽略这个条件


        isMeetPack = isMeetArr(packages, context.getPackageCurrent());
        //因为是并的And关系，所有直接返回
        if (isMeetPack == false) {
            return isMeetPre;
        }


        String name = Build.BRAND.toLowerCase() + "#" + Build.MODEL.toLowerCase();
        isMeetBrand = isMeetArr(brands, name);
        //因为是并的And关系，所有直接返回
        if (isMeetBrand == false) {
            return isMeetPre;
        }


        ignoreResult = isEmptyArr(classes) && isEmptyArr(ids) && isEmptyArr(texts);
        isMeetPre = isSysPack && isMeetBrand && isMeetPack;

        return isMeetPre;
    }

    //是否策略前满足
    boolean isMeetPre = false;
    //是否需要走策略
    boolean ignoreResult = false;

    @Override
    public boolean isThis(List<NodeInfo> nodes) {
        results.clear();
        if (ignoreResult) {
            isTrue = isMeetPre;
            return isTrue;
        }
        if (nodes == null) {
            isTrue = false;
            return isTrue;
        }

        //反面数组匹配直接返回
        // || isMeetArrNegation(texts, n.getContentDesc()) 忽略
        for (NodeInfo n : nodes) {
            if (isMeetArrNegation(classes, n.getClazzName())
                    || isMeetArrNegation(ids, n.getName())
                    || (isMeetArrNegation(texts, n.getText()))) {
                Logger.info(String.format("monitor--->CRA[%s],isThis [N Rect] ：%s", this.getKey(), n.toStringShort()));
                isTrue = false;
                //只有一个匹配反面数组则返回
                return isTrue;
            }
        }
        //正面数组匹配
        //|| isMeetArrPositive(texts, n.getContentDesc()) 忽略
        for (NodeInfo n : nodes) {
            if (isMeetArrPositive(classes, n.getClazzName())
                    && isMeetArrPositive(ids, n.getName())
                    && (isMeetArrPositive(texts, n.getText()) )) {
                results.add(n);
            }
        }
        isTrue = results.size() > 0 && isMeetPre;
        return isTrue;
    }

    @Override
    public ConditionRuleAnd clone() {
        ConditionRuleAnd obj = new ConditionRuleAnd(key, (ArrPack) packages, (ArrBrand) brands, (ArrCls) classes, (ArrId) ids, (ArrText) texts, isSystemPackage);
        return obj;
    }

    public static void main(String[] args) {
        ConditionRuleAnd conditionRuleAnd = new ConditionRuleAnd(null, null, null, null, null, null, false);
        boolean isMatch = conditionRuleAnd.isMeetArr(new ArrText(new String[]{}), "禁止后不再询问");
        ;
        System.out.printf(isMatch + "\n");
    }
}

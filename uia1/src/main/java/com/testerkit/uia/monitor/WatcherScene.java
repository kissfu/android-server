package com.testerkit.uia.monitor;


import com.testerkit.common.enums.Relation;
import com.testerkit.common.log.Logger;
import com.testerkit.common.model.NodeInfo;
import com.testerkit.common.watcher.actions.ActionAbstract;
import com.testerkit.common.watcher.rules.ConditionRule;
import com.testerkit.common.watcher.search.ControlItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author able
 */
public class WatcherScene {

    private String key = "";
    private Relation relation = Relation.OR;
    //-1 是被执行 无限次。
    private int times = -1;
    private int timesGo = 0;
    private ConditionRule[] rules;
    private ActionAbstract[] methods;

    public WatcherScene(String key,ConditionRule[] rules, ActionAbstract[] methods){
        this.key = key;
        this.rules = rules;
        this.methods = methods;
    }
    public WatcherScene(String key,Relation relation, ConditionRule[] rules, ActionAbstract[] methods){
        this.key = key;
        this.relation = relation;
        this.rules = rules;
        this.methods = methods;
    }
    public WatcherScene(String key,Relation relation, int times, ConditionRule[] rules, ActionAbstract[] methods){
        this.key = key;
        this.times = times;
        this.relation = relation;
        this.rules = rules;
        this.methods = methods;
    }

    public void setTimesGo(int timesGo) {
        this.timesGo = timesGo;
    }

//    public void setNumber(int number) {
//        this.number = number;
//    }

    public boolean isMeetPre(){
        boolean isOk = false;
        if(rules == null || rules.length == 0 || (timesGo > times && times != -1)){
            return isOk;
        }
        List<Boolean> res = new ArrayList<Boolean>();
        for (ConditionRule r: rules) {
            res.add(r.isMeetPreRule());
        }
        switch (relation){
            case OR:
                //有一个是真结果就是成功
                isOk = res.contains(true);
                break;
            case AND:
                //有一个是否定结果就是失败
                isOk = !res.contains(false);
                break;
        }
        return isOk;
    }

    public List<ControlItem> getCondition(){
        List<ControlItem> controlItems = new ArrayList<ControlItem>();
        for (ConditionRule r: rules) {
            ControlItem item = r.getCondition();
            if(item != null) {
                //Log.i(Utils.tag, String.format("monitor[%s][%s][%s]--->ws getCondition:%s", timesGo, number, r.getKey(), item.toString()));
                controlItems.add(item);
            }
        }
        return controlItems;
    }


    //多个条件 取或  得出最后的结果,所有条件都要循环
    public boolean isThis(List<NodeInfo> nodes){

        boolean isOk = false;
        if(rules == null || rules.length == 0 || (timesGo > times && times != -1)){
            return isOk;
        }
        List<Boolean> res = new ArrayList<Boolean>();
        for (ConditionRule r: rules) {
            boolean  isResult = r.isThis(nodes);
            res.add(isResult);
        }
        switch (relation){
            case OR:
                //有一个是真结果就是成功
                isOk = res.contains(true);
                break;
            case AND:
                //有一个是否定结果就是失败
                isOk = !res.contains(false);
                break;
        }
        Logger.info(String.format("monitor[%s][%s]--->ws isThis,isOK:%s", timesGo, key, isOk));
        return isOk;
    }

    //所有方法都要执行
    public boolean doAction(){
        boolean isResult = false;
        for (ActionAbstract m: methods) {
            ConditionRule rule = isMethodDo(m.getKey());
              if(rule != null){

                  // 放弃API Level为16及以下的设备。 暂时没有发现这类设备有这个问题。
                  if (android.os.Build.VERSION.SDK_INT < 17) {
                      return false;
                  }
                  boolean isOk = m.doAction(rule.getResults());
                  Logger.info(String.format("monitor[%s][%s][%s][%s]--->ws doAction,isOK:%s",timesGo,key,rule.getKey(),m.getKey(),isOk));
            }

        }
        return isResult;
    }

    private ConditionRule isMethodDo(String key){
        ConditionRule result = null;
        for (ConditionRule r: rules) {
           if(r.getKey().equals(key) && r.isTrue()){
               result = r;
               break;
           }
        }
        return result;
    }
}

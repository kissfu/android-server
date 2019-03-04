package com.testerkit.common.search.matcher;

import com.google.gson.Gson;
import com.testerkit.common.enums.ByOption;
import com.testerkit.common.enums.Relation;
import com.testerkit.common.json.ConditionJson;
import com.testerkit.common.json.StepJson;
import com.testerkit.common.log.Logger;
import com.testerkit.common.model.NodeInfo;
import com.testerkit.common.model.UIDumpInfo;
import com.testerkit.common.search.by.ByBase;

import java.util.ArrayList;
import java.util.List;

public class MatcherManager {

    private String FUNC = "Matcher";

    public NodeInfo isMatchSingle(StepJson step, UIDumpInfo dump) {

        ConditionJson condition = step.getCondition();
        List<MatcherBase> matchers = getMatchersWithoutXP(condition, dump, ByOption.REQUIRED);
        NodeInfo result = null;
        List<NodeInfo> results = new ArrayList<NodeInfo>();

        //REQUIRED matcher
        for (NodeInfo node : dump.getNodes()) {
            boolean isOk = isMatch(matchers, node, condition.getRelation());
            if (isOk) {
                results.add(node);
            }
        }
        if (results.isEmpty()) {
            return result;
        }
        if (results.size() == 1) {
            return results.get(0);
        }
        //FILTER matcher
        matchers = getMatchersWithoutXP(condition, dump, ByOption.FILTER);
        results = isMatchFilter(matchers, results);
        if (results.isEmpty()) {
            return result;
        }
        if (results.size() == 1) {
            return results.get(0);
        }
        //XPATH matcher
        results = isMatchXPath(condition, dump, results);
        if (results.isEmpty()) {
            return result;
        }
        if (results.size() == 1) {
            return results.get(0);
        }
        Logger.iFunc(FUNC, String.format("find nodes [%s]", results.size()));
        return result;
    }

    //region Matcher's Match

    private List<NodeInfo> isMatchXPath(ConditionJson condition, UIDumpInfo dump, List<NodeInfo> nodes) {
        List<NodeInfo> results = new ArrayList<NodeInfo>();
        MatcherBase matcher = null;
        ByBase by = condition.getXpath();
        if (by != null && by.isIgnoredPre() == false) {
            matcher = new MatcherXPath(condition.getXpath(), dump);
        }
        if (matcher == null) {
            return nodes;
        }

        List<NodeInfo> simpleArr = matcher.findMatches();

        for (NodeInfo node : nodes) {
            for (NodeInfo n : simpleArr) {
                if (node.getXpathSimple().equalsIgnoreCase(n.getXpathSimple())) {
                    results.add(node);
                }
            }
        }

        return results;
    }

    private List<NodeInfo> isMatchFilter(List<MatcherBase> matchers, List<NodeInfo> nodes) {
        List<NodeInfo> resultsRemove = new ArrayList<NodeInfo>();
        if (matchers.isEmpty()) {
            return nodes;
        }

        for (NodeInfo node : nodes) {
            for (MatcherBase matcher : matchers) {
                boolean isOk = matcher.isMatch(node);
                if (false == isOk) {
                    resultsRemove.add(node);
                }
            }
        }
        nodes.removeAll(resultsRemove);
        return nodes;
    }

    private boolean isMatch(List<MatcherBase> matchers, NodeInfo node, Relation relation) {
        boolean result = false;
        switch (relation) {
            case AND:
                for (MatcherBase matcher : matchers) {
                    boolean isOk = matcher.isMatch(node);
                    if (isOk == false) {
                        return false;
                    }
                }
                result = true;
                break;
            case OR:
                for (MatcherBase matcher : matchers) {
                    boolean isOk = matcher.isMatch(node);
                    if (isOk == true) {
                        return true;
                    }
                }
                result = true;
                break;
        }


        return result;
    }

    private List<MatcherBase> getMatchersWithoutXP(ConditionJson condition, UIDumpInfo dump, ByOption option) {
        List<MatcherBase> matchers = new ArrayList<MatcherBase>();

        if (condition == null) {
            return matchers;
        }

        ByBase by = condition.getName();
        if (by != null && by.isIgnoredPre() == false && by.getOption() == option) {
            matchers.add(new MatcherText(by, dump));
        }
        by = condition.getClazz();
        if (by != null && by.isIgnoredPre() == false && by.getOption() == option) {
            matchers.add(new MatcherText(by, dump));
        }
        by = condition.getPackageName();
        if (by != null && by.isIgnoredPre() == false && by.getOption() == option) {
            matchers.add(new MatcherText(by, dump));
        }
        by = condition.getText();
        if (by != null && by.isIgnoredPre() == false && by.getOption() == option) {
            matchers.add(new MatcherText(by, dump));
        }
        return matchers;
    }

    //endregion


    //region SingletonHolder

    /**
     * Java单例模式的写法（Initialization on Demand Holder模式）
     * http://blog.csdn.net/kohaku/article/details/39268697
     * http://www.cnblogs.com/sunxucool/p/3949327.html
     */
    private static class SingletonHolder {
        public final static MatcherManager instance = new MatcherManager();
    }

    public static MatcherManager getInstance() {
        return SingletonHolder.instance;
    }

    //endregion

    public static void main(String[] args) {
        String str = "{\"action\":\"find\",\"rule\":\"click\",\"node\":{\"type\":\"uia\",\"index\":\"3\",\"bounds\":{\"left\":0,\"top\":2,\"right\":3,\"bottom\":4}},\"scroll\":{\"times\":\"1\",\"timeOut\":\"3000\",\"toCenter\":\"true\",\"direction\":\"up\"},\"condition\":{\"relation\":\"AND\",\"name\":{\"arr\":[\"id\"],\"option\":\"REQUIRED\",\"relation\":\"AND\"},\"clazz\":{\"arr\":[\"com.test.demo.MainActivity\"],\"option\":\"REQUIRED\",\"relation\":\"AND\"},\"text\":{\"arr\":[\"hello world\"],\"option\":\"REQUIRED\",\"relation\":\"AND\"},\"packageName\":{\"arr\":[\"com.test.demo\"],\"option\":\"IGNORED\",\"relation\":\"AND\"},\"xpath\":{\"xps\":[{\"option\":\"SIMPLE\",\"xpath\":\"0-0-0\"},{\"option\":\"NO_TEXT\",\"xpath\":\"//node[@name='id' and @clazz='com.test.demo.MainActivity']/node[@name='id']\"}],\"option\":\"FILTER\",\"relation\":\"OR\"}}}";
        // 1. 创建Gson对象
        Gson gson = new Gson();
        StepJson json = gson.fromJson(str, StepJson.class);
    }

}

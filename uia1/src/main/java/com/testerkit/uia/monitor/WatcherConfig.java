package com.testerkit.uia.monitor;

import com.testerkit.common.constants.ConstantWatcher;
import com.testerkit.common.enums.ClickPosition;
import com.testerkit.common.enums.Relation;
import com.testerkit.common.log.Logger;
import com.testerkit.common.watcher.actions.ActionAbstract;
import com.testerkit.common.watcher.criterias.Arr;
import com.testerkit.common.watcher.criterias.ArrBrand;
import com.testerkit.common.watcher.criterias.ArrCls;
import com.testerkit.common.watcher.criterias.ArrId;
import com.testerkit.common.watcher.criterias.ArrPack;
import com.testerkit.common.watcher.criterias.ArrText;
import com.testerkit.common.watcher.rules.ConditionRule;
import com.testerkit.common.watcher.search.ControlItem;
import com.testerkit.common.watcher.search.WatcherSearch;
import com.testerkit.uia.monitor.actions.AClick;
import com.testerkit.uia.monitor.actions.AClickNeverShowAgain;
import com.testerkit.uia.monitor.actions.AOppoCompleteButtonClick;
import com.testerkit.uia.monitor.actions.AOppoSpecialInstall;
import com.testerkit.uia.monitor.actions.APressBack;
import com.testerkit.uia.monitor.actions.AStopApp;
import com.testerkit.uia.monitor.actions.ASwipe;
import com.testerkit.uia.monitor.actions.ATypeOppo;
import com.testerkit.uia.monitor.actions.AWarningInstall;
import com.testerkit.uia.monitor.rules.ConditionRuleAnd;

import java.util.ArrayList;
import java.util.List;


public class WatcherConfig {
    public static List<WatcherScene> WATCHERS = new ArrayList<WatcherScene>();

    public static WatcherSearch SEARCH = null;


    public static void load() {

        String[] texts = Arr.concatAll(ConstantWatcher.OK, ConstantWatcher.ALLOW, ConstantWatcher.NEXT);


        /**
         * 运行场景之前，解锁屏幕
         */
        WATCHERS.add(new WatcherScene(0, Relation.OR, 1, new ConditionRule[]{
                new ConditionRuleAnd("1",
                        new ArrPack(new String[]{"com.android.systemui"}),
                        new ArrBrand(new String[]{".*vivo.*"}),
                        new ArrCls(new String[]{android.widget.ImageView.class.getName()}),
                        new ArrId(new String[]{"left_shortcut"}),
                        null,
                        false),
                new ConditionRuleAnd("2",
                        new ArrPack(new String[]{"com.vivo.lockscreendefaultwidget", "com.vivo.lockscreen.weather", "bbk.music.lockwidget", "com.gionee.navi.keyguard"}),
                        null,
                        null,
                        null,
                        null,
                        false)
        }, new ActionAbstract[]{
                new ASwipe("1"), new ASwipe("2")
        }));


        //region oppo

        /**
         * 安装APK时输入密码
         */
        WATCHERS.add(new WatcherScene(1, Relation.AND, new ConditionRule[]{
                new ConditionRuleAnd("1",
                        new ArrPack(new String[]{"com.coloros.safecenter", "com.bbk.account"}),
                        new ArrBrand(new String[]{".*oppo.*", ".*vivo.*"}),
                        null,
                        null,
                        null,
                        true)
        }, new ActionAbstract[]{
                new ATypeOppo("1")
        }));


        /**
         * oppo usb 选择
         * [usb-selected.png]
         */
        WATCHERS.add(new WatcherScene(2, new ConditionRule[]{
                new ConditionRuleAnd("1",
                        new ArrPack(new String[]{"com.coloros.usbselection"}),
                        null,
                        new ArrCls(new String[]{android.widget.Button.class.getName()}),
                        null,
                        new ArrText(new String[]{"取消"}),
                        false)
        }, new ActionAbstract[]{
                new AClick("1")
        }));


        /**
         * OPPO新版本手机，安装界面通过Class 和ID判断安装控件位置
         */
        WATCHERS.add(new WatcherScene(7, Relation.OR, new ConditionRule[]{
                new ConditionRuleAnd("1",
                        new ArrPack(new String[]{"com.android.packageinstaller"}),
                        new ArrBrand(new String[]{".*oppo.*"}),
                        new ArrCls(new String[]{android.widget.LinearLayout.class.getName(), android.widget.Button.class.getName()}),
                        new ArrId(new String[]{"bottom_button_layout", "done_button"}),
                        new ArrText(new String[]{"完成"}),
                        true),
                new ConditionRuleAnd("2",
                        new ArrPack(new String[]{"com.android.packageinstaller"}),
                        new ArrBrand(new String[]{".*oppo.*"}),
                        new ArrCls(new String[]{android.widget.LinearLayout.class.getName()}),
                        new ArrId(new String[]{"bottom_button_layout"}),
                        null,
                        true)
        }, new ActionAbstract[]{
                new AOppoCompleteButtonClick("1"), new AOppoCompleteButtonClick("2")
        }));

        /**
         *  点击掉OPPO手机的【无视风险安装】的安装文本
         */
        WATCHERS.add(new WatcherScene(8, Relation.AND, new ConditionRule[]{
                new ConditionRuleAnd("1",
                        new ArrPack(new String[]{}, new String[]{"com.coloros.safecenter"}),
                        new ArrBrand(new String[]{".*oppo.*"}),
                        new ArrCls(new String[]{android.widget.TextView.class.getName()}),
                        null,
                        new ArrText(new String[]{".*无视风险安装.*"}),
                        true)
        }, new ActionAbstract[]{
                new AClick("1", ClickPosition.BOTTOM_RIGHT)
        }));

        //endregion


        /**
         *  被root手机权限弹出框进行处理
         */
        WATCHERS.add(new WatcherScene(3, Relation.AND, new ConditionRule[]{
                new ConditionRuleAnd("1",
                        new ArrPack(new String[]{"com.kingroot.kinguser"}),
                        null,
                        new ArrCls(new String[]{android.widget.Button.class.getName()}),
                        null,
                        new ArrText(new String[]{"提示：若非本人行为，建议拒绝"}),
                        false),
                new ConditionRuleAnd("2",
                        new ArrPack(new String[]{"com.kingroot.kinguser"}),
                        null,
                        new ArrCls(new String[]{android.widget.Button.class.getName()}),
                        null,
                        new ArrText(ConstantWatcher.ALLOW),
                        false)
        }, new ActionAbstract[]{
                new AClick("2")
        }));


        /**
         * 对被root的手机弹出权限框进行处理：非KingRoot软件root的手机
         */
        WATCHERS.add(new WatcherScene(4, Relation.AND, new ConditionRule[]{
                new ConditionRuleAnd("1",
                        new ArrPack(new String[]{"com.mgyun.shua.su", "eu.chainfire.supersu", ".*root.*", ".*supersu.*"}),
                        null,
                        new ArrCls(new String[]{android.widget.Button.class.getName()}),
                        null,
                        new ArrText(new String[]{"拒绝", "拒絕", "Deny"}),
                        false)
        }, new ActionAbstract[]{
                new AClick("1")
        }));


        /**
         * 强制结束关于金融类APP被重签后弹出的安全提示包名（因为这类弹出框不能点击任何提示的按钮，否则会造成测试失败）
         */
        WATCHERS.add(new WatcherScene(5, Relation.AND, new ConditionRule[]{
                new ConditionRuleAnd("1",
                        new ArrPack(new String[]{"com.coloros.securepay"}),
                        new ArrBrand(new String[]{".*oppo.*"}),
                        null,
                        null,
                        null,
                        false)
        }, new ActionAbstract[]{
                new AStopApp("1")
        }));


        /**
         *  处理点击带有【风险、安全、威胁、警示】关键词和【继续安装】的安装流程
         *  (.*风险.*)|(安全)
         */
        WATCHERS.add(new WatcherScene(6, Relation.AND, new ConditionRule[]{
                new ConditionRuleAnd("1",
                        null,
                        null,
                        null,
                        null,
                        new ArrText(new String[]{".*风险.*", ".*安全.*", ".*威胁.*", ".*警示.*"}),
                        true),
                new ConditionRuleAnd("2",
                        null,
                        null,
                        new ArrCls(new String[]{android.widget.Button.class.getName()}),
                        null,
                        new ArrText(new String[]{"继续安装"}),
                        true)
        }, new ActionAbstract[]{
                new AWarningInstall("2")
        }));


        /**
         * 一加手机点击权限框
         */
        WATCHERS.add(new WatcherScene(9, Relation.AND, new ConditionRule[]{
                new ConditionRuleAnd("1",
                        null,
                        new ArrBrand(new String[]{".*oneplus.*"}),
                        null,
                        null,
                        new ArrText(new String[]{"禁止后不再询问"}),
                        true),
                new ConditionRuleAnd("2",
                        null,
                        null,
                        null,
                        null,
                        new ArrText(new String[]{"始终允许"}),
                        true)
        }, new ActionAbstract[]{
                new AClick("2")
        }));


        /**
         *  勾选usb调试\小米等手机
         */
        WATCHERS.add(new WatcherScene(10, Relation.AND, new ConditionRule[]{
                new ConditionRuleAnd("1",
                        null,
                        null,
                        null,
                        null,
                        new ArrText(new String[]{"允许使用这台计算机进行调试", "allow from this computer", "允許透過這台電腦進行"}),
                        true),
                new ConditionRuleAnd("2",
                        null,
                        null,
                        new ArrCls(new String[]{android.widget.Button.class.getName()}),
                        null,
                        new ArrText(ConstantWatcher.OK),
                        true)
        }, new ActionAbstract[]{
                new AClick("1"), new AClick("2")
        }));


        /**
         * 点掉带有【安全警告】且有【好】的弹出框
         */
        WATCHERS.add(new WatcherScene(11, Relation.AND, new ConditionRule[]{
                new ConditionRuleAnd("1",
                        new ArrPack(new String[]{"android"}),
                        null,
                        new ArrCls(new String[]{android.widget.TextView.class.getName()}),
                        null,
                        new ArrText(new String[]{"安全警告"}),
                        false),
                new ConditionRuleAnd("2",
                        new ArrPack(new String[]{"android"}),
                        null,
                        new ArrCls(new String[]{android.widget.TextView.class.getName(), android.widget.Button.class.getName()}),
                        null,
                        new ArrText(new String[]{"好"}),
                        false)
        }, new ActionAbstract[]{
                new AClick("2")
        }));

        /**
         * 提示敏感权限
         * [ui-attention1.png]
         */
        WATCHERS.add(new WatcherScene(12, Relation.AND, new ConditionRule[]{
                new ConditionRuleAnd("1",
                        null,
                        null,
                        new ArrCls(new String[]{android.widget.TextView.class.getName()}),
                        null,
                        new ArrText(new String[]{"检测.*有敏感权限.*是否进行设置.*"}),
                        true),
                new ConditionRuleAnd("2",
                        null,
                        null,
                        new ArrCls(new String[]{android.widget.TextView.class.getName(), android.widget.Button.class.getName()}),
                        null,
                        new ArrText(new String[]{"取消"}),
                        true)
        }, new ActionAbstract[]{
                new AClick("2")
        }));

        /**
         * 努比亚N2（NX573J，NX575J）的禁止后不再询问需要不勾选，如果勾选了，"始终允许无法被点击"
         * 要优先后面的那个禁止后不再询问
         * [ui-noask1.png]
         */
        WATCHERS.add(new WatcherScene(13, Relation.AND, new ConditionRule[]{
                new ConditionRuleAnd("1",
                        null,
                        null,
                        null,
                        null,
                        new ArrText(new String[]{"禁止后不再询问"}),
                        true),
                new ConditionRuleAnd("2",
                        null,
                        null,
                        new ArrCls(new String[]{android.widget.TextView.class.getName(), android.widget.Button.class.getName()}),
                        null,
                        new ArrText(new String[]{"始终允许"}),
                        true),
                new ConditionRuleAnd("3",
                        null,
                        null,
                        new ArrCls(new String[]{android.widget.TextView.class.getName()}),
                        null,
                        new ArrText(new String[]{".*需要.*是否允许.*你可以在手机管家-权限管理更改权限设置。"}),
                        true)
        }, new ActionAbstract[]{
                new AClickNeverShowAgain("1", false), new AClick("2")
        }));


        /**
         *  【确定】类按钮的权限弹出框点击，需要先勾选，然后点击,  组建勾选不再提示的Condition
         */
        WATCHERS.add(new WatcherScene(14, Relation.OR, new ConditionRule[]{
                new ConditionRuleAnd("1",
                        null,
                        null,
                        new ArrCls(new String[]{android.widget.Button.class.getName(), "amigo.widget.AmigoButton", android.widget.TextView.class.getName()}),
                        null,
                        new ArrText(texts, new String[]{"应用程序操作", "应用程序权限"}),
                        true),
                new ConditionRuleAnd("2",
                        null,
                        null,
                        null,
                        null,
                        new ArrText(ConstantWatcher.NEXT_NOT_PROMPT),
                        true)
        }, new ActionAbstract[]{
                new AClickNeverShowAgain("2"), new AClick("1")
        }));

        /**
         * 【确定】、【允许】、【是】、【完成】、【忽略更新】类按钮的点击..
         */
        WATCHERS.add(new WatcherScene(15, Relation.OR, new ConditionRule[]{
                new ConditionRuleAnd("1",
                        null,
                        null,
                        new ArrCls(new String[]{android.widget.Button.class.getName()}),
                        null,
                        new ArrText(texts),
                        true)
        }, new ActionAbstract[]{
                new AClick("1")
        }));

        /**
         * 检测到系统更新了，点击返回退出更新界面
         */
        WATCHERS.add(new WatcherScene(16, Relation.AND, new ConditionRule[]{
                new ConditionRuleAnd("1", null, null, null, null,
                        new ArrText(new String[]{"夜间", ".*更新版本.*vivo.*", ".*系统升级.*", "新版本NX.*下载完成.*"}), true),
        }, new ActionAbstract[]{
                new APressBack("1")
        }));

        /**
         *  电量低
         * [ui-power-low1.png]
         */
        WATCHERS.add(new WatcherScene(17, Relation.AND, new ConditionRule[]{
                new ConditionRuleAnd("1",
                        null,
                        null,
                        null,
                        null,
                        new ArrText(new String[]{"电量低于.*"}),
                        true),
                new ConditionRuleAnd("2",
                        null,
                        null,
                        new ArrCls(new String[]{android.widget.Button.class.getName()}),
                        null,
                        new ArrText(new String[]{"取消"}),
                        true)
        }, new ActionAbstract[]{
                new AClick("2")
        }));

        /**
         * OPPO手机【软件商店安装】偏移点击实现安装
         *  在当前包名下，不能找到【继续安装】但能找到【软件商店安装】
         */
        WATCHERS.add(new WatcherScene(18, Relation.AND, new ConditionRule[]{
                new ConditionRuleAnd("1",
                        new ArrPack(new String[]{"com.android.packageinstaller"}),
                        new ArrBrand(new String[]{".*oppo.*"}),
                        new ArrCls(new String[]{android.widget.Button.class.getName()}),
                        null,
                        new ArrText(new String[]{"[^继续安装]"}),
                        false),
                new ConditionRuleAnd("2",
                        new ArrPack(new String[]{"com.android.packageinstaller"}),
                        new ArrBrand(new String[]{".*oppo.*"}),
                        new ArrCls(new String[]{android.widget.Button.class.getName()}),
                        null,
                        new ArrText(new String[]{"软件商店安装"}),
                        false)
        }, new ActionAbstract[]{
                new AOppoSpecialInstall("2")
        }));


        /**
         *  崩溃框点击
         */
        WATCHERS.add(new WatcherScene(19, Relation.AND, 2, new ConditionRule[]{
                new ConditionRuleAnd("1",
                        null,
                        null,
                        null,
                        null,
                        new ArrText(new String[]{".*已停止运行.*", ".*已停止.*", ".*意外停止.*", ".*无响应.*", ".*未响应.*", ".*没有响应.*", ".*isn't responding.*", ".*has stopped.*"}),
                        true),
                new ConditionRuleAnd("2",
                        null,
                        null,
                        new ArrCls(new String[]{android.widget.Button.class.getName()}),
                        null,
                        new ArrText(new String[]{"确定", "重新打开应用", "关闭", "取消", "重启应用", "关闭应用", "OK", "ok", "cancle", "Cancle", "close", "Close", "關閉", "確定"}),
                        true)
        }, new ActionAbstract[]{
                new AClick("2")
        }));

        /**
         * 处理完成对话框：比如应用打开一个链接，调用系统浏览器进行访问，但系统上安装了两个浏览器，这时系统就会弹出让用户选择使用哪个浏览器，以完成打开链接这一操作关键词
         */
        WATCHERS.add(new WatcherScene(20, Relation.OR, new ConditionRule[]{
                new ConditionRuleAnd("1",
                        new ArrPack(new String[]{"android"}),
                        null,
                        null,
                        null,
                        new ArrText(new String[]{"Always", "始终", "总是", "總是", "始終"}),
                        false)
        }, new ActionAbstract[]{
                new AClick("1", ClickPosition.CENTER)
        }));

        /**
         * 处理网络连接失败情况
         */
        WATCHERS.add(new WatcherScene(21, Relation.AND, new ConditionRule[]{
                new ConditionRuleAnd("1",
                        null,
                        null,
                        null,
                        null,
                        new ArrText(new String[]{"网络连接失败", "当前网络不稳定.*", "加载失败了.*重试下吧", "数据异常.*请重试",
                                "您的网络好像太不给力.*请稍后再试", "网络请求失败.*", "页面加载失败", "抱歉.*网络管理员开小差了.*请刷新", "出错了.*请稍后再试.*"}),
                        false),
                new ConditionRuleAnd("2",
                        null,
                        null,
                        new ArrCls(new String[]{android.widget.Button.class.getName()}),
                        null,
                        new ArrText(new String[]{"点击重试", "重新加载", "刷新", "重试", "刷新试试"}),
                        false)
        }, new ActionAbstract[]{
                new AClick("2", ClickPosition.CENTER)
        }));

        /**
         * 对应场景图片ui-1
         */
        WATCHERS.add(new WatcherScene(22, Relation.AND, new ConditionRule[]{
                new ConditionRuleAnd("1",
                        null,
                        null,
                        new ArrCls(new String[]{android.widget.Button.class.getName(), android.widget.TextView.class.getName()}),
                        null,
                        new ArrText(ConstantWatcher.OK),
                        true),
                new ConditionRuleAnd("2",
                        null,
                        null,
                        null,
                        null,
                        new ArrText(new String[]{"应用程序操作", "应用程序权限"}),
                        true)
        }, new ActionAbstract[]{
                new AClick("1")
        }));

        /**
         *  猎豹清理大师
         * [ui-liebao1.png]
         */
        WATCHERS.add(new WatcherScene(23, Relation.AND, new ConditionRule[]{
                new ConditionRuleAnd("1",
                        new ArrPack(new String[]{"com.cleanmaster.mguard_cn"}),
                        null,
                        null,
                        null,
                        new ArrText(new String[]{"猎豹清理大师"}),
                        false),
                new ConditionRuleAnd("2",
                        new ArrPack(new String[]{"com.cleanmaster.mguard_cn"}),
                        null,
                        null,
                        null,
                        new ArrText(new String[]{"取消"}),
                        false)
        }, new ActionAbstract[]{
                new AClick("2")
        }));

        List<ControlItem> controlItems = new ArrayList<ControlItem>();
        SEARCH = new WatcherSearch(controlItems);
        for (int i = 0; i < WATCHERS.size(); i++) {
//            WATCHERS.get(i).setNumber(i);//设置编号，方便查找
            controlItems.addAll(WATCHERS.get(i).getCondition());
        }
        SEARCH.initAll();
    }

    public static void main(String[] args) {
        Logger.IS_OPEN = true;
        WatcherManager.getInstance();//.start();
        List<ControlItem> items = new ArrayList<ControlItem>();
        for (WatcherScene ws : WATCHERS) {
            List<ControlItem> tempList = ws.getCondition();
            if (tempList != null && tempList.size() > 0) {
                items.addAll(tempList);
            }
        }
//        WatcherManager.getInstance().getContext().getClassifyControlManager().load(items);
    }

}

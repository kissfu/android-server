package com.testerkit.uia.monitor;

import android.view.accessibility.AccessibilityNodeInfo;

import com.testerkit.common.log.Logger;
import com.testerkit.common.model.NodeInfo;
import com.testerkit.common.utils.SleepUtil;
import com.testerkit.common.utils.StringUtil;
import com.testerkit.uia.BaseContext;
import com.testerkit.uia.utils.dumps.AXWindowHelpers;

import java.util.ArrayList;
import java.util.List;


/**
 * @author able
 * @fix cmlanche
 * 单例
 */
public class WatcherManager {


    static List<WatcherScene> watchersTemp = new ArrayList<WatcherScene>();


    private WatcherContext context = new WatcherContext();


    private final long PAUSE_INTERVAL = 1 * 1000;
    private final long MONITOR_ACTION_INTERVAL = 3 * 1000;
    private volatile int timesPausing = 1;
    private volatile int timesGo = 1;
    private volatile boolean isPausing = false;
    private volatile boolean isRunning = false;

    /**
     * 初始化Watcher Manager
     */
    private WatcherManager() {
        //获取系统包名 在外面设置context
        WatcherConfig.load();
    }

    private static class SingletonHolder {
        public final static WatcherManager instance = new WatcherManager();
    }

    public static WatcherManager getInstance() {
        return WatcherManager.SingletonHolder.instance;
    }


    /**
     * 该方法里面不需要root信息
     */
    private void alwaysMonitor() {
        try {
//            if (context.getTypeMonitor() == TypeMonitor.SWJ) {
//                this.logic.rebootMonitor();
//            }
        } catch (Exception e) {
            Logger.error(String.format("monitor[%s]--->alwaysMonitor exception:%s", timesGo, e.getMessage()), e);
        }
    }

    private void sometimesMonitor() {
        try {
            AccessibilityNodeInfo[] tempRoots = context.getRoots();
            if (tempRoots == null || tempRoots.length == 0 || tempRoots[0] == null) {
                Logger.info(String.format("monitor[%s]--->sometimesMonitor,no root", timesGo));
                return;
            }
//            this.logic.getExecutor().textListenService(tempRoots);
            BaseContext.getInstance().getDevice().wake();
        } catch (Exception e) {
            Logger.error(String.format("monitor[%s]--->sometimesMonitor exception:%s", timesGo, e.getMessage()), e);
        }
    }

    public void start() {
        if (isRunning) {
            return;
        }
        isRunning = true;
        timesGo = timesPausing = 1;
        while (isRunning) {
            // alwaysMonitor();
            if (isPausing) {
                Logger.info(String.format("monitor[%s]--->pausing...", timesGo));
                timesPausing = timesGo;
                SleepUtil.sleep(PAUSE_INTERVAL);
                continue;
            }
            runOnce(true);
            SleepUtil.sleep(MONITOR_ACTION_INTERVAL);
        }
        Logger.info(String.format("monitor[%s]--->over!!!", timesGo));
    }

    private synchronized void runOnce(boolean pauseWork) {
        long startTime = System.currentTimeMillis();
        try {
            context.reSetRoots();//每次都会获取最新的
            sometimesMonitor();
            if (Logger.IS_OPEN) {
                logTimeElapsed(startTime, "reSetRoots");
            }
            AccessibilityNodeInfo[] tempRoots = context.getRoots();
            if (tempRoots == null || tempRoots.length == 0 || tempRoots[0] == null) {
                Logger.info(String.format("monitor[%s]--->no root", timesGo));
                return;
            }
            //实时获取当前包名
            String curPackage = AXWindowHelpers.getRootPackageName(tempRoots[0]);
            if (StringUtil.isEmpty(curPackage)) {
                Logger.info(String.format("monitor[%s]--->no curPackage", timesGo));
                return;
            }
            if (Logger.IS_OPEN) {
                logTimeElapsed(startTime, "getCurrentPackageName");
            }
            context.setPackageCurrent(curPackage);

            watchersTemp.clear();
            for (WatcherScene ws : WatcherConfig.WATCHERS) {
                if (isPausing && pauseWork) {
                    return;
                }
                ws.setTimesGo(timesGo);
                if (ws.isMeetPre()) {
                    watchersTemp.add(ws);
                }
            }
            if (Logger.IS_OPEN) {
                logTimeElapsed(startTime, "isMeetPre");
            }
            if (watchersTemp.size() == 0) {
                Logger.info(String.format("monitor[%s]--->no need strategy,curPackage:[%s]", timesGo, curPackage));
                return;
            }

            //搜索策略
            List<NodeInfo> nodes = WatcherConfig.SEARCH.isMatch(context.getDumpInfo());
            int size = nodes == null ? 0 : nodes.size();
            Logger.info(String.format("monitor[%s]--->go watcher search,result size:[%s]", timesGo, size));
            if (Logger.IS_OPEN && size != 0) {
                for (NodeInfo n : nodes) {
                    Logger.info(String.format("monitor[%s]--->tree:[%s]", timesGo, n));
                }
            }
            //根据结果执行动作
            for (WatcherScene ws : watchersTemp) {
                if (isPausing && pauseWork) {
                    return;
                }
                boolean isThis = ws.isThis(nodes);
                if (isThis) {
                    ws.doAction();
                    return;
                }
            }
        } catch (Exception e) {
            Logger.error(String.format("monitor[%s]--->exception:", timesGo), e);
        } finally {
            logTimeElapsed(startTime, "runOnce");
            timesGo = timesGoPlus(timesGo);
        }
    }

    private void logTimeElapsed(long startTime, String flag) {
        Logger.info(String.format("monitor[%s]--->[%s],time elapsed:[%s]ms", timesGo, flag, (System.currentTimeMillis() - startTime)));
    }

    private int timesGoPlus(int times) {
        if (times > 2147483647) {
            times = 101;//每个场景的执行次数最大值不能超过100
        }
        return times + 1;
    }

    public void stop() {
        isRunning = false;
        SleepUtil.sleep(MONITOR_ACTION_INTERVAL);
    }

    public void runTimes(int times) {
        if (times <= 0 || isAutoClick == false) {
            return;
        }
        Logger.info(String.format("monitor[%s]--->runTimes[%s] in,timesPausing[%s],isPausing[%s]", timesGo, times, timesPausing, isPausing));
        //临时保存是否暂停状态
        boolean temp = isPausing;
        if (temp == false) {
            this.isPausing = true;
        }
        //等待暂停
        while (timesGo != timesPausing) {
            SleepUtil.sleep(100L);
        }
        for (int i = 0; i < times; i++) {
            runOnce(false);
        }
        //恢复是否暂停状态
        switchPause(temp);
        Logger.info(String.format("monitor[%s]--->runTimes[%s] out,timesPausing[%s],isPausing[%s]", timesGo, times, timesPausing, isPausing));
    }

    public synchronized void switchPause(boolean isPausing) {
        if (isAutoClick == false) {
            return;
        }
        this.isPausing = isPausing;
        if (this.isPausing) {
            timesPausing = timesGo;
        }
    }

    private volatile boolean isAutoClick = true;

    public synchronized void switchAutoClickDialog(boolean isAutoClick) {
        this.isAutoClick = isAutoClick;
        this.isPausing = !isAutoClick;
        if (this.isPausing == true) { // 等待停止，6秒超时
            long startTime = System.currentTimeMillis();
            while (timesPausing != timesGo && (System.currentTimeMillis() - startTime < 6 * 1000)) {
                SleepUtil.sleep(1);
            }
        }
    }

    /**
     * 是否为暂停状态
     *
     * @return
     */
    public boolean isPausing() {
        return this.isPausing;
    }

    public WatcherContext getContext() {
        return context;
    }
}

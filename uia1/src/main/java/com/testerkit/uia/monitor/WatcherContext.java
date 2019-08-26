package com.testerkit.uia.monitor;

import android.view.accessibility.AccessibilityNodeInfo;

import com.testerkit.common.constants.PackagesAndroid;
import com.testerkit.common.log.Logger;
import com.testerkit.common.model.UIDumpInfo;
import com.testerkit.uia.BaseContext;
import com.testerkit.uia.utils.dumps.XMLHierarchy;

import java.util.List;

public class WatcherContext {

    private String packageCurrent = "";
    private AccessibilityNodeInfo[] roots;


    public String getPackageCurrent() {
        return packageCurrent;
    }

    public void setPackageCurrent(String packageCurrent) {
        this.packageCurrent = packageCurrent;
    }

    public synchronized AccessibilityNodeInfo[] getRoots() {
        return roots;
    }

    public void setRoots(AccessibilityNodeInfo[] roots) {
        this.roots = roots;
    }


    public boolean isSysPackage() {
        boolean isSysPkg = PackagesAndroid.PACKAGES_SYSTEM.contains(this.packageCurrent)
                && !PackagesAndroid.PACKAGES_IGNORE.contains(this.packageCurrent);
        return isSysPkg;
    }

    public synchronized void reSetRoots() {
        try {
            List<AccessibilityNodeInfo> list = BaseContext.getInstance().getDevice().getRoots();
            this.roots = new AccessibilityNodeInfo[list.size()];
            list.toArray(this.roots);
            //this.roots = .toArray(new AccessibilityNodeInfo[]{});
        } catch (Exception e) {
            //uia2 可能出现异常
            Logger.error(String.format("monitor--->reSetRoots exception:%s", e.getMessage()), e);
        }

    }

    public UIDumpInfo getDumpInfo(){
        return XMLHierarchy.getDumpInfo(this.roots);
    }

}

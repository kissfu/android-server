package com.testerkit.common.watcher;

import com.testerkit.common.constants.PackagesAndroid;

public class WatcherContext {

    private String packageCurrent = "";
    private Object[] roots;

    public String getPackageCurrent() {
        return packageCurrent;
    }

    public void setPackageCurrent(String packageCurrent) {
        this.packageCurrent = packageCurrent;
    }

    public Object[] getRoots() {
        return roots;
    }

    public void setRoots(Object[] roots) {
        this.roots = roots;
    }


    public boolean isSysPackage() {
        boolean isSysPkg = PackagesAndroid.PACKAGES_SYSTEM.contains(this.packageCurrent)
                && !PackagesAndroid.PACKAGES_IGNORE.contains(this.packageCurrent);
        return isSysPkg;
    }
}

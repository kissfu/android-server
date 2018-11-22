/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.testerkit.uia.model.serach;

//import static io.appium.uiautomator2.model.internal.NativeAndroidBySelector.SELECTOR_ACCESSIBILITY_ID;
//import static io.appium.uiautomator2.model.internal.NativeAndroidBySelector.SELECTOR_ANDROID_UIAUTOMATOR;
//import static io.appium.uiautomator2.model.internal.NativeAndroidBySelector.SELECTOR_CLASS;
//import static io.appium.uiautomator2.model.internal.NativeAndroidBySelector.SELECTOR_NATIVE_ID;
//import static io.appium.uiautomator2.model.internal.NativeAndroidBySelector.SELECTOR_XPATH;

import com.testerkit.uia.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Mechanism used to locate elements within a document. In order to create your own locating
 * mechanisms, it is possible to subclass this class and override the protected methods as
 * required.
 */
public abstract class By {

    public final static String REGULAR = "/";
    protected ByOption option = ByOption.REQUIRED;


    public abstract String getElementLocator();

    protected boolean checkCriteria(String value) {
        if (this.option == null || this.option == ByOption.IGNORED) {
            return true;
        }
        return false;
    }

    //region public

    public static boolean isMatch(Pattern criteria, String value) {
        if (criteria == null) {
            return true;
        }
        return criteria.matcher(value != null ? value : "").matches();
    }



    //endregion

    //region Override

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        By by = (By) o;

        return toString().equals(by.toString());
    }

    public ByOption getOption() {
        return option;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public String toString() {
        // A stub to prevent endless recursion in hashCode()
        return "[unknown locator]";
    }

    //endregion

    //region  class BY...

    public static class ByName extends By {
        private final String name;

        public ByName() {
            name = "";
        }

        public ByName(String name) {
            this.name = name;
        }

        @Override
        protected boolean checkCriteria(String value) {
            String criteria = name;
            if (super.checkCriteria(criteria)) {
                return true;
            }
            if (StringUtils.isNullOrEmpty(criteria)) {
                return true;
            }

            return criteria.equals(value);

        }

        @Override
        public String getElementLocator() {
            return name;
        }

        @Override
        public String toString() {
            return "By.name: " + name;
        }
    }

    public static class ByClass extends By {
        private final String clazz;

        public ByClass() {
            clazz = "";
        }

        public ByClass(String clazz) {
            this.clazz = clazz;
        }

        private String specialHandle() {
            String newClazz = "";
            if (StringUtils.isNullOrEmpty(clazz)) {
                return newClazz;
            }
            // 兼容android.widget.TextView和android.support.v7.widget.AppCompatTextView
            // 兼容android.widget.Button和android.support.v7.widget.AppCompatButton
            // 兼容com.android.internal.policy.impl.PhoneWindow$DecorView和com.android.internal.policy.PhoneWindow$DecorView

            if (clazz.equalsIgnoreCase("android.widget.TextView")
                    || clazz.equalsIgnoreCase("android.support.v7.widget.AppCompatTextView")) {
                newClazz = "android*widget*TextView";
            }
            if (clazz.equalsIgnoreCase("android.widget.Button")
                    || clazz.equalsIgnoreCase("android.support.v7.widget.AppCompatButton")) {
                newClazz = "android*widget*Button";
            }
            if (clazz.equalsIgnoreCase("com.android.internal.policy.impl.PhoneWindow$DecorView")
                    || clazz.equalsIgnoreCase("com.android.internal.policy.PhoneWindow$DecorView")
                    || clazz.equalsIgnoreCase("com.android.internal.policy.MultiPhoneWindow$MultiPhoneDecorView")) {
                newClazz = "com.android.internal.policy*PhoneWindow$*DecorView";
            }

            if (StringUtils.isNullOrEmpty(newClazz)) {
                return clazz;
            }
            return newClazz;
        }

        @Override
        protected boolean checkCriteria(String value) {
            String criteria = clazz;
            if (super.checkCriteria(criteria)) {
                return true;
            }
            if (StringUtils.isNullOrEmpty(criteria)) {
                return true;
            }

            if (criteria.startsWith(REGULAR) && criteria.endsWith(REGULAR)) {
                criteria = criteria.substring(1, criteria.lastIndexOf(REGULAR));
                return By.isMatch(Pattern.compile(criteria), value);

            }
            criteria = specialHandle();
            return By.isMatch(Pattern.compile(criteria), value);

        }

        @Override
        public String getElementLocator() {
            return clazz;
        }

        @Override
        public String toString() {
            return "By.clazz: " + clazz;
        }
    }

    public static class ByXPath extends By {
        private final List<XPathInfo> xpathes;

        public ByXPath() {
            xpathes = new ArrayList<>();
        }

        public ByXPath(List<XPathInfo> xpathList) {
            this.xpathes = xpathList;
        }


        @Override
        protected boolean checkCriteria(String value) {
            List<XPathInfo> criteria = xpathes;
            if(super.checkCriteria(value)){
                return true;
            }
            if(criteria == null || criteria.size() == 0){
                return true;
            }



            return true;
        }

        @Override
        public String getElementLocator() {
            return StringUtils.join(xpathes.toArray(), ",");
        }

        @Override
        public String toString() {
            return "By.xpathList: " + getElementLocator();
        }
    }

    public static class ByPackageName extends By {
        private final String packageName;

        public ByPackageName() {
            packageName = "";
        }

        public ByPackageName(String packageName) {
            this.packageName = packageName;
        }

        @Override
        public String getElementLocator() {
            return packageName;
        }

        @Override
        public boolean checkCriteria(String value) {
            String criteria = packageName;
            if (super.checkCriteria(criteria)) {
                return true;
            }
            //如果查找时候，当前包名为空，或者待查找的包名为空都不参与查找,暂时不需要验证查找到的元素包名
            if (StringUtils.isNullOrEmpty(criteria)) {
                return true;
            }

            return criteria.equals(value);
        }

        @Override
        public String toString() {
            return "By.ByPackageName: " + packageName;
        }
    }

    public static class ByText extends By {
        private final String text;

        public ByText() {
            text = "";
        }

        public ByText(String text) {
            this.text = text;
        }


        @Override
        public String getElementLocator() {
            return text;
        }

        @Override
        public boolean checkCriteria(String value) {
            String criteria = text;
            if (super.checkCriteria(criteria)) {
                return true;
            }
            if (StringUtils.isNullOrEmpty(criteria)) {
                return true;
            }

            if (criteria.startsWith(REGULAR) && criteria.endsWith(REGULAR)) {
                criteria = criteria.substring(1, criteria.lastIndexOf(REGULAR));
                return By.isMatch(Pattern.compile(criteria), value);

            }
            criteria = specialHandle(criteria);
            value = specialHandle(value);
            return By.isMatch(Pattern.compile(criteria), value);
        }

        @Override
        public String toString() {
            return "By.text: " + text;
        }

        private String specialHandle(String str) {
            if (StringUtils.isNullOrEmpty(str)) {
                return "";
            }
            return str.replaceAll("[\r\n\\t\\s]", "");
        }
    }

    //endregion

    //region enum option...

    public static enum ByOption {
        REQUIRED,
        IGNORED,
        FILTER
    }

    public static enum XPathOption {
        ALL,
        SIMPLE,
        NO_ID,
        NO_TEXT,
        NO_ID_TEXT
    }

    //endregion
}

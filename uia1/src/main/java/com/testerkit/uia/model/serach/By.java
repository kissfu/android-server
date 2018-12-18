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


import com.testerkit.uia.utils.ClazzUtil;
import com.testerkit.uia.utils.Logger;
import com.testerkit.uia.utils.RegExUtil;
import com.testerkit.uia.utils.StringUtils;
import com.testerkit.uia.utils.dumps.UIDumpInfo;
import com.testerkit.uia.utils.dumps.XMLHierarchy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Mechanism used to locate elements within a document. In order to create your own locating
 * mechanisms, it is possible to subclass this class and override the protected methods as
 * required.
 */
public abstract class By {


    protected ByOption option = ByOption.REQUIRED;

    public abstract String getElementLocator();

    public abstract boolean isMatch(Object value);

    protected boolean checkCriteria() {
        if (this.option == null || this.option == ByOption.IGNORED) {
            return true;
        }
        if (StringUtils.isNullOrEmpty(getElementLocator())) {
            return true;
        }
        return false;
    }

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
        public boolean isMatch(Object value) {
            String criteria = name;
            if (super.checkCriteria()) {
                return true;
            }
            if (value == null) {
                return false;
            }
            return criteria.equals(value.toString());
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

        @Override
        public boolean isMatch(Object value) {
            String criteria = clazz;
            if (super.checkCriteria()) {
                return true;
            }
            if (value == null) {
                return false;
            }
            if (criteria.startsWith(RegExUtil.REGULAR) && criteria.endsWith(RegExUtil.REGULAR)) {
                criteria = criteria.substring(1, criteria.lastIndexOf(RegExUtil.REGULAR));
                return RegExUtil.isMatch(criteria, value.toString());

            }
            criteria = ClazzUtil.compatibleRegEx(criteria);
            return RegExUtil.isMatchWithStar(criteria, value.toString());

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

        public List<XPathInfo> getXpathes() {
            return xpathes;
        }

        @Override
        public boolean isMatch(Object value) {
            List<XPathInfo> criteria = xpathes;
            if (super.checkCriteria()) {
                return true;
            }
            //升序排列
            Collections.sort(xpathes);

            UIDumpInfo dumpInfo = null;
            if (value instanceof UIDumpInfo) {
                dumpInfo = (UIDumpInfo) value;
            }


            //TODO xpath match
            for (XPathInfo xp : xpathes) {
                if (xp.getOption() == XPathOption.SIMPLE) {
                    continue;
                }
                List<String> arr = XMLHierarchy.findByXpath(dumpInfo.getUiXml(), xp.getXpath());
                //if(arr.)

            }

            return true;
        }

        @Override
        public String getElementLocator() {
            if (xpathes == null || xpathes.size() == 0) {
                return "";
            }
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
        public boolean isMatch(Object value) {
            String criteria = packageName;
            if (super.checkCriteria()) {
                return true;
            }
            if (value == null) {
                return false;
            }

            return criteria.equals(value.toString());
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
        public boolean isMatch(Object value) {
            String criteria = text;
            if (super.checkCriteria()) {
                return true;
            }
            if (value == null) {
                return false;
            }
            if (criteria.startsWith(RegExUtil.REGULAR) && criteria.endsWith(RegExUtil.REGULAR)) {
                criteria = criteria.substring(1, criteria.lastIndexOf(RegExUtil.REGULAR));
                return RegExUtil.isMatch(criteria, value.toString());

            }
            return RegExUtil.isMatchWithStar(criteria, value.toString());
        }

        @Override
        public String toString() {
            return "By.text: " + text;
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
        ALL(0),
        SIMPLE(4),
        NO_ID(2),
        NO_TEXT(1),
        NO_ID_TEXT(3);

        private final int value;

        XPathOption(final int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }

    //endregion
}

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

/**
 * Mechanism used to locate elements within a document. In order to create your own locating
 * mechanisms, it is possible to subclass this class and override the protected methods as
 * required.
 */
public abstract class By {

    protected ByOption option = ByOption.REQUIRED;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        By by = (By) o;

        return toString().equals(by.toString());
    }



    public abstract String getElementLocator();

    public  ByOption getOption(){
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

    public static class ById extends By {
        private final String id;

        public ById(String id) {
            this.id = id;
        }

        @Override
        public String getElementLocator() {
            return id;
        }

        @Override
        public String toString() {
            return "By.id: " + id;
        }
    }

    public static class ByClass extends By {
        private final String clazz;

        public ByClass(String clazz) {
            this.clazz = clazz;
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
        private final String xpathExpression;

        public ByXPath(String xpathExpression) {
            this.xpathExpression = xpathExpression;
        }

        @Override
        public String getElementLocator() {
            return xpathExpression;
        }

        @Override
        public String toString() {
            return "By.xpath: " + xpathExpression;
        }
    }

    public static class ByText extends By {
        private final String text;

        public ByText(String text) {
            this.text = text;
        }

        @Override
        public String getElementLocator() {
            return text;
        }

        @Override
        public String toString() {
            return "By.text: " + text;
        }
    }

    public static enum ByOption{
        REQUIRED,
        IGNORED,
        FILTER
    }

    public static enum XPathOption{
        ALL,
        NO_ID,
        NO_TEXT,
        NO_ID_TEXT
    }
}

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

package com.testerkit.uia.utils.dumps;



import com.testerkit.common.search.by.ByName;
import com.testerkit.common.utils.StringUtil;
import com.testerkit.uia.model.Session;
import com.testerkit.uia.utils.Logger;

import java.util.regex.Pattern;



public class LocationHelpers {
    /**
     * java_package : type / name
     * <p>
     * com.example.Test:id/enter
     * <p>
     * ^[a-zA-Z_] - Java package must start with letter or underscore
     * [a-zA-Z0-9\._]* - Java package may contain letters, numbers, periods and
     * underscores : - : ends the package and starts the type [^\/]+ - type is
     * made up of at least one non-/ characters \\/ - / ends the type and starts
     * the name [\S]+$ - the name contains at least one non-space character and
     * then the line is ended
     * <p>
     * Example:
     * http://java-regex-tester.appspot.com/regex/5f04ac92-f9aa-45a6-b1dc-e2c25fd3cc6b
     */
    private static final Pattern resourceIdRegex = Pattern
            .compile("^[a-zA-Z_][a-zA-Z0-9._]*:[^/]+/[\\S]+$");

    public static String rewriteIdLocator(ByName by) {
        String locator = by.getElementLocator();

        if (!resourceIdRegex.matcher(by.getElementLocator()).matches()) {
            // not a fully qualified resource id
            // transform "textToBeChanged" into:
            // com.example.android.testing.espresso.BasicSample:id/textToBeChanged
            // it's prefixed with the app package.
            locator = Session.capabilities.get("appPackage") + ":id/" + by.getElementLocator();
            Logger.debug("Updated findElement locator strategy: " + locator);
        }
        return locator;
    }

    public static String getID(String viewIdResourceName){
        String id = "";
        if (StringUtil.isNullOrEmpty(viewIdResourceName) ) {
            return id;
        }
        if (viewIdResourceName.contains(":id/")) {
            id = viewIdResourceName.substring(viewIdResourceName.indexOf(":id/") + 4);
        }
        return id;
    }
}

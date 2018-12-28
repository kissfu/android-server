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

package com.testerkit.uia.model.settings;

//import io.appium.uiautomator2.model.Session;
//import io.appium.uiautomator2.utils.Logger;

import com.testerkit.uia.model.Session;
import com.testerkit.common.log.Logger;

public class AllowInvisibleElements extends AbstractSetting<Boolean> {

    private static final String SETTING_NAME = "allowInvisibleElements";

    public AllowInvisibleElements() {
        super(Boolean.class, SETTING_NAME);
    }

    @Override
    public Boolean getValue() {
        Object allowInvisibleElements = Session.capabilities.get(getName());
        return allowInvisibleElements != null && (boolean) allowInvisibleElements;
    }

    @Override
    protected void apply(Boolean allowInvisibleElements) {
        Logger.debug("Dummy setting.");
    }

}

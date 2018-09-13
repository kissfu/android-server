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

package com.testerkit.uia.model;

/**
 * An enumeration that mirrors {@link android.view.Surface}.
 * KeyEnum.RIGHT.ordinal() 从0开始返回枚举的index
 * KeyEnum.RIGHT.name() 返回该对象的字符串，即“RIGHT”--->.toString()
 */
public enum KeyEnum {
    MENU(82), BACK(4), HOME(3), SEARCH(84), ENTER(66), LEFT(21), RIGHT(22),UP(19),DOWN(20),CENTER(23);

    private final int value;

    KeyEnum(final int value) {
        this.value = value;
    }

    public static KeyEnum fromInteger(final int x) {
        for (KeyEnum em : values()) {
            if (em.value == x) {
                return em;
            }
        }
        throw new IllegalArgumentException(String.format("Orientation value '%s' is not supported", x));
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {

        switch (this) {
            case MENU:
                return "菜单";
            case BACK:
                return "返回";
            default:
                return "未知(" + this + ")";
        }
    }

    public static void main(String[] args) {
        System.out.printf(KeyEnum.valueOf("dd")+ "");
    }
}

package com.testerkit.common.json;


import com.testerkit.common.enums.KeyEnum;
import com.testerkit.common.utils.StringUtil;

/**
 * "key":{
 * "keyName":"HOME",
 * "keyCode":3,
 * "metaState":0,
 * "flags":-1
 * }
 */
public class KeyJson {
    private KeyEnum keyName;
    private int keyCode;
    private String keyCodeName;
    private int metaState;
    private int flags;
    private boolean longPress;

    public KeyEnum getKeyName() {
        return keyName;
    }

    public void setKeyName(KeyEnum keyName) {
        this.keyName = keyName;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }

    public String getKeyCodeName() {
        return keyCodeName;
    }

    public void setKeyCodeName(String keyCodeName) {
        this.keyCodeName = keyCodeName;
    }

    public int getMetaState() {
        return metaState;
    }

    public void setMetaState(int metaState) {
        this.metaState = metaState;
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public boolean isLongPress() {
        return longPress;
    }

    public void setLongPress(boolean longPress) {
        this.longPress = longPress;
    }

    public String toDescription() {
        StringBuilder sb = new StringBuilder();
        if (keyCode != -1) {
            KeyEnum keyEnum = KeyEnum.fromInteger(keyCode);
            String desc = (keyEnum == KeyEnum.NONE ? keyCodeName : keyEnum.getDescription());
            sb.append("按键：" + desc);
        } else if (keyName != null) {
            sb.append("按键：" + keyName.getDescription());
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "KeyJson{" +
                "keyName=" + keyName +
                ", keyCode=" + keyCode +
                ", metaState=" + metaState +
                ", flags=" + flags +
                ", longPress=" + longPress +
                '}';
    }
}

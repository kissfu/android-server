package com.testerkit.common.json;


import com.testerkit.common.enums.KeyEnum;

/**
 *   "key":{
 *     "keyName":"HOME",
 *     "keyCode":3,
 *     "metaState":0,
 *     "flags":-1
 *   }
 */
public class KeyJson {
    private KeyEnum keyName;
    private int keyCode;
    private int metaState;
    private int flags;

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

    @Override
    public String toString() {
        return String.format("keyName=%s,keyCode=%s",keyName,keyCode);
    }
}

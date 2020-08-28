package com.testerkit.uia2.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.text.TextUtils;

import com.testerkit.common.log.Logger;

/**
 * 剪切板读写工具
 */
public class ClipBoardUtil {
    /**
     * 获取剪切板内容
     *
     * @return
     */
    public static String paste(Context context) {
        try {
            ClipboardManager manager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            if (manager != null) {
                if (manager.hasPrimaryClip() && manager.getPrimaryClip().getItemCount() > 0) {
                    CharSequence addedText = manager.getPrimaryClip().getItemAt(0).getText();
                    String addedTextString = String.valueOf(addedText);
                    if (!TextUtils.isEmpty(addedTextString)) {
                        return addedTextString;
                    }
                }
            }
        }catch (Exception e){
            Logger.error(e.getMessage(),e);
        }
        return "";
    }

    /**
     * 清空剪切板
     */
    public boolean clear(Context context) {
        ClipboardManager manager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        if (manager != null) {
            try {
                manager.setPrimaryClip(manager.getPrimaryClip());
                manager.setPrimaryClip(ClipData.newPlainText("", ""));
            } catch (Exception e) {
                Logger.error(e.getMessage(),e);
                return false;
            }
        }
        return true;
    }
}
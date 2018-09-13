package com.testerkit.uia.interfaces;

import android.view.accessibility.AccessibilityNodeInfo;

import com.testerkit.uia.model.ScreenSize;

import java.util.List;

/**
 * Created by able on 2018/2/13.
 */

public interface IDevice {


   Object getUiDevice();
   /**
    * 获取界面的Rotation
    * @return  ROTATION_0(0), ROTATION_90(1), ROTATION_180(2), ROTATION_270(3)
    */
   int getRotation();



   void wake() throws  android.os.RemoteException;

   ScreenSize getScreenSize();

   List<AccessibilityNodeInfo> getRoots();

   //List<String> getRootsPackageName


   /**
    * 用反射实现点击、长按
    * @param x 坐标点x值
    * @param y 坐标点y值
    * @param holdTime 长按时长
    * @return true/false 长按成功/失败
    */
   boolean click(int x, int y,long holdTime);


   /**
    * 根据给定的按键名称，执行按键。
    * @param keyName 按键名称。
    * @return true/false 表示按键是否执行成功。
    */
   boolean pressKey(String keyName);

   /**
    * 给定keycode，由UIDevice执行press keycode.
    * @param keycode
    * @return true/false 表示按键是否执行成功。
    */
   boolean pressKey(int keycode);


}

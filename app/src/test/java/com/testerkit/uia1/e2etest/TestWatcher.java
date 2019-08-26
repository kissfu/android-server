package com.testerkit.uia1.e2etest;

import com.testerkit.common.utils.RegExUtil;

import org.junit.Test;

public class TestWatcher {


    @Test
    public void isMatchRegx() {
        String regexText = "(取消)|(完成)|(.*无视风险安装.*)|(提示：若非本人行为，建议拒绝)|(允许)|(允许(\\(\\d{1,}秒\\)){0,})|(允許)|(Allow)|(ALLOW)|(总是允许)|(始终允许)|(允许一次)|(设为允许)|(拒绝)|(拒絕)|(Deny)|(.*风险.*)|(.*安全.*)|(.*威胁.*)|(.*警示.*)|(继续安装)|(禁止后不再询问)|(允许使用这台计算机进行调试)|(allow from this computer)|(允許透過這台電腦進行)|(确定)|(OK)|(Ok)|(ok)|(確定)|(确认)|(確認)|(安全警告)|(好)|(检测.*有敏感权限.*是否进行设置.*)|(.*需要.*是否允许.*你可以在手机管家-权限管理更改权限设置。)|(下一步)|(下壹步)|(仅充电)|(删除)|(立即删除)|(清除)|(安装)|(安裝)|(Install)|(继续安装旧版本)|(覆盖安装)|(重新安装)|(我已充分了解该风险.*继续安装)|(替换)|(应用程序操作)|(应用程序权限)|(我已充分了解该风险，继续安装)|(不再显示此内容)|(不再提示)|(不再提醒)|(下次不再提示)|(保存为默认设置,以后不再提醒)|(下次不再提醒)|(不再顯示此內容)|(保存為默認設置,以後不再提醒)|(记住)|(記住)|(记住我的选择)|(记住我的选择。)|(記住我的選擇)|(记住此选择)|(記住此選擇)|(下次记住我的选择)|(下次記住我的選擇)|(下次不再询问)|(下次不再詢問)|(24小时内记住选择)|(不允许后不再询问)|(拒绝后下次安装不再提示)|(夜间)|(.*更新版本.*vivo.*)|(.*系统升级.*)|(新版本NX.*下载完成.*)|(电量低于.*)|([^继续安装])|(软件商店安装)|(.*已停止运行.*)|(.*已停止.*)|(.*意外停止.*)|(.*无响应.*)|(.*未响应.*)|(.*没有响应.*)|(.*isn't responding.*)|(.*has stopped.*)|(重新打开应用)|(关闭)|(重启应用)|(关闭应用)|(cancle)|(Cancle)|(close)|(Close)|(關閉)|(Always)|(始终)|(总是)|(總是)|(始終)|(网络连接失败)|(当前网络不稳定.*)|(加载失败了.*重试下吧)|(数据异常.*请重试)|(您的网络好像太不给力.*请稍后再试)|(网络请求失败.*)|(页面加载失败)|(抱歉.*网络管理员开小差了.*请刷新)|(出错了.*请稍后再试.*)|(点击重试)|(重新加载)|(刷新)|(重试)|(刷新试试)|(猎豹清理大师)";
        String regexId = "(left_shortcut)|(bottom_button_layout)";
        String regexClasses = "(android.widget.ImageView)|(android.widget.Button)|(android.widget.LinearLayout)|(android.widget.TextView)|(amigo.widget.AmigoButton)";

        System.out.println("text is match:" + RegExUtil.isMatch(regexText, "取消"));
        System.out.println("id is match:" + RegExUtil.isMatch(regexId, "button2"));
        System.out.println("class is match:" + RegExUtil.isMatch(regexClasses, "android.widget.Button"));


    }


    @Test
    public void combination() {

        /**
         * 算法说明：当n大于2时，n个数的全组合一共有(2^n)-1种。
         * 当对n个元素进行全组合的时候，可以用一个n位的二进制数表示取法。
         * 1表示在该位取，0表示不取。例如，对ABC三个元素进行全组合，  100表示取A，010表示取B，001表示取C，101表示取AC  110表示取AB，011表示取BC，111表示取ABC
         * 注意到表示取法的二进制数其实就是从1到7的十进制数
         * 推广到对n个元素进行全排列，取法就是从1到2^n-1的所有二进制形式
         * 要取得2^n，只需将0xFFFFFFFF左移32-n位，再右移回来就可以了。
         */
        String str[] = {"A", "B", "C"};

        int nCnt = str.length;

        int nBit = (0xFFFFFFFF >>> (32 - nCnt));

        for (int i = 1; i <= nBit; i++) {
            String group = "";
            for (int j = 0; j < nCnt; j++) {
                if ((i << (31 - j)) >> 31 == -1) {
                    group += str[j];
                    //System.out.print(str[j]);
                }
            }
            System.out.println(group);
        }
    }


}


package com.xiaomi.xmplugindemo;

import android.content.Context;
import android.content.Intent;

import com.xiaomi.plugin.core.XmPluginPackage;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.device.api.IXmPluginMessageReceiver;
import com.xiaomi.smarthome.device.api.MessageCallback;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;

/**
 * 所有插件入口函数，必须实现
 */
public class MessageReceiver implements IXmPluginMessageReceiver {
    public static final String MODEL = "xiaomi.demo.v1";

    @Override
    public boolean handleMessage(Context context, XmPluginPackage xmPluginPackage, int type,
            Intent intent,
            DeviceStat deviceStat) {
        switch (type) {
            case LAUNCHER: {// 启动入口
                XmPluginHostApi.instance().startActivity(context, xmPluginPackage, intent,
                        deviceStat.did, MainActivity.class);
                return true;
            }

            default:
                break;
        }
        return false;
    }

    @Override
    public boolean handleMessage(Context context, XmPluginPackage xmPluginPackage, int type,
            Intent intent, DeviceStat deviceStat, MessageCallback callback) {
        // TODO Auto-generated method stub
        return false;
    }

}


package com.xiaomi.smarthome.device.api;

import android.content.Context;
import android.content.Intent;

import com.xiaomi.plugin.core.XmPluginPackage;

public interface IXmPluginMessageReceiver {
    /**
     * ApiLevel:1
     */
    public static final int LAUNCHER = 1;// 入口消息

    // push消息类型 数据为Json字符串，JSONObject msgData = new
    // JSONObject(intent.getStringExtra("data"));
    /**
     * ApiLevel:1
     */
    public static final int PUSH_MESSAGE = 2;

    /**
     * ApiLevel:6
     */
    public static final int MSG_LAUNCHER = 1;

    /**
     * ApiLevel:6
     */
    public static final int MSG_PUSH_MESSAGE = 2;

    /**
     * ApiLevel:6
     */
    public static final int MSG_GET_SCENE_VALUE = 3;

    /**
     * ApiLevel:6
     */
    public static final int MSG_CUSTOM_START = 10000;

    /**
     * 所有插件必须实现该接口，并且在type=LAUNCHER时，启动入口页面 比如下MiTVPageActivity为入口activity
     * switch (type) { case LAUNCHER: {// 启动入口
     * XmPluginHostApi.instance().startActivity(xmPluginPackage, intent,
     * deviceStat.did, MiTVPageActivity.class); return true; } default: break; }
     * 且 必须在AndroidManifest.xml文件中application下指定 <meta-data android:name="model"
     * android:value="xiaomi.tvbox.v1"/> <meta-data
     * android:name="message_handler"
     * android:value="com.xiaomi.plug.mitv.MitvMessageReceiver"/>
     * 消息处理接口，处理外部传递过来的消息 启动入口必须在这里指定<br/>
     * <br/>
     * ApiLevel:1
     * 
     * @param xmPluginPackage
     * @param intent
     * @param deviceStat
     * @return
     */
    public boolean handleMessage(Context context, XmPluginPackage xmPluginPackage, int type,
            Intent intent, DeviceStat deviceStat);

    /**
     * ApiLevel:2 功能同上，异步调用完成后，通过callback 返回结果
     */
    public boolean handleMessage(Context context, XmPluginPackage xmPluginPackage, int type,
            Intent intent, DeviceStat deviceStat, MessageCallback callback);

}

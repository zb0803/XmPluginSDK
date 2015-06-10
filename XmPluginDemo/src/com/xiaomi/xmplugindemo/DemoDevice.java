
package com.xiaomi.xmplugindemo;

import android.util.Log;

import com.xiaomi.smarthome.device.api.BaseDevice;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;

import java.util.ArrayList;
import java.util.HashSet;

//设备功能处理
public class DemoDevice extends BaseDevice {
    public static final String MODEL = "xiaomi.demo.v1";
    
    // 缓存设备状态数据，每次进入不需要立即更新数据
    private static ArrayList<DemoDevice> DEVICE_CACHE = new ArrayList<DemoDevice>();

    // 先从缓存中获取Device，并更新DeviceStat
    public static synchronized DemoDevice getDevice(DeviceStat deviceStat) {
        for (DemoDevice device : DEVICE_CACHE) {
            if (deviceStat.did.equals(device.getDid())) {
                device.mDeviceStat = deviceStat;
                return device;
            }
        }

        DemoDevice device = new DemoDevice(deviceStat);
        DEVICE_CACHE.add(device);
        return device;
    }

    // 通过did获取Device
    public static synchronized DemoDevice getDevice(String did) {
        for (DemoDevice device : DEVICE_CACHE) {
            if (did.equals(device.getDid())) {
                return device;
            }
        }
        return null;
    }
    
    public int mRGB;
    public int mTemperature;
    public int mHumidity;

    private HashSet<DemoDeviceListener> mListeners = new HashSet<DemoDeviceListener>();

    private DemoDevice(DeviceStat deviceStat) {
        super(deviceStat);
    }

    public int getR() {
        int r = (mRGB & 0x00FF0000) >> 16;
        return r;
    }

    public int getG() {
        int g = (mRGB & 0x0000FF00) >> 8;
        return g;
    }

    public int getB() {
        int b = mRGB & 0x000000FF;
        return b;
    }

    public void setRGB(int rgb) {
        mRGB = rgb;
        triggerStatusUpdated();
    }

    public int getRGB() {
        return mRGB;
    }

    public interface DemoDeviceListener {
        void onStatusUpdated();
    }

    public void registerListener(DemoDeviceListener listener) {
        mListeners.add(listener);
    }

    public void unregisterListener(DemoDeviceListener listener) {
        mListeners.remove(listener);
    }

    void triggerStatusUpdated() {
        for (DemoDeviceListener listener : mListeners) {
            listener.onStatusUpdated();
        }
    }

    
    // 订阅属性变化，每次只维持3分钟订阅事件
    public void subscribeProperty(String[] props, Callback<Void> callback) {
        ArrayList<String> propList = new ArrayList<String>();
        for (String prop : props) {
            if (prop.startsWith("prop.")) {
                propList.add(prop);
            } else {
                propList.add("prop." + prop);
            }
        }
        XmPluginHostApi.instance()
                .subscribeDevice(getDid(), mDeviceStat.pid, propList, 3, callback);
    }
    
    // 订阅事件信息，每次只维持3分钟订阅事件
    public void subscribeEvent(String[] events, Callback<Void> callback) {
        ArrayList<String> eventList = new ArrayList<String>();
        for (String event : events) {
            if (event.startsWith("event.")) {
                eventList.add(event);
            } else {
                eventList.add("event." + event);
            }
        }
        XmPluginHostApi.instance().subscribeDevice(getDid(), mDeviceStat.pid, eventList, 3,
                callback);
    }

    // 收到订阅的信息
    public void onSubscribeData(String data) {
        Log.d(DemoDevice.MODEL, "DevicePush :" + data);
        //TODO 处理订阅信息
    }
}

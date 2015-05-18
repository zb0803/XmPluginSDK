
package com.xiaomi.xmplugindemo;

import com.xiaomi.smarthome.device.api.BaseDevice;
import com.xiaomi.smarthome.device.api.DeviceStat;

import java.util.HashSet;

//设备功能处理
public class DemoDevice extends BaseDevice {

    public int mRGB;
    public int mTemperature;
    public int mHumidity;

    private HashSet<DemoDeviceListener> mListeners = new HashSet<DemoDeviceListener>();

    public DemoDevice(DeviceStat deviceStat) {
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

}

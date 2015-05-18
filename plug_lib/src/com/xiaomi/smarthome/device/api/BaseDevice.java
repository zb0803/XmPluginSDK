
package com.xiaomi.smarthome.device.api;

import android.text.TextUtils;

import org.json.JSONArray;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * 抽象设备基类 </br> 插件设备继承该类，并完成具体设备的操作逻辑
 */
public class BaseDevice {
    protected DeviceStat mDeviceStat;
    ArrayList<WeakReference<StateChangedListener>> mStateChangedListeners = new ArrayList<WeakReference<StateChangedListener>>();

    public BaseDevice(DeviceStat deviceStat) {
        mDeviceStat = deviceStat;
    }

    /**
     * 状态改变监听接口
     */
    public interface StateChangedListener {
        public void onStateChanged(final BaseDevice device);
    }

    /**
     * 添加状态监听
     * 
     * @param listener
     */
    public void addStateChangedListener(StateChangedListener listener) {
        if (listener == null)
            return;
        for (WeakReference<StateChangedListener> ls : mStateChangedListeners) {
            if (ls.get() == listener)
                return;
        }
        mStateChangedListeners
                .add(new WeakReference<StateChangedListener>(
                        listener));

        // DevicePushListener.getInstance().registerListener(this);
    }

    /**
     * 移除状态监听
     * 
     * @param listener
     */
    public void removeStateChangedListener(StateChangedListener listener) {
        if (listener == null)
            return;

        for (int i = 0; i < mStateChangedListeners.size(); i++) {
            if (mStateChangedListeners.get(i).get() == listener) {
                mStateChangedListeners.remove(i);
                return;
            }

        }

        // DevicePushListener.getInstance().unregisterListener(this);
    }

    public void notifyStateChanged() {
        for (int i = 0; i < mStateChangedListeners.size(); i++) {
            StateChangedListener listener = mStateChangedListeners.get(i).get();
            if (listener != null)
                listener.onStateChanged(this);
        }
    }

    public static final int ERROR_CODE_ILLEGAL_PARAMS = -1;
    public static final int ERROR_CODE_ILLEGAL_ERROR_CODE_BUSY = -2;

    /**
     * 设备方法调用
     * 
     * @param method 方法名
     * @param params 参数，可以是一个集合Collection子类
     * @param callback 回调结果
     * @param parser
     */
    public <T> void callMethod(String method, Object[] params,
            final Callback<T> callback, final Parser<T> parser) {
        XmPluginHostApi.instance().callMethod(getDid(), method, params, callback, parser);
    }

    /**
     * 设备方法调用
     * 
     * @param method 方法名
     * @param params
     * @param callback 回调结果
     * @param parser
     */
    public <T> void callMethod(String method, JSONArray params,
            final Callback<T> callback, final Parser<T> parser) {
        XmPluginHostApi.instance().callMethod(getDid(), method, params, callback, parser);
    }

    /**
     * 获取设备固件升级信息
     * 
     * @param callback
     */
    public void checkDeviceUpdateInfo(final Callback<DeviceUpdateInfo> callback) {
        XmPluginHostApi.instance().getUpdateInfo(getModel(), getDid(), mDeviceStat.pid, callback);
    }

    /**
     * 返回设备id
     * 
     * @return
     */
    public String getDid() {
        return mDeviceStat.did;
    }

    /**
     * 返回设备名字
     * 
     * @return
     */
    public String getName() {
        return mDeviceStat.name;
    }

    /**
     * 返回设备ip
     * 
     * @return
     */
    public String getIp() {
        return mDeviceStat.ip;
    }

    /**
     * 返回设备mac地址
     * 
     * @return
     */
    public String getMac() {
        return mDeviceStat.mac;
    }

    /**
     * 返回model
     * 
     * @return
     */
    public String getModel() {
        return mDeviceStat.model;
    }

    /**
     * 返回model
     * 
     * @return
     */
    public String getParentId() {
        return mDeviceStat.parentId;
    }

    /**
     * 返回model
     * 
     * @return
     */
    public String getParentModel() {
        return mDeviceStat.parentModel;
    }

    public int getBindFlag() {
        return mDeviceStat.bindFlag;
    }

    public int getAuthFlag() {
        return mDeviceStat.authFlag;
    }

    /**
     * 是否是子设备，仅支持二级子设备
     * 
     * @return
     */
    public boolean isSubDevice() {
        return !TextUtils.isEmpty(mDeviceStat.parentId);
    }

    /**
     * 是否是绑定设备
     * 
     * @return
     */
    public boolean isBinded() {
        return mDeviceStat.bindFlag == 1;
    }

    public void updateDeviceStatus() {
        mDeviceStat = XmPluginHostApi.instance().getDeviceByDid(mDeviceStat.did);
    }

    public String getToken() {
        return mDeviceStat.token;
    }

    public DeviceStat deviceStat() {
        return mDeviceStat;
    }
}

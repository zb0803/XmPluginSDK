package com.xiaomi.smarthome.bluetooth;

import android.os.Bundle;

import java.util.UUID;
import com.xiaomi.smarthome.bluetooth.Response;
import com.xiaomi.smarthome.bluetooth.Response.BleNotifyResponse;
import com.xiaomi.smarthome.bluetooth.Response.BleReadResponse;
import com.xiaomi.smarthome.bluetooth.Response.BleWriteResponse;
import com.xiaomi.smarthome.bluetooth.Response.BleConnectResponse;

/**
 * ApiLevel: 13
 * Created by liwentian on 2015/10/29.
 */
public abstract class XmBluetoothManager {

    /**
     * ApiLevel: 13
     */
    protected static XmBluetoothManager instance = null;

    /**
     * ApiLevel: 13
     */
    public static XmBluetoothManager getInstance() {
        return instance;
    }

    /**
     * ApiLevel: 13
     * 通知智能家庭已绑定的设备
     * @param bundle
     */
    public abstract void notifyDeviceBinded(Bundle bundle);

    /**
     * ApiLevel: 13
     * 设备连接
     */
    public abstract void connect(String mac, final BleConnectResponse response);

    /**
     * ApiLevel: 13
     * 读设备
     */
    public abstract void read(String mac, UUID serviceId, UUID characterId, final BleReadResponse response);

    /**
     * ApiLevel: 13
     * 写设备
     */
    public abstract void write(String mac, UUID serviceId, UUID characterId, byte[] bytes, final BleWriteResponse response);

    /**
     * ApiLevel: 13
     * notify
     */
    public abstract void notify(String mac, UUID serviceId, UUID characterId, final BleNotifyResponse response);

    /**
     * ApiLevel 13
     * 断开连接
     */
    public abstract void disconnect(String mac);

    /**
     * ApiLevel 13
     * 取消notify
     * @param mac
     * @param service
     * @param character
     */
    public abstract void unnotify(String mac, UUID service, UUID character);
}

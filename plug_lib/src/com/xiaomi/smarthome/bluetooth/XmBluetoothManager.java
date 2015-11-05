package com.xiaomi.smarthome.bluetooth;

import android.os.Bundle;

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
     */
    public abstract byte[] getMiotPduDataBytes(byte[] scanRecord);

    /**
     * ApiLevel: 13
     * 通知智能家庭已绑定的设备
     * @param bundle
     */
    public abstract void notifyDeviceBinded(Bundle bundle);
}

package com.xiaomi.smarthome.bluetooth;

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
}

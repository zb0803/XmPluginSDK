package com.xiaomi.smarthome.bluetooth;

/**
 * Created by liwentian on 2015/10/29.
 */
public abstract class XmBluetoothManager {

    /**
     * ApiLevel:12
     */
    protected static XmBluetoothManager instance = null;

    /**
     * ApiLevel:12
     */
    public static XmBluetoothManager getInstance() {
        return instance;
    }

    /**
     * ApiLevel:12
     */
    public abstract byte[] getMiotPduDataBytes(byte[] scanRecord);
}

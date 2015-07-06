package com.xiaomi.smarthome.bluetooth;

/**
 * ApiLevel:5 蓝牙设备
 */
public class XmBluetoothDevice {
	/**
	 * ApiLevel:5
	 */
    public android.bluetooth.BluetoothDevice device;
    /**
     * ApiLevel:5
     */
    public int rssi;
    /**
     * ApiLevel:7
     */
    public boolean isConnected;
    /**
     * ApiLevel:7
     */
    public byte[] scanRecord;
}


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
     * ApiLevel:8
     */
    public boolean isConnected;
    /**
     * ApiLevel:8
     */
    public byte[] scanRecord;

    /**
     * ApiLevel:10
     */
    public int deviceType;

    /**
     * ApiLevel:10
     */
    public XmBluetoothDevice() {

    }

    /**
     * ApiLevel:10
     */
    public XmBluetoothDevice(android.bluetooth.BluetoothDevice device, int deviceType) {
        this.device = device;
        this.deviceType = deviceType;
        this.isConnected = true;
    }

    /**
     * ApiLevel:10
     */
    public XmBluetoothDevice(android.bluetooth.BluetoothDevice device, int rssi, byte[] scanRecord,
            int deviceType) {
        this.device = device;
        this.rssi = rssi;
        this.scanRecord = scanRecord;
        this.deviceType = deviceType;
        this.isConnected = false;
    }
}

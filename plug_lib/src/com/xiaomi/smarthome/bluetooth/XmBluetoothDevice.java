
package com.xiaomi.smarthome.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * ApiLevel:5 蓝牙设备
 */
public class XmBluetoothDevice implements Parcelable {

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
     * ApiLevel:11
     */
    public String name;

    /**
     * ApiLevel:10
     */
    public static final int DEVICE_TYPE_CLASSIC = 1;

    /**
     * ApiLevel:10
     */
    public static final int DEVICE_TYPE_BLE = 2;

    /**
     * ApiLevel:10
     */
    public XmBluetoothDevice() {

    }

    /**
     * ApiLevel:10
     */
    public XmBluetoothDevice(BluetoothDevice device, int deviceType) {
        this.device = device;
        this.deviceType = deviceType;
    }

    /**
     * ApiLevel:10
     */
    public XmBluetoothDevice(BluetoothDevice device, int rssi, byte[] scanRecord, int deviceType) {
        this.device = device;
        this.rssi = rssi;
        this.scanRecord = scanRecord;
        this.deviceType = deviceType;
    }

    public XmBluetoothDevice(Parcel in) {
        device = in.readParcelable(BluetoothDevice.class.getClassLoader());
        rssi = in.readInt();
        isConnected = (in.readByte() != 0);

        int length = in.readInt();
        if (length > 0) {
            scanRecord = new byte[length];
            in.readByteArray(scanRecord);
        } else {
            scanRecord = null;
        }
    }

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // TODO Auto-generated method stub
        dest.writeParcelable(device, 0);
        dest.writeInt(rssi);
        dest.writeByte((byte) (isConnected ? 1 : 0));

        dest.writeInt(scanRecord != null ? scanRecord.length : 0);
        dest.writeByteArray(scanRecord);
    }

    public static final Parcelable.Creator<XmBluetoothDevice> CREATOR = new Parcelable.Creator<XmBluetoothDevice>() {

        @Override
        public XmBluetoothDevice createFromParcel(Parcel source) {
            return new XmBluetoothDevice(source);
        }

        @Override
        public XmBluetoothDevice[] newArray(int size) {
            return new XmBluetoothDevice[size];
        }
    };

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        StringBuilder sb = new StringBuilder();
        sb.append("name = " + name);
        sb.append(", mac = " + device.getAddress());
        sb.append(", connected = " + isConnected);
        return sb.toString();
    }
}

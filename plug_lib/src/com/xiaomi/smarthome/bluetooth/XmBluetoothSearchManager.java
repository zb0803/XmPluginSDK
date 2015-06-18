
package com.xiaomi.smarthome.bluetooth;

public abstract class XmBluetoothSearchManager {

    protected static XmBluetoothSearchManager instance = null;

    public static abstract class BluetoothHandler {
        public static final int BLE = 1;
        public static final int BSC = 2;

        public int handlerType;

        public BluetoothHandler(int handlerType) {
            this.handlerType = handlerType;
        }

        public abstract void onSearchStarted();
        public abstract void onDeviceFounded(XmBluetoothDevice device);
        public abstract void onSearchStopped();
    }

    public static XmBluetoothSearchManager getInstance() {
        return instance;
    }

    public abstract void startScanBluetooth(BluetoothHandler handler);

    public abstract void stopScanBluetooth(BluetoothHandler handler);

}

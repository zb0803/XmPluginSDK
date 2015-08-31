
package com.xiaomi.smarthome.bluetooth;

/**
 * ApiLevel:5
 */
public abstract class XmBluetoothSearchManager {

    /**
     * ApiLevel:5
     */
    protected static XmBluetoothSearchManager instance = null;

    /**
     * ApiLevel:5
     */
    public static abstract class BluetoothHandler {
        /**
         * ApiLevel:5
         */
        public static final int BLE = 1;

        /**
         * ApiLevel:5
         */
        public static final int BSC = 2;

        /**
         * ApiLevel:5
         */
        public int handlerType;
        
        /**
         * ApiLevel:10
         */
        public long scanTime;

        /**
         * ApiLevel:5
         */
        public BluetoothHandler(int handlerType) {
            this.handlerType = handlerType;
        }
        
        /**
         * ApiLevel:10
         */
        public BluetoothHandler(int handlerType, long scanTime) {
            this.handlerType = handlerType;
            this.scanTime = scanTime;
        }

        /**
         * ApiLevel:5
         */
        public abstract void onSearchStarted();

        /**
         * ApiLevel:5
         */
        public abstract void onDeviceFounded(XmBluetoothDevice device);

        /**
         * ApiLevel:5
         */
        public abstract void onSearchStopped();
    }

    /**
     * ApiLevel:5
     */
    public static XmBluetoothSearchManager getInstance() {
        return instance;
    }

    /**
     * ApiLevel:5
     */
    public abstract void startScanBluetooth(BluetoothHandler handler);

    /**
     * ApiLevel:5
     */
    public abstract void stopScanBluetooth(BluetoothHandler handler);
    
    /**
     * ApiLevel:10
     */
    public abstract void startScanBluetoothImmediately(BluetoothHandler handler);

}

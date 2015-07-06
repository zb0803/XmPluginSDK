package com.xiaomi.smarthome.device.api;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * 推荐场景信息类
 *
 * ApiLevel:7
 */
public class RecommendSceneItem implements Parcelable {

    /**
     * ApiLevel:7
     */
    public int mRecommId;
    /**
     * ApiLevel:7
     */
    public String mName;
    /**
     * ApiLevel:7
     */
    public RemommendSceneCondition[] mRecommendConditionList;
    /**
     * ApiLevel:7
     */
    public RemommendSceneAction[] mRecommendActionList;

    /**
     * ApiLevel:7
     */
    public static class Key {
        public String mKey;
        public Object mValues;
        public void writeToParcel(Parcel parcel) {
            parcel.writeString(mKey);
            parcel.writeValue(mValues);
        }

        public void readFromParcel(Parcel in) {
            mKey = in.readString();
            mValues = in.readValue(ClassLoader.getSystemClassLoader());
        }
    }

    public static class RemommendSceneCondition {
        /**
         * ApiLevel:7
         */
        public String [] mDeviceModels;
        /**
         * ApiLevel:7
         */
        public String mConditionName;
        /**
         * ApiLevel:7
         */
        public Key[] mKeys;
        /**
         * ApiLevel:7
         */
        public String mProductId;
        /**
         * ApiLevel:7
         */
        public Boolean mAddAllDevice = false;

        public void writeToParcel(Parcel parcel) {
            parcel.writeStringArray(mDeviceModels);
            parcel.writeString(mConditionName);
            if(mKeys != null) {
                parcel.writeInt(mKeys.length);
                for(int j=0; j<mKeys.length; j++) {
                    mKeys[j].writeToParcel(parcel);
                }
            }
            else
                parcel.writeInt(0);
            parcel.writeString(mProductId);
            parcel.writeValue(mAddAllDevice);
        }

        public void readFromParcel(Parcel parcel) {
            mDeviceModels = parcel.createStringArray();
            mConditionName = parcel.readString();
            int size = parcel.readInt();
            if(size != 0) {
                mKeys = new Key[size];
                for(int i=0; i<size; i++) {
                    mKeys[i] = new Key();
                    mKeys[i].readFromParcel(parcel);
                }
            }
            mProductId = parcel.readString();
            mAddAllDevice = (Boolean) parcel.readValue(ClassLoader.getSystemClassLoader());
        }
    }

    public static class RemommendSceneAction {
        /**
         * ApiLevel:7
         */
        public String [] mDeviceModels;
        /**
         * ApiLevel:7
         */
        public String mActionName;
        /**
         * ApiLevel:7
         */
        public Key[] mKeys;
        /**
         * ApiLevel:7
         */
        public String mProductId;
        /**
         * ApiLevel:7
         */
        public Boolean mAddAllDevice;

        public void writeToParcel(Parcel parcel) {
            parcel.writeStringArray(mDeviceModels);
            parcel.writeString(mActionName);
            if(mKeys != null) {
                parcel.writeInt(mKeys.length);
                for(int j=0; j<mKeys.length; j++) {
                    mKeys[j].writeToParcel(parcel);
                }
            }
            else
                parcel.writeInt(0);
            parcel.writeString(mProductId);
            parcel.writeValue(mAddAllDevice);
        }

        public void readFromParcel(Parcel parcel) {
            mDeviceModels = parcel.createStringArray();
            mActionName = parcel.readString();
            int size = parcel.readInt();
            if(size != 0) {
                mKeys = new Key[size];
                for(int i=0; i<size; i++) {
                    mKeys[i] = new Key();
                    mKeys[i].readFromParcel(parcel);
                }
            }
            mProductId = parcel.readString();
            mAddAllDevice = (Boolean) parcel.readValue(ClassLoader.getSystemClassLoader());
        }
    }

    public RecommendSceneItem(Parcel in) {
        mRecommId = in.readInt();
        int sizeCondition = in.readInt();
        if(sizeCondition > 0) {
            mRecommendConditionList = new RemommendSceneCondition[sizeCondition];
            for(int i=0; i<sizeCondition; i++) {
                mRecommendConditionList[i].readFromParcel(in);
            }
        }

        int sizeAction = in.readInt();
        if(sizeAction > 0) {
            mRecommendActionList = new RemommendSceneAction[sizeAction];
            for(int i=0; i<sizeAction; i++) {
                mRecommendActionList[i].readFromParcel(in);
            }
        }
    }

    public RecommendSceneItem() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mRecommId);
        parcel.writeInt(mRecommendConditionList.length);
        for(int j=0; j<mRecommendConditionList.length; j++) {
            mRecommendConditionList[j].writeToParcel(parcel);
        }

        parcel.writeInt(mRecommendActionList.length);
        for(int j=0; j<mRecommendActionList.length; j++) {
            mRecommendActionList[j].writeToParcel(parcel);
        }
    }

    public static final Parcelable.Creator<RecommendSceneItem> CREATOR = new Parcelable.Creator<RecommendSceneItem>() {

        @Override
        public RecommendSceneItem createFromParcel(Parcel in) {
            return new RecommendSceneItem(in);
        }

        @Override
        public RecommendSceneItem[] newArray(int size) {
            return new RecommendSceneItem[size];
        }
    };
}
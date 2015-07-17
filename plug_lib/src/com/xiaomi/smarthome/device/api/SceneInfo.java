package com.xiaomi.smarthome.device.api;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 场景信息类
 *
 * ApiLevel:8
 *
 */
public class SceneInfo implements Parcelable {

    /**
     * ApiLevel:8
     */
    public static final int LAUNCH_CLICK = 0;
    public static final int LAUNCH_TIMER = 1;
    public static final int LAUNCH_DEVICE = 2;
    public static final int LAUNCH_LEAVE_HOME = 3;
    public static final int LAUNCH_COME_HOME = 4;
    public static final int LAUNCH_MIKEY = 5;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mSceneId);
        dest.writeInt(mRecommId);
        dest.writeString(mName);
        dest.writeInt(mEnable ? 1 : 0);
        mLaunch.writeToParcel(dest, flags);
        dest.writeList(mActions);
    }

    public SceneInfo() {

    }

    public SceneInfo(Parcel in) {
        readFromParcel(in);
    }

    void readFromParcel(Parcel in) {
        mSceneId = in.readInt();
        mName = in.readString();
        mEnable = in.readInt() == 1;
        mLaunch = new SceneLaunch(in);
        mActions = new ArrayList<SceneAction>();
        in.readList(mActions, SceneAction.class.getClassLoader());
    }

    /**
     * ApiLevel:8
     */
    public int mSceneId;
    /**
     * ApiLevel:8
     */
    public int mRecommId;
    /**
     * ApiLevel:8
     */
    public String mName;
    /**
     * ApiLevel:8
     */
    public boolean mEnable;
    /**
     * ApiLevel:8
     */
    public SceneLaunch mLaunch;
    /**
     * ApiLevel:8
     */
    public List<SceneAction> mActions;

    /**
     * ApiLevel:8
     */
    public static class SceneLaunch implements Parcelable {
        /**
         * ApiLevel:8
         */
        public int mLaunchType;
        /**
         * ApiLevel:8
         */
        public String mLaunchName;
        /**
         * ApiLevel:8
         */
        public String mDeviceModel;
        /**
         * ApiLevel:8
         */
        public String mEventString;
        public SceneLaunch() {

        }

        public SceneLaunch(Parcel in) {
            mLaunchType = in.readInt();
            mLaunchName = in.readString();
            mDeviceModel = in.readString();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(mLaunchType);
            dest.writeString(mLaunchName);
            dest.writeString(mDeviceModel);
            dest.writeString(mEventString);
        }
    }

    /**
     * ApiLevel:8
     */
    public static class SceneAction implements Parcelable{
        /**
         * ApiLevel:8
         */
        public String mDeviceName;
        /**
         * ApiLevel:8
         */
        public String mDeviceModel;
        /**
         * ApiLevel:8
         */
        public String mActionName;
        /**
         * ApiLevel:8
         */
        public String mActionString;
        public SceneAction() {

        }

        public SceneAction(Parcel in) {
            mDeviceName = in.readString();
            mDeviceModel = in.readString();
            mActionName = in.readString();
            mActionString = in.readString();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(mDeviceName);
            dest.writeString(mDeviceModel);
            dest.writeString(mActionName);
            dest.writeString(mActionString);
        }

        public static final Parcelable.Creator<SceneAction> CREATOR = new Parcelable.Creator<SceneAction>() {

            @Override
            public SceneAction createFromParcel(Parcel source) {
                return new SceneAction(source);
            }

            @Override
            public SceneAction[] newArray(int size) {
                return new SceneAction[size];
            }
        };
    }

    public static final Parcelable.Creator<SceneInfo> CREATOR = new Parcelable.Creator<SceneInfo>() {

        @Override
        public SceneInfo createFromParcel(Parcel source) {
            return new SceneInfo(source);
        }

        @Override
        public SceneInfo[] newArray(int size) {
            return new SceneInfo[size];
        }
    };
}
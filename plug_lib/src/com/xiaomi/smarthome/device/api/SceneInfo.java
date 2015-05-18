package com.xiaomi.smarthome.device.api;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class SceneInfo implements Parcelable {

    public static final int LAUNCH_CLICK = 0;
    public static final int LAUNCH_TIMER = 1;
    public static final int LAUNCH_DEVICE = 2;
    public static final int LAUNCH_LEAVE_HOME = 3;
    public static final int LAUNCH_COME_HOME = 4;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mSceneId);
        dest.writeString(mName);
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
        mLaunch = new SceneLaunch(in);
        mActions = new ArrayList<SceneAction>();
        in.readList(mActions, SceneAction.class.getClassLoader());
    }

    public int mSceneId;
    public String mName;
    public SceneLaunch mLaunch;
    public List<SceneAction> mActions;

    public static class SceneLaunch implements Parcelable {
        public int mLaunchType;
        public String mLaunchName;
        public String mDeviceModel;
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

    public static class SceneAction implements Parcelable{
        public String mDeviceName;
        public String mDeviceModel;
        public String mActionName;
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

package com.xiaomi.plugin.core;

import android.content.pm.PackageInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.xiaomi.smarthome.device.api.IXmPluginMessageReceiver;

import dalvik.system.DexClassLoader;

public class XmPluginPackage {
    public String packageName;
    public String packagePath;
    public DexClassLoader classLoader;
    public AssetManager assetManager;
    public Resources resources;
    public PackageInfo packageInfo;
    public String model;
    public IXmPluginMessageReceiver xmPluginMessageReceiver;

    //
   
    public Drawable appIcon;
    public CharSequence appLabel;
//    public String signatureMD5;
    public int miniApiVersion;
    public int packageVersion;
    

    public XmPluginPackage(String packageName, String path,
            DexClassLoader loader, AssetManager assetManager,
            Resources resources, PackageInfo packageInfo, String model,
            IXmPluginMessageReceiver xmPluginMessageReceiver) {
        this.packageName = packageName;
        this.packagePath = path;
        this.classLoader = loader;
        this.assetManager = assetManager;
        this.resources = resources;
        this.packageInfo = packageInfo;

        this.model = model;
        this.xmPluginMessageReceiver = xmPluginMessageReceiver;
    }

}

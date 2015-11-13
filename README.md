# 小米智能家庭Android app免安装插件开发手册


**最新修改**

------

- 子设备下拉菜单规范 [子设备下拉菜单交互](子设备下拉菜单规范.pdf)


使用plug_common库中MenuDialog控件实现

```
MenuDialog menuDialog = new MenuDialog(activity());


 //设置默认样式
    public void setItems(CharSequence[] items, DialogInterface.OnClickListener listener) {
        this.mItems = items;
        this.onClickListener = listener;
    }
    //设置背景颜色
    public void setBackGroundColor(int color){
        mBackgroundColor = color;
    }
    //设置可定制样式
    public void setMenuAdapter(BaseAdapter adapter){
        mListViewAdapter = adapter;
    }


```
- 调整plug_common库UI风格，[新插件规范1](智能家庭app规范001.jpg) [新插件规范2](智能家庭app规范002.jpg)

- 新插件规范，更新了titlebar，dialog，使用说明参考plugdemo工程

- list item风格需要插件开发者按照规范实现

- 请更新插件，使用新的plug_common库重新build插件

- 添加家庭分享关系

```
public class BaseDevice


  /**
     * ApiLevel:10 是否主人设备
     * 
     * @return
     */
    public boolean isOwner() {
        return (mDeviceStat.permitLevel & PERMISSION_NONE_MASK & PERMISSION_OWNER) != 0;
    }

    /**
     * ApiLevel:10 是否家庭设备
     * 
     * @return
     */
    public boolean isFamily() {
        return (mDeviceStat.permitLevel & PERMISSION_NONE_MASK & PERMISSION_FAMILY) != 0;
    }

    /**
     * ApiLevel:10 是否是分享权限
     * 
     * @return
     */
    public boolean isShared() {
        return ((mDeviceStat.permitLevel & PERMISSION_NONE_MASK & PERMISSION_SHARE) != 0)
                // 电视必须检查ownerName
                && !TextUtils.isEmpty(mDeviceStat.ownerName);
    }

    /**
     * ApiLevel:10 是否绑定设备，无论哪种权限，主人，分享，家庭都算
     * 
     * @return
     */
    public boolean isBinded2() {
        return (mDeviceStat.permitLevel & PERMISSION_NONE_MASK) != 0;
    }

```


## 插件调试

###安装ant

插件编译并安装到智能家庭app中需要ant脚本执行

[安装ant](http://ant.apache.org/manual/install.html)

###安装智能家庭开发版

安装Github中的最新smarthome.apk

###配置插件编译脚本

1.拷贝PluginDemo工程下的build.sh,build.xml,custom_rules.xml,project.properties文件到插件工程下面

2.修改project.properties，配置插件签名文件

```
key.alias=androiddebugkey
key.store.password=android
key.store=./keystore/xiaomi.demo.v1.keystore
key.alias.password=android
```

3.设置系统ANDROID_HOME变量指定到android sdk安装根目录，设置方法参考设置系统PATH变量

4.Linux系统打开终端terminal，进入插件工程目录，执行

```
./build.sh type project_name
```
type 为debug和release两种

project_name 为插件工程名


或者修改build.sh文件,直接指定build参数，这样只需要执行./build.sh即可

```
BUILD_TYPE=$1
PROJECT_NAME=$2

```

在已经安装智能家庭ap开发版情况下，会自动编译插件工程，生成插件apk，签名，并安装到智能家庭app中

5.Windows执行对应的build.bat

6.如果没有按照ant工具，可以先在eclipse或者android studio中打包，签名好，然后执行

```
adb push bin/your.apk /sdcard/SmartHome/plugin/debug/1.mpk

adb shell "am broadcast -a com.xiaomi.smarthome.action.OPEN_API --es type plugin_debug --es sub_type debug_package"

```



###调试插件


在插件中打断点，启动智能家庭app，调试com.xiaomi.smarthome进程

android studio中按照下面红色标记依次点击，即可调试插件

![](./md_images/debug_studio.png =539x626)


eclipse中调试插件，具体参考

[开发环境配置](开发环境配置.md)

------


```
免安装插件框架实现了一套在Android上面加载apk技术，跳过系统安装程序，只需要把apk上传到后台服务器，app端自动更新插件，具有H5实时更新特性，又具有native app灵活高效率特性。
```

- [框架描述](框架描述.md)
- [服务器部署](服务器部署.md)
- [开发环境配置](开发环境配置.md)
- [插件开发](插件开发.md)
- [开发接口描述](开发接口描述.md)





小米智能家庭蓝牙插件规范
========================================

根据设备是否符合小米蓝牙标准协议，智能家庭有两种不同的调用方式

1. [符合小米蓝牙标准协议](https://github.com/dingjikerbo/blog/blob/master/%E5%B0%8F%E7%B1%B3%E6%99%BA%E8%83%BD%E5%AE%B6%E5%BA%AD%E8%A7%84%E8%8C%831.md)
2. [不符合小米蓝牙标准协议](https://github.com/dingjikerbo/blog/blob/master/%E5%B0%8F%E7%B1%B3%E6%99%BA%E8%83%BD%E5%AE%B6%E5%BA%AD%E8%A7%84%E8%8C%832.md)

<!-- create time: 2015-04-17 10:53:01  -->

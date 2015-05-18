# 小米智能家庭Android app免安装插件开发手册


**最新修改**

------

1.去掉PluginTest工程，直接在smarthome app中调试，更接近实际测试环境

2.使用脚本编译打包，直接安装本地插件，调试更方便


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
./build.sh type project_name model
```
type 为debug和release两种

project_name 为插件工程名

model为插件的model名

或者修改build.sh文件,直接指定build参数，这样只需要执行./build.sh即可

```
BUILD_TYPE=$1
PROJECT_NAME=$2
MODEL=$3

```

在已经安装智能家庭ap开发版情况下，会自动编译插件工程，生成插件apk，签名，并安装到智能家庭app中

5.Windows执行对应的build.bat

6.如果没有按照ant工具，可以先在eclipse或者android studio中打包，签名好，然后执行

```
adb push bin/$MODEL.apk /sdcard/SmartHome/plugin/debug/$MODEL.mpk

adb shell "am broadcast -a com.xiaomi.smarthome.action.OPEN_API --es type plugin_debug --es sub_type download_plugin_from_sdcard --es param_model $MODEL"

adb shell "am broadcast -a com.xiaomi.smarthome.action.OPEN_API --es type plugin_debug --es sub_type install_plugin --es param_model $MODEL"

```



###调试插件


在插件中打断点，启动智能家庭app，调试com.xiaomi.smarthome进程

android studio中按照下面红色标记依次点击，即可调试插件

![](./md_images/debug_studio.png)



------


```
免安装插件框架实现了一套在Android上面加载apk技术，跳过系统安装程序，只需要把apk上传到后台服务器，app端自动更新插件，具有H5实时更新特性，又具有native app灵活高效率特性。
```

- [框架描述](框架描述.md)
- [服务器部署](服务器部署.md)
- [开发环境配置](开发环境配置.md)
- [插件开发](插件开发.md)
- [开发接口描述](开发接口描述.md)




<!-- create time: 2015-04-17 10:53:01  -->

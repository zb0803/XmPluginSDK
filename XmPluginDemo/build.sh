BUILD_TYPE=debug
PROJECT_NAME=XmPluginDemo

HELP_STRING="use 'build.sh debug yourAntProject' or 'build.sh release yourAntProject'"

if [ ! $BUILD_TYPE ]; then
    echo "build_type is null, $HELP_STRING"
    exit
fi

if [ $BUILD_TYPE != "debug" ] && [ $BUILD_TYPE != "release" ]; then
    echo "build_type is wrong, $HELP_STRING"
    exit
fi

if [ ! $PROJECT_NAME ]; then
    echo "project is null, $HELP_STRING"
    exit
fi

ant clean $BUILD_TYPE

adb push bin/$PROJECT_NAME-$BUILD_TYPE.apk /sdcard/SmartHome/plugin/debug/1.mpk

adb shell "am broadcast -a com.xiaomi.smarthome.action.OPEN_API --es type plugin_debug --es sub_type debug_package"

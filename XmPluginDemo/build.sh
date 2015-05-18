BUILD_TYPE=$1
PROJECT_NAME=$2
MODEL=$3

HELP_STRING="use 'build.sh debug yourAntProject yourModel' or 'build.sh release yourAntProject yourModel'"

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

if [ ! $MODEL ]; then
    echo "model is null, $HELP_STRING"
    exit
fi

ant clean $BUILD_TYPE

adb push bin/$PROJECT_NAME-$BUILD_TYPE.apk /sdcard/SmartHome/plugin/debug/$MODEL.mpk

adb shell "am broadcast -a com.xiaomi.smarthome.action.OPEN_API --es type plugin_debug --es sub_type download_plugin_from_sdcard --es param_model $MODEL"

adb shell "am broadcast -a com.xiaomi.smarthome.action.OPEN_API --es type plugin_debug --es sub_type install_plugin --es param_model $MODEL"

#adb shell "am broadcast -a com.xiaomi.smarthome.action.OPEN_API --es msg_type 'plugin_debug' --es msg_body '"'{"debug_type":"download_plugin_from_sdcard","debug_body":{"model":"xiaomi.demo.v1"}}'"'"
#adb shell "am broadcast -a com.xiaomi.smarthome.action.OPEN_API --es msg_type 'plugin_debug' --es msg_body '"'{"debug_type":"install_plugin","debug_body":{"model":"xiaomi.demo.v1"}}'"'"
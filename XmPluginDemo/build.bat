set BUILD_TYPE=%1
set PROJECT_NAME=%2
set MODEL=%3

if {%BUILD_TYPE%} == {} (
    echo build type is null, use "build.bat debug yourAntProject yourModel" or "build.bat release yourAntProject yourModel" 
    exit /b
) else if "%BUILD_TYPE%" == "debug" (
    echo %BUILD_TYPE%
) else if "%BUILD_TYPE%" == "release" (
    echo %BUILD_TYPE%
) else (
    echo build_type is wrong, use "build.bat debug yourAntProject yourModel" or "build.bat release yourAntProject yourModel"  
    exit /b
)

if {%PROJECT_NAME%} == {} (
    echo project is null, use "build.bat debug yourAntProject yourModel" or "build.bat release yourAntProject yourModel"
    exit /b
)

if {%MODEL%} == {} (
    echo model is null, use "build.bat debug yourAntProject yourModel" or "build.bat release yourAntProject yourModel"  
    exit /b
)

call ant clean %BUILD_TYPE%

echo adb push bin/%PROJECT_NAME%-%BUILD_TYPE%.apk /sdcard/SmartHome/plugin/debug/%MODEL%.mpk

adb push bin/%PROJECT_NAME%-%BUILD_TYPE%.apk /sdcard/SmartHome/plugin/debug/%MODEL%.mpk



echo adb shell "am broadcast -a com.xiaomi.smarthome.action.OPEN_API --es type plugin_debug --es sub_type download_plugin_from_sdcard --es param_model %MODEL%"

adb shell "am broadcast -a com.xiaomi.smarthome.action.OPEN_API --es type plugin_debug --es sub_type download_plugin_from_sdcard --es param_model %MODEL%"


echo adb shell "am broadcast -a com.xiaomi.smarthome.action.OPEN_API --es type plugin_debug --es sub_type install_plugin --es param_model %MODEL%"

adb shell "am broadcast -a com.xiaomi.smarthome.action.OPEN_API --es type plugin_debug --es sub_type install_plugin --es param_model %MODEL%"

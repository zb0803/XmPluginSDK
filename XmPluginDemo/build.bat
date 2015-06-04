set BUILD_TYPE=%1
set PROJECT_NAME=%2

if {%BUILD_TYPE%} == {} (
    echo build type is null, use "build.bat debug yourAntProject" or "build.bat release yourAntProject" 
    exit /b
) else if "%BUILD_TYPE%" == "debug" (
    echo %BUILD_TYPE%
) else if "%BUILD_TYPE%" == "release" (
    echo %BUILD_TYPE%
) else (
    echo build_type is wrong, use "build.bat debug yourAntProject" or "build.bat release yourAntProject"  
    exit /b
)

if {%PROJECT_NAME%} == {} (
    echo project is null, use "build.bat debug yourAntProject" or "build.bat release yourAntProject"
    exit /b
)


call ant clean %BUILD_TYPE%

echo adb push bin/%PROJECT_NAME%-%BUILD_TYPE%.apk /sdcard/SmartHome/plugin/debug/1.mpk

adb push bin/%PROJECT_NAME%-%BUILD_TYPE%.apk /sdcard/SmartHome/plugin/debug/1.mpk



echo adb shell "am broadcast -a com.xiaomi.smarthome.action.OPEN_API --es type plugin_debug --es sub_type debug_package"

adb shell "am broadcast -a com.xiaomi.smarthome.action.OPEN_API --es type plugin_debug --es sub_type debug_package"
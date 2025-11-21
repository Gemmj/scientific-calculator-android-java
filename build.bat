@echo off
REM Android科学计算器构建脚本
REM 使用方法: 直接双击运行此批处理文件

echo ========================================
echo Android科学计算器 - 构建脚本
echo ========================================
echo.

echo 正在清理项目...
call gradlew clean
if errorlevel 1 (
    echo 错误: 项目清理失败
    pause
    exit /b 1
)

echo.
echo 正在编译调试版APK...
call gradlew assembleDebug
if errorlevel 1 (
    echo 错误: 编译失败
    pause
    exit /b 1
)

echo.
echo ========================================
echo 编译完成！
echo ========================================
echo APK文件位置: app\build\outputs\apk\debug\app-debug.apk
echo.
echo 您可以将此APK文件安装到Android设备上进行测试。
echo.
pause

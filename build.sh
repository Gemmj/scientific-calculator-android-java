#!/bin/bash

# Android科学计算器构建脚本
# 使用方法: chmod +x build.sh && ./build.sh

echo "========================================"
echo "Android科学计算器 - 构建脚本"
echo "========================================"
echo

echo "正在清理项目..."
./gradlew clean
if [ $? -ne 0 ]; then
    echo "错误: 项目清理失败"
    exit 1
fi

echo
echo "正在编译调试版APK..."
./gradlew assembleDebug
if [ $? -ne 0 ]; then
    echo "错误: 编译失败"
    exit 1
fi

echo
echo "========================================"
echo "编译完成！"
echo "========================================"
echo "APK文件位置: app/build/outputs/apk/debug/app-debug.apk"
echo
echo "您可以将此APK文件安装到Android设备上进行测试。"
echo

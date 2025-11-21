# Android 科学计算器 (纯Java版)

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![API](https://img.shields.io/badge/API-24%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=24)
[![Java](https://img.shields.io/badge/Java-100%25-orange.svg)](https://www.java.com/)

一款现代化的 Android 科学计算器，使用纯Java开发，具有精美的 Material Design 3 界面、全面的计算功能和持久化历史记录存储。

## ✨ 功能特性

### 📱 基础计算功能
- **算术运算**：加法 (+)、减法 (-)、乘法 (×)、除法 (÷)
- **小数支持**：完整的浮点数运算
- **括号支持**：复杂表达式和嵌套括号
- **百分比计算**：快速百分比运算 (%)
- **实时预览**：输入时即时显示计算结果

### 🧮 科学计算功能
- **三角函数**：sin、cos、tan（支持角度输入）
- **对数函数**：ln（自然对数）、log（常用对数）
- **指数运算**：e 常数、π 常数、幂运算 (x^y)
- **开方运算**：√x 计算
- **阶乘运算**：n! 整数阶乘

### 💾 数据持久化
- **SQLite 数据库**：所有计算历史本地存储
- **智能历史**：自动保存表达式、结果和时间戳
- **历史浏览**：专用界面查看和重用历史计算
- **数据管理**：支持确认清除历史记录功能

### 🎨 现代化界面设计
- **Material Design 3**：最新设计指南和组件
- **深色主题**：护眼的深色配色方案
- **响应式布局**：
  - 竖屏模式：传统计算器网格布局
  - 横屏模式：分屏显示，结果显示面板和按键面板
- **多屏幕支持**：针对手机和平板优化
- **流畅动画**：按钮按压效果和平滑过渡

### 🛠️ 用户体验
- **复制结果**：长按复制结果到剪贴板
- **智能错误处理**：友好的错误提示和输入验证
- **智能清除功能**：
  - C：清除当前输入
  - AC：全部清除（重置所有）
  - ⌫：退格（删除最后一个字符）

## 🚀 快速开始

### 环境要求
- Android Studio Arctic Fox 或更高版本
- Android SDK API level 24+ (Android 7.0+)
- JDK 8 或更高版本

### 安装步骤

1. **克隆仓库**
   ```bash
   git clone https://github.com/yourusername/scientific-calculator-android.git
   cd scientific-calculator-android
   ```

2. **在 Android Studio 中打开**
   - 启动 Android Studio
   - 选择"打开现有项目"
   - 导航到克隆的目录

3. **构建并运行**
   - 连接 Android 设备或启动模拟器
   - 点击"运行"按钮或按 `Ctrl+R`

### 构建 APK

```bash
./gradlew assembleRelease
```

APK 将在 `app/build/outputs/apk/release/` 目录下生成

## 🏗️ Architecture

### Tech Stack
- **Language**: Java 100%
- **Min SDK**: API 24 (Android 7.0)
- **Target SDK**: API 34 (Android 14)
- **Architecture**: MVC pattern

## 🏗️ 技术架构

### 技术栈
- **编程语言**：Java 100%
- **最低 SDK**：API 24 (Android 7.0)
- **目标 SDK**：API 34 (Android 14)
- **架构模式**：MVC 模式

### 核心组件
- **MainActivity**：主界面，处理用户交互和计算
- **HistoryActivity**：计算历史浏览器界面
- **CalculatorEngine**：使用 exp4j 库的科学计算引擎
- **DatabaseHelper**：历史记录的 SQLite 数据库管理
- **HistoryAdapter**：显示计算历史的 RecyclerView 适配器

### 依赖库
- **exp4j**：数学表达式计算
- **Material Design Components**：现代化 UI 组件
- **AndroidX Libraries**：Lifecycle、RecyclerView、ConstraintLayout
- **SQLite**：历史记录本地数据库

## 📸 应用截图

*截图即将添加*

## 🤝 参与贡献

我们欢迎贡献！请查看我们的 [贡献指南](CONTRIBUTING.md) 了解详情。

### 开发环境搭建
1. Fork 仓库
2. 创建功能分支：`git checkout -b feature/amazing-feature`
3. 提交更改：`git commit -m 'Add amazing feature'`
4. 推送分支：`git push origin feature/amazing-feature`
5. 打开 Pull Request

### 代码规范
- 遵循 Java 编码规范
- 使用有意义的变量和函数名
- 为公共 API 添加 JavaDoc 注释
- 提交前确保所有测试通过

## 🐛 问题反馈

发现 Bug？请创建 issue 并包含：
- 设备信息（型号、Android 版本）
- 重现步骤
- 期望行为 vs 实际行为
- 截图（如果适用）

## 📝 许可证

本项目采用 Apache License 2.0 许可证 - 查看 [LICENSE](LICENSE) 文件了解详情。

## 🙏 致谢

- [exp4j](https://www.objecthunter.net/exp4j/) - 数学表达式计算
- [Material Design](https://material.io/) - 设计系统和组件
- Android 社区持续的灵感和支持

## 📊 项目状态

- ✅ 核心计算器功能
- ✅ 科学函数
- ✅ 历史记录持久化
- ✅ 响应式界面设计
- 🔄 单元测试（开发中）
- 🔄 国际化支持（计划中）
- 🔄 小部件支持（计划中）

---

**如果觉得有用，请给个 ⭐ Star！**

### 系统要求
- Android 7.0 (API 24) 或更高版本
- 约 5MB 存储空间

### 安装步骤
1. 从项目的 `app/build/outputs/apk/debug/` 目录获取 `app-debug.apk` 文件
2. 在Android设备上启用"未知来源"应用安装
3. 安装APK文件

### 使用指南
1. **基础计算**：直接点击数字和运算符按钮
2. **科学计算**：使用科学函数按钮，如sin、cos、√等
3. **查看历史**：点击右上角的历史图标
4. **复制结果**：长按结果区域可复制到剪贴板
5. **清除历史**：在菜单中选择"清除历史记录"

## 项目结构

```
app/
├── src/main/
│   ├── java/com/scientificcalculator/android/
│   │   ├── MainActivity.java              # 主界面
│   │   ├── HistoryActivity.java           # 历史记录界面
│   │   ├── adapter/
│   │   │   └── HistoryAdapter.java        # 历史记录适配器
│   │   ├── calculator/
│   │   │   └── CalculatorEngine.java      # 计算引擎
│   │   └── data/
│   │       ├── CalculationHistory.java    # 数据模型
│   │       └── DatabaseHelper.java        # 数据库操作
│   ├── res/
│   │   ├── layout/                        # 布局文件
│   │   ├── layout-land/                   # 横屏布局
│   │   ├── values/                        # 资源文件
│   │   ├── drawable/                      # 图标和背景
│   │   └── menu/                          # 菜单资源
│   └── AndroidManifest.xml
└── build.gradle
```

## 开发特性

### 响应式设计
- 使用ConstraintLayout和LinearLayout的组合
- 支持横竖屏自动切换
- 按钮和文本大小自适应屏幕

### 数据安全
- 本地SQLite数据库，数据不会上传
- 支持Android备份机制
- 异常处理确保应用稳定性

### 性能优化
- 使用ViewBinding提高性能
- 合理的内存管理
- 异步数据库操作（如需要）

## 未来扩展

可以考虑添加的功能：
- [ ] 更多科学计算函数（反三角函数、双曲函数等）
- [ ] 图形绘制功能
- [ ] 单位转换
- [ ] 历史记录搜索和分类
- [ ] 主题切换（浅色/深色/自定义）
- [ ] 计算过程步骤显示
- [ ] 语音输入支持
- [ ] 数据导出功能

## 开发者信息

此项目展示了Android应用开发的最佳实践，包括：
- 现代Android开发技术栈
- 清晰的项目架构
- 用户友好的界面设计
- 完整的数据持久化方案
- 响应式布局设计

## 许可证

本项目仅供学习和参考使用。

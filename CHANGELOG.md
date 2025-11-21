# 变更日志

本项目的所有重要变更都将记录在这个文件中。

格式基于 [Keep a Changelog](https://keepachangelog.com/zh-CN/1.0.0/)，
本项目遵循 [语义版本控制](https://semver.org/lang/zh-CN/spec/v2.0.0.html)。

## [未发布]

### 新增
- 初始开源发布准备
- GitHub Actions CI/CD 流水线
- 贡献指南和问题模板

## [1.0.0] - 2025-11-21

### 新增
- 基础算术运算 (+, -, ×, ÷)
- 科学函数 (sin, cos, tan, ln, log, √, ^, !)
- 数学常数 (π, e)
- 复杂表达式的括号支持
- 实时计算预览
- 计算历史的 SQLite 数据库
- 可点击历史条目的历史浏览器
- Material Design 3 深色主题界面
- 响应式布局（竖屏/横屏）
- 复制到剪贴板功能
- 智能错误处理和验证
- 多种清除功能（C、AC、退格）
- 支持 Android 7.0+ (API 24+)

### 技术细节
- 使用 Kotlin 100% 构建
- 使用 exp4j 库进行数学表达式计算
- MVVM 架构配合 ViewBinding
- SQLite 数据库用于持久化存储
- Material Design 组件用于 UI
- 使用 ConstraintLayout 的响应式布局

[未发布]: https://github.com/yourusername/scientific-calculator-android/compare/v1.0.0...HEAD
[1.0.0]: https://github.com/yourusername/scientific-calculator-android/releases/tag/v1.0.0

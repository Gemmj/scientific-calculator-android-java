# 开发指南

本文档为科学计算器项目的开发者提供全面的信息。

## 🏗️ 项目结构

```
app/
├── src/
│   ├── main/
│   │   ├── java/com/scientificcalculator/android/
│   │   │   ├── MainActivity.kt              # 主计算器界面
│   │   │   ├── HistoryActivity.kt           # 计算历史浏览器
│   │   │   ├── calculator/
│   │   │   │   └── CalculatorEngine.kt      # 核心计算逻辑
│   │   │   ├── data/
│   │   │   │   ├── DatabaseHelper.kt        # SQLite 数据库管理
│   │   │   │   ├── CalculationHistory.kt    # 历史记录数据模型
│   │   │   │   └── HistoryAdapter.kt        # RecyclerView 适配器
│   │   │   └── utils/
│   │   │       └── Constants.kt             # 应用常量
│   │   ├── res/
│   │   │   ├── layout/                      # UI 布局文件
│   │   │   ├── values/                      # 字符串、颜色、尺寸
│   │   │   ├── drawable/                    # 矢量图形和图片
│   │   │   └── mipmap/                      # 应用图标
│   │   └── AndroidManifest.xml              # 应用配置
│   └── test/
│       └── java/com/scientificcalculator/android/
│           └── calculator/
│               └── CalculatorEngineTest.kt  # 单元测试
├── build.gradle.kts                         # 模块构建配置
└── proguard-rules.pro                       # ProGuard 混淆规则
```

## 🛠️ 开发环境设置

### 环境要求
- **Android Studio**: 2022.3.1 (Giraffe) 或更高版本
- **JDK**: 11 或更高版本
- **Android SDK**: API 24+ (Android 7.0+)
- **Kotlin**: 1.8.0+

### IDE 配置
1. **代码风格**: Android Studio 默认 Kotlin 风格
2. **实时模板**: 启用 Android 和 Kotlin 模板
3. **插件**: 确保 Kotlin 插件已启用

## 📐 架构设计

### 设计模式
应用遵循 **MVVM (Model-View-ViewModel)** 架构：

- **Model**: 数据类和 SQLite 数据库 (`data/` 包)
- **View**: Activity 和布局文件 (`MainActivity`, `HistoryActivity`)
- **ViewModel**: 逻辑在 Activity 中处理 (未来可以抽取到 ViewModel)

### 关键组件

#### CalculatorEngine
```kotlin
class CalculatorEngine {
    fun evaluate(expression: String): String
    fun formatResult(result: Double): String
}
```
- 使用 exp4j 库进行数学表达式计算
- 处理运算符转换 (×, ÷ 转换为 *, /)
- 支持三角函数的角度输入
- 实现无效表达式的错误处理

#### DatabaseHelper
```kotlin
class DatabaseHelper(context: Context) : SQLiteOpenHelper {
    fun addCalculation(expression: String, result: String)
    fun getAllHistory(): List<CalculationHistory>
    fun clearHistory()
}
```
- SQLite 数据库用于持久化存储
- 自动管理数据库架构升级
- 为计算历史提供 CRUD 操作

#### MainActivity
- 主计算器界面
- 处理按钮点击和用户输入
- 管理显示更新和计算
- 实现不同方向的响应式布局

## 🧪 测试策略

### 单元测试
位于 `app/src/test/java/`

**CalculatorEngineTest.kt** 覆盖：
- 基础算术运算
- 科学函数
- 复杂表达式
- 错误处理
- 边界情况

### 运行测试
```bash
# 运行所有单元测试
./gradlew test

# 运行特定测试类
./gradlew test --tests CalculatorEngineTest

# 运行代码覆盖率测试
./gradlew testDebugUnitTestCoverage
```

### 测试覆盖率目标
- **计算器引擎**: 90%+ 覆盖率
- **数据库操作**: 80%+ 覆盖率
- **UI 逻辑**: 70%+ 覆盖率

## 🎨 UI/UX 指南

### 设计系统
- **Material Design 3**: 最新设计指南
- **深色主题**: 默认配色方案
- **排版**: Roboto 字体系列
- **间距**: 8dp 网格系统

### 配色方案
```xml
<!-- 主要颜色 -->
<color name="primary">#6200EE</color>
<color name="primary_variant">#3700B3</color>

<!-- 背景颜色 -->
<color name="background">#121212</color>
<color name="surface">#1E1E1E</color>

<!-- 文本颜色 -->
<color name="on_background">#FFFFFF</color>
<color name="on_surface">#FFFFFF</color>
```

### 布局原则
1. **响应式设计**: 适应不同屏幕尺寸
2. **触摸目标**: 交互元素最小 48dp
3. **无障碍性**: 适当的内容描述和对比度
4. **一致性**: 统一的间距和组件使用

## 📦 依赖管理

### 核心依赖
```kotlin
// 核心 Android 库
implementation("androidx.core:core-ktx:1.12.0")
implementation("androidx.appcompat:appcompat:1.6.1")
implementation("androidx.constraintlayout:constraintlayout:2.1.4")

// Material Design
implementation("com.google.android.material:material:1.11.0")

// 数学表达式计算
implementation("net.objecthunter:exp4j:0.4.8")
```

### 依赖管理
- **版本目录**: 考虑使用以更好地管理依赖
- **更新**: 定期更新依赖项以获得安全和功能
- **兼容性**: 确保与最低SDK的向后兼容性

## 🔧 构建配置

### 构建类型
- **Debug**: 启用调试的开发构建
- **Release**: 启用 ProGuard 的生产构建

### 签名配置
对于发布版本，在 `app/build.gradle.kts` 中配置签名：
```kotlin
signingConfigs {
    create("release") {
        storeFile = file("path/to/keystore.jks")
        storePassword = "store_password"
        keyAlias = "key_alias"
        keyPassword = "key_password"
    }
}
```

### ProGuard
发布版本使用 ProGuard：
- 代码混淆
- 死代码移除
- 应用大小优化

## 🚀 发布流程

### 版本管理
在 `app/build.gradle.kts` 中更新版本：
```kotlin
defaultConfig {
    versionCode = 2
    versionName = "1.1.0"
}
```

### 发布检查清单
- [ ] 更新版本号
- [ ] 更新 CHANGELOG.md
- [ ] 运行完整测试套件
- [ ] 在多个设备上测试
- [ ] 构建发布 APK
- [ ] 创建 GitHub 发布
- [ ] 更新文档

### GitHub 发布
```bash
# 创建并推送标签
git tag -a v1.1.0 -m "版本 1.1.0"
git push origin v1.1.0

# GitHub Actions 将自动创建发布
```

## 🐛 调试

### 日志记录
使用 Android 日志系统：
```kotlin
import android.util.Log

private val TAG = "CalculatorEngine"
Log.d(TAG, "评估表达式: $expression")
Log.e(TAG, "计算错误", exception)
```

### 常见问题
1. **表达式解析**: 检查运算符转换逻辑
2. **数据库错误**: 验证 SQLite 架构和操作
3. **UI 响应性**: 在不同屏幕尺寸上测试
4. **内存泄漏**: 使用 Android Studio 分析器

## 🔐 安全考虑

### 数据保护
- 所有数据本地存储（无网络传输）
- SQLite 数据库使用标准 Android 安全
- 不收集敏感信息

### 代码混淆
发布版本使用 ProGuard：
- 保护知识产权
- 降低逆向工程风险
- 优化应用性能

## 📝 代码风格

### Kotlin 指南
遵循 [Kotlin 编码规范](https://kotlinlang.org/docs/coding-conventions.html)：

```kotlin
// 良好示例
class CalculatorEngine {
    fun evaluate(expression: String): String {
        return try {
            // 实现代码
        } catch (e: Exception) {
            "错误"
        }
    }
}

// 函数文档
/**
 * 评估数学表达式并返回结果。
 * 
 * @param expression 要评估的数学表达式
 * @return 计算结果字符串，如果无效则返回"错误"
 */
```

### 命名约定
- **类名**: PascalCase (`CalculatorEngine`)
- **函数**: camelCase (`evaluateExpression`)
- **常量**: UPPER_SNAKE_CASE (`MAX_HISTORY_ITEMS`)
- **资源**: snake_case (`button_calculator`)

## 🔄 贡献工作流程

1. **Fork** 仓库
2. **创建** 功能分支: `git checkout -b feature/new-function`
3. **实现** 带测试的更改
4. **全面测试** 在多个设备上
5. **提交** 带详细描述的 pull request
6. **代码审查** 并处理反馈
7. **合并** 审核后

## 📚 学习资源

### Android 开发
- [Android 开发者指南](https://developer.android.com/guide)
- [Material Design 指南](https://material.io/design)
- [Kotlin 文档](https://kotlinlang.org/docs/)

### 数学库
- [exp4j 文档](https://www.objecthunter.net/exp4j/)
- [数学表达式解析](https://en.wikipedia.org/wiki/Shunting-yard_algorithm)

---

如有疑问或需要澄清，请查看现有问题或创建带有 `question` 标签的新问题。

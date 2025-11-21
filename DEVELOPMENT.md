# 开发指南

欢迎参与 Android 科学计算器的开发！本文档将帮助您快速上手。

## 🚀 快速开始

### 环境准备
1. **Java开发环境**
   - JDK 8 或更高版本
   - 建议使用 JDK 17 (LTS版本)

2. **Android开发环境**
   - Android Studio Arctic Fox 或更高版本
   - Android SDK API 24+ (Android 7.0+)
   - Android Build Tools

3. **版本控制**
   - Git

### 项目设置

1. **克隆项目**
   ```bash
   git clone https://github.com/Gemmj/scientific-calculator-android-java.git
   cd scientific-calculator-android-java
   ```

2. **导入Android Studio**
   - 打开 Android Studio
   - 选择 "Open an existing project"
   - 选择项目目录

3. **同步项目**
   - Android Studio会自动提示同步Gradle
   - 等待同步完成

## 🏗️ 项目架构

### 目录结构
```
app/src/main/java/com/scientificcalculator/android/
├── MainActivity.java              # 主界面Activity
├── HistoryActivity.java           # 历史记录界面
├── adapter/
│   └── HistoryAdapter.java        # 历史记录列表适配器
├── calculator/
│   └── CalculatorEngine.java      # 计算引擎核心
└── data/
    ├── CalculationHistory.java    # 计算历史数据模型
    └── DatabaseHelper.java        # SQLite数据库操作
```

### 核心组件说明

#### MainActivity.java
- 应用主界面
- 处理用户输入和显示
- 管理计算器状态
- 响应按钮点击事件

#### CalculatorEngine.java
- 数学表达式解析和计算
- 使用exp4j库进行复杂计算
- 错误处理和验证

#### DatabaseHelper.java
- SQLite数据库管理
- 历史记录的增删查改操作
- 数据持久化

#### HistoryAdapter.java
- RecyclerView适配器
- 显示计算历史列表
- 处理历史项点击事件

## 🛠️ 开发工作流

### 分支策略
- `main`: 稳定的生产分支
- `develop`: 开发分支
- `feature/*`: 功能分支
- `bugfix/*`: Bug修复分支

### 开发步骤
1. **创建功能分支**
   ```bash
   git checkout -b feature/你的功能名称
   ```

2. **编写代码**
   - 遵循Java编码规范
   - 添加必要的注释
   - 保持代码整洁

3. **测试**
   ```bash
   # 运行单元测试
   ./gradlew test
   
   # 构建并测试APK
   ./gradlew assembleDebug
   ```

4. **提交代码**
   ```bash
   git add .
   git commit -m "feat: 添加新功能描述"
   ```

5. **推送并创建PR**
   ```bash
   git push origin feature/你的功能名称
   ```

## 🧪 测试

### 运行测试
```bash
# 运行所有单元测试
./gradlew test

# 运行特定测试
./gradlew test --tests CalculatorEngineTest

# 查看测试报告
# 报告位置: app/build/reports/tests/testDebugUnitTest/index.html
```

### 添加测试
1. 在 `app/src/test/java` 目录下创建测试文件
2. 使用JUnit4框架编写测试
3. 确保测试覆盖核心功能

## 📝 代码规范

### Java编码规范
- 使用驼峰命名法
- 类名首字母大写
- 方法和变量名首字母小写
- 常量使用全大写字母，用下划线分隔

### 注释规范
```java
/**
 * 计算数学表达式
 * @param expression 数学表达式字符串
 * @return 计算结果
 * @throws IllegalArgumentException 当表达式无效时
 */
public double calculate(String expression) {
    // 实现代码
}
```

### 提交消息规范
```
type(scope): subject

body

footer
```

类型说明：
- `feat`: 新功能
- `fix`: Bug修复
- `docs`: 文档更新
- `style`: 代码格式化
- `refactor`: 代码重构
- `test`: 测试相关
- `chore`: 构建工具或辅助工具的变动

## 🐛 调试

### 常见问题
1. **编译错误**
   - 检查Java版本兼容性
   - 清理并重新构建: `./gradlew clean build`

2. **依赖问题**
   - 同步Gradle: `./gradlew --refresh-dependencies`

3. **模拟器问题**
   - 确保使用API 24+的模拟器
   - 检查AVD设置

### 调试工具
- Android Studio调试器
- Logcat日志查看
- 布局检查器

## 📦 构建和发布

### 调试构建
```bash
./gradlew assembleDebug
```

### 发布构建
```bash
./gradlew assembleRelease
```

### 使用构建脚本
```bash
# Windows
build.bat

# Linux/Mac
./build.sh
```

## 🤝 贡献指南

1. Fork 项目
2. 创建功能分支
3. 提交更改
4. 推送到分支
5. 创建 Pull Request

请确保您的贡献：
- 遵循项目代码规范
- 包含适当的测试
- 更新相关文档
- 通过所有CI检查

## 📚 资源链接

- [Android官方文档](https://developer.android.com/)
- [exp4j数学库文档](https://www.objecthunter.net/exp4j/)
- [Material Design指南](https://material.io/design)
- [Java编码规范](https://google.github.io/styleguide/javaguide.html)

## 💡 获取帮助

- 提交Issue: [GitHub Issues](https://github.com/Gemmj/scientific-calculator-android-java/issues)
- 查看已有讨论: [GitHub Discussions](https://github.com/Gemmj/scientific-calculator-android-java/discussions)

感谢您的贡献！🎉
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

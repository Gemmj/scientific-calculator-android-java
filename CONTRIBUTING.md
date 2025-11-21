# 贡献指南

感谢您对科学计算器项目的关注！本文档提供了参与本项目贡献的指南和说明。

## 🤝 行为准则

我们致力于为所有人提供友好和包容的体验。请在所有交互中保持尊重和体贴。

## 🚀 快速开始

### 环境要求

在贡献之前，请确保您拥有：
- Android Studio Arctic Fox (2020.3.1) 或更高版本
- Android SDK API level 24+ (Android 7.0+)
- Kotlin 1.8.0+
- Git 版本控制
- Android 开发和 Kotlin 基础知识

### 搭建开发环境

1. **Fork 仓库**
   ```bash
   # 在 GitHub 上 Fork 仓库，然后克隆您的 fork
   git clone https://github.com/YOUR_USERNAME/scientific-calculator-android.git
   cd scientific-calculator-android
   ```

2. **设置远程仓库**
   ```bash
   git remote add upstream https://github.com/ORIGINAL_OWNER/scientific-calculator-android.git
   ```

3. **在 Android Studio 中打开**
   - 启动 Android Studio
   - 选择"打开现有项目"
   - 导航到克隆的目录

4. **同步项目**
   - 让 Android Studio 下载所有依赖项
   - 确保项目成功构建

## 📝 开发指南

### 编码规范

- **编程语言**：所有新代码使用 Kotlin
- **代码风格**：遵循 [Kotlin 编码规范](https://kotlinlang.org/docs/coding-conventions.html)
- **代码格式**：使用 Android Studio 默认代码格式化 (Ctrl+Alt+L)
- **命名规范**：为变量、函数和类使用描述性名称

### 代码组织

```
app/src/main/java/com/scientificcalculator/android/
├── calculator/         # 核心计算逻辑
├── data/              # 数据库和数据模型
├── ui/                # Activity 和 UI 组件
└── utils/             # 实用工具函数
```

### 文档规范

- **KDoc**：为所有公共 API 添加 KDoc 注释
- **行内注释**：用行内注释解释复杂逻辑
- **README 更新**：为新功能更新文档

KDoc 示例：
```kotlin
/**
 * 计算给定数字的阶乘。
 * 
 * @param n 要计算阶乘的数字（必须是非负数）
 * @return 阶乘结果，如果输入无效则返回 null
 * @throws IllegalArgumentException 如果 n 为负数
 */
fun factorial(n: Int): Long? {
    // 实现代码
}
```

## 🔄 贡献工作流程

### 1. 选择问题

- 查看 [现有问题](https://github.com/OWNER/scientific-calculator-android/issues)
- 初学者可查找标有 `good first issue` 的问题
- 在问题下评论表明您正在处理它

### 2. 创建功能分支

```bash
git checkout -b feature/your-feature-name
# 或
git checkout -b bugfix/issue-number-description
```

分支命名约定：
- `feature/calculator-history` - 新功能
- `bugfix/division-by-zero` - Bug 修复
- `refactor/database-cleanup` - 代码重构
- `docs/update-readme` - 文档更新

### 3. 进行更改

- 编写清晰、可读的代码
- 遵循现有代码模式
- 添加适当的测试
- 如需要更新文档

### 4. 测试您的更改

```bash
# 运行单元测试
./gradlew test

# 运行仪器测试
./gradlew connectedAndroidTest

# 构建项目
./gradlew build
```

### 5. 提交更改

使用约定式提交格式：
```bash
git add .
git commit -m "feat: 添加平方根计算功能"
# 或
git commit -m "fix: 解决除零崩溃问题"
# 或
git commit -m "docs: 更新安装说明"
```

提交信息格式：
- `feat:` - 新功能
- `fix:` - Bug 修复
- `docs:` - 仅文档
- `refactor:` - 代码重构
- `test:` - 添加测试
- `style:` - 格式化更改

### 6. 推送并创建 Pull Request

```bash
git push origin your-branch-name
```

然后在 GitHub 上创建 Pull Request，包含：
- 清楚描述更改的标题
- 描述更改了什么以及为什么
- 引用相关问题（如 "Fixes #123"）
- UI 更改的截图

## 🧪 测试

### 单元测试
- 为新的计算函数编写单元测试
- 测试边界情况和错误条件
- 追求良好的测试覆盖率

### UI 测试
- 为 UI 更改添加仪器测试
- 测试不同的屏幕方向
- 验证无障碍功能

### 手动测试
- 在不同设备尺寸上测试
- 验证计算准确性
- 检查历史记录持久化功能

## 🎨 UI/UX 指南

### 设计原则
- 遵循 Material Design 3 指南
- 与现有 UI 保持一致性
- 确保无障碍性（对比度、触摸目标）
- 支持竖屏和横屏方向

### 添加新 UI 元素
- 使用现有的配色方案和排版
- 确保适当的间距和对齐
- 添加合适的动画和过渡
- 在不同屏幕密度上测试

## 🚫 禁止事项

- 不要在没有讨论的情况下引入破坏性更改
- 不要在没有充分理由的情况下添加依赖项
- 不要在未经批准的情况下删除现有功能
- 不要直接提交到主分支
- 不要在提交中包含个人信息

## 📋 Pull Request 检查清单

提交 PR 前，请确保：

- [ ] 代码遵循项目风格指南
- [ ] 所有测试通过
- [ ] 新功能有适当的测试
## 🆘 寻求帮助

如果您需要帮助：

1. **文档**：查看现有文档和代码注释
2. **问题**：在现有问题中搜索类似问题
3. **讨论**：使用 GitHub Discussions 提问
4. **联系**：通过 GitHub 联系维护者

## 🎯 贡献领域

我们特别欢迎在这些领域的贡献：

### 高优先级
- 单元测试覆盖率改进
- 性能优化
- 无障碍功能增强
- Bug 修复和稳定性改进

### 中等优先级
- 新的计算器功能（如内存操作）
- UI 改进和动画
- 国际化支持
- 代码文档

### 未来功能
- 计算器小部件
- 图形功能
- 主题自定义
- 导出/导入功能

## 📜 许可证

通过贡献，您同意您的贡献将在 Apache License 2.0 下许可。

---

感谢您对科学计算器的贡献！🎉

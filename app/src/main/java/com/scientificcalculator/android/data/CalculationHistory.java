package com.scientificcalculator.android.data;

/**
 * 计算历史记录数据类
 * 用于存储单次计算的表达式、结果和时间戳
 */
public class CalculationHistory {
    private long id;            // 记录ID
    private String expression;  // 计算表达式
    private String result;      // 计算结果
    private long timestamp;     // 计算时间戳

    /**
     * 默认构造函数
     */
    public CalculationHistory() {
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * 带参数的构造函数（用于新建记录）
     * @param expression 计算表达式
     * @param result 计算结果
     */
    public CalculationHistory(String expression, String result) {
        this.expression = expression;
        this.result = result;
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * 完整构造函数（用于数据库查询结果）
     * @param id 记录ID
     * @param expression 计算表达式
     * @param result 计算结果
     * @param timestamp 计算时间戳
     */
    public CalculationHistory(long id, String expression, String result, long timestamp) {
        this.id = id;
        this.expression = expression;
        this.result = result;
        this.timestamp = timestamp;
    }

    // Getter方法 - 获取属性值
    public long getId() {
        return id;
    }

    public String getExpression() {
        return expression;
    }

    public String getResult() {
        return result;
    }

    public long getTimestamp() {
        return timestamp;
    }

    // Setter方法 - 设置属性值
    public void setId(long id) {
        this.id = id;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}

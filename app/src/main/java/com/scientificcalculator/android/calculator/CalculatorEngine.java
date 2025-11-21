package com.scientificcalculator.android.calculator;

import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.function.Function;

/**
 * 计算器引擎类 - 负责数学表达式的解析和计算
 * 支持基础运算、科学函数、常量等复杂数学计算
 */
public class CalculatorEngine {

    private static final String PI = "π";        // 圆周率符号
    private static final String E = "e";         // 自然常数符号
    private static final String MULTIPLY = "×";  // 乘法符号
    private static final String DIVIDE = "÷";    // 除法符号

    // 自定义阶乘函数
    private final Function factorialFunction = new Function("factorial", 1) {
        @Override
        public double apply(double... args) {
            int n = (int) args[0];
            if (n < 0) {
                throw new IllegalArgumentException("阶乘不能计算负数");
            }
            if (n > 170) {
                throw new IllegalArgumentException("阶乘数值过大，超出计算范围");
            }

            double result = 1.0;
            for (int i = 2; i <= n; i++) {
                result *= i;
            }
            return result;
        }
    };

    /**
     * 计算数学表达式
     * @param expression 输入的数学表达式字符串
     * @return 计算结果字符串，如果出错返回"错误"
     */
    public String evaluate(String expression) {
        try {
            String processedExpression = preprocessExpression(expression);

            if (processedExpression.trim().isEmpty()) {
                return "0";
            } else {
                double result = new ExpressionBuilder(processedExpression)
                        .functions(factorialFunction)         // 添加阶乘函数
                        .variables("π", "e")                  // 添加数学常量
                        .build()
                        .setVariable("π", Math.PI)            // 设置π值
                        .setVariable("e", Math.E)             // 设置e值
                        .evaluate();

                return formatResult(result);
            }
        } catch (Exception e) {
            return "错误";
        }
    }

    /**
     * 预处理表达式，将用户输入的符号转换为计算引擎能识别的格式
     * @param expression 原始表达式
     * @return 处理后的表达式
     */
    private String preprocessExpression(String expression) {
        String processed = expression
                .replace(PI, "π")                    // 替换π符号
                .replace(E, "e")                     // 替换e符号
                .replace(MULTIPLY, "*")              // 替换×为*
                .replace(DIVIDE, "/")                // 替换÷为/
                .replace("%", "/100");               // 处理百分号

        // 处理阶乘：将数字!转换为factorial(数字)
        processed = processed.replaceAll("(\\d+)!", "factorial($1)");

        // 处理平方根：将√转换为sqrt
        processed = processed.replace("√", "sqrt");

        // 处理幂运算 - exp4j使用^操作符，不需要替换为pow
        // processed = processed.replace("^", "pow");

        // 处理对数函数
        processed = processed.replace("ln", "log");      // ln转换为自然对数
        processed = processed.replace("log", "log10");   // log转换为常用对数

        // 处理三角函数 (将角度转换为弧度)
        processed = processed.replaceAll("sin\\(([^)]+)\\)", "sin($1 * π / 180)");
        processed = processed.replaceAll("cos\\(([^)]+)\\)", "cos($1 * π / 180)");
        processed = processed.replaceAll("tan\\(([^)]+)\\)", "tan($1 * π / 180)");

        return processed;
    }

    /**
     * 格式化计算结果
     * @param result 原始计算结果
     * @return 格式化后的结果字符串
     */
    private String formatResult(double result) {
        if (Double.isNaN(result)) {
            return "错误";          // 非数值
        }
        if (Double.isInfinite(result)) {
            return "∞";             // 无穷大
        }
        if (result == (long) result) {
            return String.valueOf((long) result);  // 整数结果
        } else {
            String formatted = String.format("%.10g", result);
            // 移除末尾的零
            if (formatted.contains(".")) {
                formatted = formatted.replaceAll("0*$", "").replaceAll("\\.$", "");
            }
            return formatted;
        }
    }
}

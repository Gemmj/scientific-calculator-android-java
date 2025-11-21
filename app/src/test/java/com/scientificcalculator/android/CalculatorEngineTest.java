package com.scientificcalculator.android;

import org.junit.Test;

import static org.junit.Assert.*;

import com.scientificcalculator.android.calculator.CalculatorEngine;

/**
 * 计算器引擎测试类
 * 测试基础运算、科学函数和错误处理功能
 */
public class CalculatorEngineTest {

    private final CalculatorEngine calculatorEngine = new CalculatorEngine();

    @Test
    public void testBasicArithmetic() {
        // 基础四则运算测试
        assertEquals("4", calculatorEngine.evaluate("2+2"));
        assertEquals("6", calculatorEngine.evaluate("3×2"));
        assertEquals("2", calculatorEngine.evaluate("6÷3"));
        assertEquals("1", calculatorEngine.evaluate("3-2"));

        // 小数计算测试
        assertEquals("3.5", calculatorEngine.evaluate("2.5+1"));
        assertEquals("0.5", calculatorEngine.evaluate("1.5-1"));
    }

    @Test
    public void testScientificFunctions() {
        // 科学函数测试
        assertEquals("1", calculatorEngine.evaluate("sin(90)"));

        // cos(90) 在数值计算中会有很小的误差，接近0但不完全是0
        String cosResult = calculatorEngine.evaluate("cos(90)");
        assertTrue("cos(90) 应该非常接近0",
            Math.abs(Double.parseDouble(cosResult)) < 1e-10);

        assertEquals("2", calculatorEngine.evaluate("√(4)"));
        assertEquals("8", calculatorEngine.evaluate("2^3"));

        // 数学常量测试
        String piResult = calculatorEngine.evaluate("π");
        assertTrue(piResult.startsWith("3.14"));
        String eResult = calculatorEngine.evaluate("e");
        assertTrue(eResult.startsWith("2.71"));
    }

    @Test
    public void testComplexExpressions() {
        // 复合表达式测试
        assertEquals("10", calculatorEngine.evaluate("2×(3+2)"));
        assertEquals("14", calculatorEngine.evaluate("2+3×4"));
        assertEquals("20", calculatorEngine.evaluate("(2+3)×4"));
    }

    @Test
    public void testErrorHandling() {
        // 错误处理测试
        assertEquals("错误", calculatorEngine.evaluate("1÷0"));
        assertEquals("0", calculatorEngine.evaluate(""));  // 空字符串返回0，这是正确的行为
        assertEquals("错误", calculatorEngine.evaluate("invalid"));
    }

    @Test
    public void testFactorial() {
        // 阶乘运算测试
        assertEquals("1", calculatorEngine.evaluate("0!"));
        assertEquals("1", calculatorEngine.evaluate("1!"));
        assertEquals("2", calculatorEngine.evaluate("2!"));
        assertEquals("6", calculatorEngine.evaluate("3!"));
        assertEquals("24", calculatorEngine.evaluate("4!"));
        assertEquals("120", calculatorEngine.evaluate("5!"));
    }

    @Test
    public void testPercentage() {
        // 百分比计算测试
        assertEquals("0.1", calculatorEngine.evaluate("10%"));
        assertEquals("0.5", calculatorEngine.evaluate("50%"));
        assertEquals("1", calculatorEngine.evaluate("100%"));
    }
}

package com.example.myapplication1

import org.junit.Test
import org.junit.Assert.*
import com.example.myapplication1.calculator.CalculatorEngine

/**
 * 科学计算器功能测试
 */
class CalculatorEngineTest {

    private val calculatorEngine = CalculatorEngine()

    @Test
    fun testBasicArithmetic() {
        // 基本四则运算测试
        assertEquals("4", calculatorEngine.evaluate("2+2"))
        assertEquals("6", calculatorEngine.evaluate("3×2"))
        assertEquals("2", calculatorEngine.evaluate("6÷3"))
        assertEquals("1", calculatorEngine.evaluate("3-2"))

        // 小数计算
        assertEquals("3.5", calculatorEngine.evaluate("2.5+1"))
        assertEquals("0.5", calculatorEngine.evaluate("1.5-1"))
    }

    @Test
    fun testScientificFunctions() {
        // 科学函数测试
        assertEquals("1", calculatorEngine.evaluate("sin(90)"))
        assertEquals("0", calculatorEngine.evaluate("cos(90)"))
        assertEquals("2", calculatorEngine.evaluate("√(4)"))
        assertEquals("8", calculatorEngine.evaluate("2^3"))

        // 常数测试
        assertTrue(calculatorEngine.evaluate("π").startsWith("3.14"))
        assertTrue(calculatorEngine.evaluate("e").startsWith("2.71"))
    }

    @Test
    fun testComplexExpressions() {
        // 复合表达式测试
        assertEquals("10", calculatorEngine.evaluate("2×(3+2)"))
        assertEquals("14", calculatorEngine.evaluate("2+3×4"))
        assertEquals("20", calculatorEngine.evaluate("(2+3)×4"))
    }

    @Test
    fun testErrorHandling() {
        // 错误处理测试
        assertEquals("错误", calculatorEngine.evaluate("1/0"))
        assertEquals("错误", calculatorEngine.evaluate("sin("))
        assertEquals("错误", calculatorEngine.evaluate("2++3"))
    }

    @Test
    fun testValidation() {
        // 表达式验证测试
        assertTrue(calculatorEngine.isValidExpression("2+2"))
        assertTrue(calculatorEngine.isValidExpression("sin(45)"))
        assertFalse(calculatorEngine.isValidExpression("2++3"))
        assertFalse(calculatorEngine.isValidExpression(""))
    }
}

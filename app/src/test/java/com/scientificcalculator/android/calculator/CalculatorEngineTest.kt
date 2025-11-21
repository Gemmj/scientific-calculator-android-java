package com.scientificcalculator.android.calculator

import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

/**
 * Unit tests for CalculatorEngine
 * Tests basic arithmetic, scientific functions, and error handling
 */
class CalculatorEngineTest {

    private lateinit var calculatorEngine: CalculatorEngine

    @Before
    fun setup() {
        calculatorEngine = CalculatorEngine()
    }

    @Test
    fun testBasicArithmetic() {
        // Basic arithmetic operations
        assertEquals("4.0", calculatorEngine.evaluate("2+2"))
        assertEquals("6.0", calculatorEngine.evaluate("3×2"))
        assertEquals("2.0", calculatorEngine.evaluate("6÷3"))
        assertEquals("1.0", calculatorEngine.evaluate("3-2"))

        // Decimal calculations
        assertEquals("3.5", calculatorEngine.evaluate("2.5+1"))
        assertEquals("0.5", calculatorEngine.evaluate("1.5-1"))
    }

    @Test
    fun testScientificFunctions() {
        // Trigonometric functions (degrees)
        assertEquals("1.0", calculatorEngine.evaluate("sin(90)"))
        assertEquals("0.0", calculatorEngine.evaluate("cos(90)"))

        // Square root
        assertEquals("2.0", calculatorEngine.evaluate("√(4)"))

        // Power operations
        assertEquals("8.0", calculatorEngine.evaluate("2^3"))
        assertEquals("9.0", calculatorEngine.evaluate("3^2"))

        // Mathematical constants
        val piResult = calculatorEngine.evaluate("π")
        assertTrue("π should start with 3.14", piResult.startsWith("3.14"))

        val eResult = calculatorEngine.evaluate("e")
        assertTrue("e should start with 2.71", eResult.startsWith("2.71"))
    }

    @Test
    fun testComplexExpressions() {
        // Complex expressions with parentheses
        assertEquals("10.0", calculatorEngine.evaluate("2×(3+2)"))
        assertEquals("14.0", calculatorEngine.evaluate("2+3×4"))
        assertEquals("20.0", calculatorEngine.evaluate("(2+3)×4"))
        assertEquals("1.0", calculatorEngine.evaluate("((2+3)×4)÷20"))
    }

    @Test
    fun testPercentageCalculations() {
        // Percentage calculations
        assertEquals("50.0", calculatorEngine.evaluate("100×50%"))
        assertEquals("25.0", calculatorEngine.evaluate("50×50%"))
    }

    @Test
    fun testFactorial() {
        // Factorial calculations
        assertEquals("1.0", calculatorEngine.evaluate("0!"))
        assertEquals("1.0", calculatorEngine.evaluate("1!"))
        assertEquals("6.0", calculatorEngine.evaluate("3!"))
        assertEquals("24.0", calculatorEngine.evaluate("4!"))
        assertEquals("120.0", calculatorEngine.evaluate("5!"))
    }

    @Test
    fun testLogarithms() {
        // Natural logarithm
        val lnResult = calculatorEngine.evaluate("ln(e)")
        assertEquals("1.0", lnResult)

        // Common logarithm
        val logResult = calculatorEngine.evaluate("log(100)")
        assertEquals("2.0", logResult)
    }

    @Test
    fun testErrorHandling() {
        // Division by zero
        val divByZero = calculatorEngine.evaluate("1÷0")
        assertTrue("Should handle division by zero",
            divByZero.contains("错误") || divByZero.contains("Error") || divByZero.contains("Infinity"))

        // Invalid expressions
        val invalidExpr = calculatorEngine.evaluate("2++3")
        assertTrue("Should handle invalid expressions",
            invalidExpr.contains("错误") || invalidExpr.contains("Error"))

        // Empty input
        val empty = calculatorEngine.evaluate("")
        assertTrue("Should handle empty input",
            empty.contains("错误") || empty.contains("Error") || empty == "0")
    }

    @Test
    fun testEdgeCases() {
        // Very small numbers
        val smallResult = calculatorEngine.evaluate("0.1+0.2")
        assertTrue("Should handle floating point precision", smallResult.startsWith("0.3"))

        // Large numbers
        assertEquals("1000000.0", calculatorEngine.evaluate("10^6"))

        // Negative numbers
        assertEquals("-5.0", calculatorEngine.evaluate("3-8"))
        assertEquals("-6.0", calculatorEngine.evaluate("-2×3"))
    }

    @Test
    fun testInputValidation() {
        // Test various input formats
        assertEquals("3.0", calculatorEngine.evaluate(" 1 + 2 ")) // spaces
        assertEquals("5.0", calculatorEngine.evaluate("2.5×2"))     // mixed decimal
    }
}

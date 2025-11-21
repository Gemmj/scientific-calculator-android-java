package com.example.myapplication1.calculator

import net.objecthunter.exp4j.ExpressionBuilder
import net.objecthunter.exp4j.function.Function
import kotlin.math.*

class CalculatorEngine {

    companion object {
        private const val PI = "π"
        private const val E = "e"
        private const val MULTIPLY = "×"
        private const val DIVIDE = "÷"
    }

    // 自定义函数
    private val factorialFunction = object : Function("factorial", 1) {
        override fun apply(vararg args: Double): Double {
            val n = args[0].toInt()
            if (n < 0) throw IllegalArgumentException("Factorial is not defined for negative numbers")
            if (n > 170) throw IllegalArgumentException("Factorial overflow")

            var result = 1.0
            for (i in 2..n) {
                result *= i
            }
            return result
        }
    }

    fun evaluate(expression: String): String {
        return try {
            val processedExpression = preprocessExpression(expression)

            if (processedExpression.isBlank()) {
                "0"
            } else {
                val result = ExpressionBuilder(processedExpression)
                    .functions(factorialFunction)
                    .variables("π", "e")
                    .build()
                    .setVariable("π", Math.PI)
                    .setVariable("e", Math.E)
                    .evaluate()

                formatResult(result)
            }
        } catch (e: Exception) {
            "错误"
        }
    }

    private fun preprocessExpression(expression: String): String {
        var processed = expression
            .replace(PI, "π")
            .replace(E, "e")
            .replace(MULTIPLY, "*")
            .replace(DIVIDE, "/")
            .replace("%", "/100")

        // 处理阶乘
        processed = processed.replace(Regex("(\\d+)!")) { matchResult ->
            "factorial(${matchResult.groupValues[1]})"
        }

        // 处理平方根
        processed = processed.replace("√", "sqrt")

        // 处理幂运算
        processed = processed.replace("^", "pow")

        // 处理对数
        processed = processed.replace("ln", "log")
        processed = processed.replace("log", "log10")

        // 处理三角函数 (转换为弧度)
        processed = processed.replace(Regex("sin\\(([^)]+)\\)")) { matchResult ->
            "sin(${matchResult.groupValues[1]} * π / 180)"
        }
        processed = processed.replace(Regex("cos\\(([^)]+)\\)")) { matchResult ->
            "cos(${matchResult.groupValues[1]} * π / 180)"
        }
        processed = processed.replace(Regex("tan\\(([^)]+)\\)")) { matchResult ->
            "tan(${matchResult.groupValues[1]} * π / 180)"
        }

        return processed
    }

    private fun formatResult(result: Double): String {
        return when {
            result.isNaN() -> "错误"
            result.isInfinite() -> "∞"
            result == result.toLong().toDouble() -> result.toLong().toString()
            else -> {
                val formatted = String.format("%.10g", result)
                // 移除末尾的零
                if (formatted.contains('.')) {
                    formatted.trimEnd('0').trimEnd('.')
                } else {
                    formatted
                }
            }
        }
    }

    fun isValidExpression(expression: String): Boolean {
        return try {
            val processedExpression = preprocessExpression(expression)
            ExpressionBuilder(processedExpression)
                .functions(factorialFunction)
                .variables("π", "e")
                .build()
                .setVariable("π", Math.PI)
                .setVariable("e", Math.E)
                .evaluate()
            true
        } catch (e: Exception) {
            false
        }
    }
}

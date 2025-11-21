package com.example.myapplication1

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.myapplication1.calculator.CalculatorEngine
import com.example.myapplication1.data.CalculationHistory
import com.example.myapplication1.data.DatabaseHelper

class MainActivity : AppCompatActivity() {

    private lateinit var tvExpression: TextView
    private lateinit var tvResult: TextView
    private lateinit var calculatorEngine: CalculatorEngine
    private lateinit var databaseHelper: DatabaseHelper

    private var currentExpression = ""
    private var lastResult = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeViews()
        setupToolbar()
        setupCalculator()
        setupButtons()
    }

    private fun initializeViews() {
        tvExpression = findViewById(R.id.tvExpression)
        tvResult = findViewById(R.id.tvResult)

        // 长按结果可复制到剪贴板
        tvResult.setOnLongClickListener {
            copyResultToClipboard()
            true
        }
    }

    private fun setupToolbar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    private fun setupCalculator() {
        calculatorEngine = CalculatorEngine()
        databaseHelper = DatabaseHelper(this)
    }

    private fun setupButtons() {
        // 数字按钮
        val numberButtons = arrayOf(
            R.id.btn0 to "0", R.id.btn1 to "1", R.id.btn2 to "2", R.id.btn3 to "3",
            R.id.btn4 to "4", R.id.btn5 to "5", R.id.btn6 to "6", R.id.btn7 to "7",
            R.id.btn8 to "8", R.id.btn9 to "9"
        )

        numberButtons.forEach { (id, value) ->
            findViewById<Button>(id).setOnClickListener { appendToExpression(value) }
        }

        // 运算符按钮
        val operatorButtons = arrayOf(
            R.id.btnPlus to "+", R.id.btnMinus to "-",
            R.id.btnMultiply to "×", R.id.btnDivide to "÷",
            R.id.btnLeftBracket to "(", R.id.btnRightBracket to ")",
            R.id.btnDot to ".", R.id.btnPercent to "%"
        )

        operatorButtons.forEach { (id, value) ->
            findViewById<Button>(id).setOnClickListener { appendToExpression(value) }
        }

        // 科学函数按钮
        val functionButtons = arrayOf(
            R.id.btnSin to "sin(", R.id.btnCos to "cos(", R.id.btnTan to "tan(",
            R.id.btnLn to "ln(", R.id.btnLog to "log(", R.id.btnSqrt to "√(",
            R.id.btnPower to "^", R.id.btnFactorial to "!",
            R.id.btnPi to "π", R.id.btnE to "e"
        )

        functionButtons.forEach { (id, value) ->
            findViewById<Button>(id).setOnClickListener {
                if (value.endsWith("(")) {
                    appendToExpression(value)
                } else {
                    appendToExpression(value)
                }
            }
        }

        // 控制按钮
        findViewById<Button>(R.id.btnEquals).setOnClickListener { calculateResult() }
        findViewById<Button>(R.id.btnClear).setOnClickListener { clearLast() }
        findViewById<Button>(R.id.btnAllClear).setOnClickListener { clearAll() }
        findViewById<Button>(R.id.btnDelete).setOnClickListener { deleteLastChar() }
    }

    private fun appendToExpression(value: String) {
        currentExpression += value
        updateDisplay()
        calculatePreview()
    }

    private fun updateDisplay() {
        tvExpression.text = currentExpression
    }

    private fun calculatePreview() {
        if (currentExpression.isNotEmpty()) {
            try {
                val result = calculatorEngine.evaluate(currentExpression)
                if (result != "错误") {
                    tvResult.text = result
                }
            } catch (e: Exception) {
                // 预览计算失败时不显示错误
            }
        }
    }

    private fun calculateResult() {
        if (currentExpression.isEmpty()) return

        val result = calculatorEngine.evaluate(currentExpression)
        tvResult.text = result

        if (result != "错误") {
            // 保存到数据库
            val history = CalculationHistory(
                expression = currentExpression,
                result = result
            )
            databaseHelper.insertCalculation(history)

            lastResult = result
            currentExpression = result
            updateDisplay()
        }
    }

    private fun clearLast() {
        if (currentExpression.isNotEmpty()) {
            currentExpression = lastResult
            updateDisplay()
            tvResult.text = "0"
        }
    }

    private fun clearAll() {
        currentExpression = ""
        lastResult = ""
        tvExpression.text = ""
        tvResult.text = "0"
    }

    private fun deleteLastChar() {
        if (currentExpression.isNotEmpty()) {
            currentExpression = currentExpression.dropLast(1)
            updateDisplay()
            calculatePreview()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_history -> {
                val intent = Intent(this, HistoryActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.menu_clear_history -> {
                showClearHistoryDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun copyResultToClipboard() {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("计算结果", tvResult.text)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(this, "结果已复制到剪贴板", Toast.LENGTH_SHORT).show()
    }

    private fun showClearHistoryDialog() {
        AlertDialog.Builder(this)
            .setTitle("清除历史记录")
            .setMessage("确定要清除所有计算历史记录吗？")
            .setPositiveButton("确定") { _, _ ->
                databaseHelper.clearHistory()
                Toast.makeText(this, "历史记录已清除", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("取消", null)
            .show()
    }

    override fun onDestroy() {
        super.onDestroy()
        databaseHelper.close()
    }
}

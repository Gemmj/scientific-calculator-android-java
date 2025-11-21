package com.scientificcalculator.android;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.scientificcalculator.android.calculator.CalculatorEngine;
import com.scientificcalculator.android.data.CalculationHistory;
import com.scientificcalculator.android.data.DatabaseHelper;

/**
 * 主活动类 - 科学计算器应用的主界面
 * 提供计算器的核心功能，包括基础运算、科学函数、历史记录等
 */
public class MainActivity extends AppCompatActivity {

    private TextView tvExpression;  // 表达式显示框
    private TextView tvResult;      // 结果显示框
    private CalculatorEngine calculatorEngine;  // 计算引擎
    private DatabaseHelper databaseHelper;      // 数据库帮助类

    private String currentExpression = "";  // 当前表达式
    private String lastResult = "";         // 上次计算结果

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();   // 初始化视图
        setupToolbar();      // 设置工具栏
        setupCalculator();   // 设置计算器
        setupButtons();      // 设置按钮事件
    }

    /**
     * 初始化视图组件
     */
    private void initializeViews() {
        tvExpression = findViewById(R.id.tvExpression);
        tvResult = findViewById(R.id.tvResult);

        // 长按结果可复制到剪贴板
        tvResult.setOnLongClickListener(v -> {
            copyResultToClipboard();
            return true;
        });
    }

    /**
     * 设置工具栏
     */
    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    /**
     * 设置计算器引擎和数据库
     */
    private void setupCalculator() {
        calculatorEngine = new CalculatorEngine();
        databaseHelper = new DatabaseHelper(this);
    }

    /**
     * 设置所有按钮的点击事件
     */
    private void setupButtons() {
        // 数字按钮
        int[][] numberButtons = {
                {R.id.btn0, 0}, {R.id.btn1, 1}, {R.id.btn2, 2}, {R.id.btn3, 3},
                {R.id.btn4, 4}, {R.id.btn5, 5}, {R.id.btn6, 6}, {R.id.btn7, 7},
                {R.id.btn8, 8}, {R.id.btn9, 9}
        };

        for (int[] button : numberButtons) {
            Button btn = findViewById(button[0]);
            String value = String.valueOf(button[1]);
            btn.setOnClickListener(v -> appendToExpression(value));
        }

        // 运算符按钮
        Object[][] operatorButtons = {
                {R.id.btnPlus, "+"}, {R.id.btnMinus, "-"},
                {R.id.btnMultiply, "×"}, {R.id.btnDivide, "÷"},
                {R.id.btnLeftBracket, "("}, {R.id.btnRightBracket, ")"},
                {R.id.btnDot, "."}, {R.id.btnPercent, "%"}
        };

        for (Object[] button : operatorButtons) {
            Button btn = findViewById((Integer) button[0]);
            String value = (String) button[1];
            btn.setOnClickListener(v -> appendToExpression(value));
        }

        // 科学函数按钮
        Object[][] functionButtons = {
                {R.id.btnSin, "sin("}, {R.id.btnCos, "cos("}, {R.id.btnTan, "tan("},
                {R.id.btnLn, "ln("}, {R.id.btnLog, "log("}, {R.id.btnSqrt, "√("},
                {R.id.btnPower, "^"}, {R.id.btnFactorial, "!"},
                {R.id.btnPi, "π"}, {R.id.btnE, "e"}
        };

        for (Object[] button : functionButtons) {
            Button btn = findViewById((Integer) button[0]);
            String value = (String) button[1];
            btn.setOnClickListener(v -> appendToExpression(value));
        }

        // 控制按钮
        Button btnEquals = findViewById(R.id.btnEquals);
        btnEquals.setOnClickListener(v -> calculateResult());

        Button btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(v -> clearLast());

        Button btnAllClear = findViewById(R.id.btnAllClear);
        btnAllClear.setOnClickListener(v -> clearAll());

        Button btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(v -> deleteLastChar());
    }

    /**
     * 向表达式添加字符
     * @param value 要添加的字符
     */
    private void appendToExpression(String value) {
        currentExpression += value;
        updateDisplay();        // 更新显示
        calculatePreview();     // 计算预览
    }

    /**
     * 更新表达式显示
     */
    private void updateDisplay() {
        tvExpression.setText(currentExpression);
    }

    /**
     * 计算预览结果（实时计算）
     */
    private void calculatePreview() {
        if (!currentExpression.isEmpty()) {
            try {
                String result = calculatorEngine.evaluate(currentExpression);
                if (!result.equals("错误")) {
                    tvResult.setText(result);
                }
            } catch (Exception e) {
                // 预览计算失败时不显示错误
            }
        }
    }

    /**
     * 计算最终结果（按等号时调用）
     */
    private void calculateResult() {
        if (currentExpression.isEmpty()) return;

        String result = calculatorEngine.evaluate(currentExpression);
        tvResult.setText(result);

        if (!result.equals("错误")) {
            // 保存到数据库
            CalculationHistory history = new CalculationHistory(currentExpression, result);
            databaseHelper.insertCalculation(history);

            lastResult = result;
            currentExpression = result;
            updateDisplay();
        }
    }

    /**
     * 清除当前输入，恢复到上次结果
     */
    private void clearLast() {
        if (!currentExpression.isEmpty()) {
            currentExpression = lastResult;
            updateDisplay();
            tvResult.setText("0");
        }
    }

    /**
     * 清除所有内容
     */
    private void clearAll() {
        currentExpression = "";
        lastResult = "";
        tvExpression.setText("");
        tvResult.setText("0");
    }

    /**
     * 删除最后一个字符
     */
    private void deleteLastChar() {
        if (!currentExpression.isEmpty()) {
            currentExpression = currentExpression.substring(0, currentExpression.length() - 1);
            updateDisplay();
            calculatePreview();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_history) {
            // 打开历史记录页面
            Intent intent = new Intent(this, HistoryActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_clear_history) {
            // 显示清除历史记录确认对话框
            showClearHistoryDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 复制结果到剪贴板
     */
    private void copyResultToClipboard() {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("计算结果", tvResult.getText());
        clipboard.setPrimaryClip(clip);
        Toast.makeText(this, "结果已复制到剪贴板", Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示清除历史记录确认对话框
     */
    private void showClearHistoryDialog() {
        new AlertDialog.Builder(this)
                .setTitle("清除历史记录")
                .setMessage("确定要清除所有计算历史记录吗？")
                .setPositiveButton("确定", (dialog, which) -> {
                    databaseHelper.clearHistory();
                    Toast.makeText(this, "历史记录已清除", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("取消", null)
                .show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (databaseHelper != null) {
            databaseHelper.close();
        }
    }
}

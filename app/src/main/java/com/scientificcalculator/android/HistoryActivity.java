package com.scientificcalculator.android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scientificcalculator.android.adapter.HistoryAdapter;
import com.scientificcalculator.android.data.CalculationHistory;
import com.scientificcalculator.android.data.DatabaseHelper;

import java.util.List;

/**
 * 历史记录活动类 - 显示计算历史记录
 * 用户可以查看之前的计算记录，并点击重新使用
 */
public class HistoryActivity extends AppCompatActivity implements HistoryAdapter.OnItemClickListener {

    private RecyclerView recyclerView;      // 历史记录列表
    private TextView emptyStateView;        // 空状态提示文本
    private HistoryAdapter historyAdapter;  // 历史记录适配器
    private DatabaseHelper databaseHelper; // 数据库帮助类

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        initializeViews();    // 初始化视图
        setupToolbar();       // 设置工具栏
        setupRecyclerView();  // 设置列表视图
        loadHistory();        // 加载历史记录
    }

    /**
     * 初始化视图组件
     */
    private void initializeViews() {
        recyclerView = findViewById(R.id.recyclerViewHistory);
        emptyStateView = findViewById(R.id.tvEmptyState);
        databaseHelper = new DatabaseHelper(this);
    }

    /**
     * 设置工具栏，添加返回按钮
     */
    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setNavigationOnClickListener(v -> finish());
    }

    /**
     * 设置历史记录列表视图
     */
    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        historyAdapter = new HistoryAdapter(null, this);
        recyclerView.setAdapter(historyAdapter);
    }

    /**
     * 加载历史记录数据
     */
    private void loadHistory() {
        List<CalculationHistory> historyList = databaseHelper.getAllHistory();

        if (historyList.isEmpty()) {
            // 没有历史记录时显示空状态
            recyclerView.setVisibility(View.GONE);
            emptyStateView.setVisibility(View.VISIBLE);
        } else {
            // 有历史记录时显示列表
            recyclerView.setVisibility(View.VISIBLE);
            emptyStateView.setVisibility(View.GONE);
            historyAdapter.updateHistory(historyList);
        }
    }

    @Override
    public void onItemClick(CalculationHistory history) {
        // 点击历史记录项时，将表达式返回给主界面
        Intent intent = new Intent();
        intent.putExtra("expression", history.getExpression());
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (databaseHelper != null) {
            databaseHelper.close();
        }
    }
}

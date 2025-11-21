package com.example.myapplication1

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication1.adapter.HistoryAdapter
import com.example.myapplication1.data.DatabaseHelper

class HistoryActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyStateView: TextView
    private lateinit var historyAdapter: HistoryAdapter
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        initializeViews()
        setupToolbar()
        setupRecyclerView()
        loadHistory()
    }

    private fun initializeViews() {
        recyclerView = findViewById(R.id.recyclerViewHistory)
        emptyStateView = findViewById(R.id.tvEmptyState)
        databaseHelper = DatabaseHelper(this)
    }

    private fun setupToolbar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)

        historyAdapter = HistoryAdapter(emptyList()) { history ->
            // 点击历史记录项时，将表达式返回给主界面
            setResult(RESULT_OK, intent.apply {
                putExtra("expression", history.expression)
            })
            finish()
        }

        recyclerView.adapter = historyAdapter
    }

    private fun loadHistory() {
        val historyList = databaseHelper.getAllHistory()

        if (historyList.isEmpty()) {
            recyclerView.visibility = View.GONE
            emptyStateView.visibility = View.VISIBLE
        } else {
            recyclerView.visibility = View.VISIBLE
            emptyStateView.visibility = View.GONE
            historyAdapter.updateHistory(historyList)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        databaseHelper.close()
    }
}

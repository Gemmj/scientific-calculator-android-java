package com.example.myapplication1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication1.R
import com.example.myapplication1.data.CalculationHistory
import java.text.SimpleDateFormat
import java.util.*

class HistoryAdapter(
    private var historyList: List<CalculationHistory>,
    private val onItemClick: (CalculationHistory) -> Unit
) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

    class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvExpression: TextView = itemView.findViewById(R.id.tvExpression)
        val tvResult: TextView = itemView.findViewById(R.id.tvResult)
        val tvTimestamp: TextView = itemView.findViewById(R.id.tvTimestamp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_history, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val history = historyList[position]

        holder.tvExpression.text = history.expression
        holder.tvResult.text = "= ${history.result}"
        holder.tvTimestamp.text = dateFormat.format(Date(history.timestamp))

        holder.itemView.setOnClickListener {
            onItemClick(history)
        }
    }

    override fun getItemCount(): Int = historyList.size

    fun updateHistory(newHistoryList: List<CalculationHistory>) {
        historyList = newHistoryList
        notifyDataSetChanged()
    }
}

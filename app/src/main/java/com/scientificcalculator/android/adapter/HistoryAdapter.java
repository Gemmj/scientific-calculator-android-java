package com.scientificcalculator.android.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.scientificcalculator.android.R;
import com.scientificcalculator.android.data.CalculationHistory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 历史记录适配器类 - 管理历史记录列表的显示
 * 用于RecyclerView显示计算历史记录，支持点击事件
 */
public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private List<CalculationHistory> historyList;        // 历史记录列表
    private final OnItemClickListener onItemClickListener;  // 点击事件监听器
    private final SimpleDateFormat dateFormat;              // 日期格式化器

    /**
     * 列表项点击事件接口
     */
    public interface OnItemClickListener {
        void onItemClick(CalculationHistory history);
    }

    /**
     * 构造函数
     * @param historyList 历史记录列表
     * @param onItemClickListener 点击事件监听器
     */
    public HistoryAdapter(List<CalculationHistory> historyList, OnItemClickListener onItemClickListener) {
        this.historyList = historyList;
        this.onItemClickListener = onItemClickListener;
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    }

    /**
     * 视图持有者类 - 缓存列表项的视图组件
     */
    public static class HistoryViewHolder extends RecyclerView.ViewHolder {
        public TextView tvExpression;  // 表达式文本视图
        public TextView tvResult;      // 结果文本视图
        public TextView tvTimestamp;   // 时间戳文本视图

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvExpression = itemView.findViewById(R.id.tvExpression);
            tvResult = itemView.findViewById(R.id.tvResult);
            tvTimestamp = itemView.findViewById(R.id.tvTimestamp);
        }
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_history, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        CalculationHistory history = historyList.get(position);

        // 设置显示内容
        holder.tvExpression.setText(history.getExpression());
        holder.tvResult.setText("= " + history.getResult());
        holder.tvTimestamp.setText(dateFormat.format(new Date(history.getTimestamp())));

        // 设置点击事件
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(history);
            }
        });
    }

    @Override
    public int getItemCount() {
        return historyList != null ? historyList.size() : 0;
    }

    /**
     * 更新历史记录列表
     * @param newHistoryList 新的历史记录列表
     */
    public void updateHistory(List<CalculationHistory> newHistoryList) {
        this.historyList = newHistoryList;
        notifyDataSetChanged();  // 通知适配器数据已更改
    }
}

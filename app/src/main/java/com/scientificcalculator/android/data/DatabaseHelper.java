package com.scientificcalculator.android.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库帮助类 - 管理计算历史记录的存储
 * 负责创建、更新数据库，以及对历史记录的增删改查操作
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "calculator.db";    // 数据库名称
    private static final int DATABASE_VERSION = 1;                  // 数据库版本

    private static final String TABLE_HISTORY = "calculation_history";  // 历史记录表名
    private static final String COLUMN_ID = "id";                       // ID列
    private static final String COLUMN_EXPRESSION = "expression";       // 表达式列
    private static final String COLUMN_RESULT = "result";               // 结果列
    private static final String COLUMN_TIMESTAMP = "timestamp";         // 时间戳列

    // 创建历史记录表的SQL语句
    private static final String CREATE_TABLE_HISTORY =
            "CREATE TABLE " + TABLE_HISTORY + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_EXPRESSION + " TEXT NOT NULL, " +
                    COLUMN_RESULT + " TEXT NOT NULL, " +
                    COLUMN_TIMESTAMP + " INTEGER NOT NULL" +
                    ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_HISTORY);  // 创建历史记录表
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY);  // 删除旧表
        onCreate(db);  // 重新创建表
    }

    /**
     * 插入一条计算历史记录
     * @param history 计算历史记录对象
     * @return 插入记录的ID
     */
    public long insertCalculation(CalculationHistory history) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EXPRESSION, history.getExpression());
        values.put(COLUMN_RESULT, history.getResult());
        values.put(COLUMN_TIMESTAMP, history.getTimestamp());

        return db.insert(TABLE_HISTORY, null, values);
    }

    /**
     * 获取所有计算历史记录
     * @return 按时间倒序排列的历史记录列表
     */
    public List<CalculationHistory> getAllHistory() {
        List<CalculationHistory> historyList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_HISTORY,
                null,
                null,
                null,
                null,
                null,
                COLUMN_TIMESTAMP + " DESC"  // 按时间倒序排列
        );

        if (cursor != null) {
            try {
                while (cursor.moveToNext()) {
                    // 获取各列的索引
                    int idIndex = cursor.getColumnIndexOrThrow(COLUMN_ID);
                    int expressionIndex = cursor.getColumnIndexOrThrow(COLUMN_EXPRESSION);
                    int resultIndex = cursor.getColumnIndexOrThrow(COLUMN_RESULT);
                    int timestampIndex = cursor.getColumnIndexOrThrow(COLUMN_TIMESTAMP);

                    // 创建历史记录对象
                    CalculationHistory history = new CalculationHistory(
                            cursor.getLong(idIndex),
                            cursor.getString(expressionIndex),
                            cursor.getString(resultIndex),
                            cursor.getLong(timestampIndex)
                    );
                    historyList.add(history);
                }
            } finally {
                cursor.close();  // 确保释放游标资源
            }
        }

        return historyList;
    }

    /**
     * 清除所有计算历史记录
     */
    public void clearHistory() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_HISTORY, null, null);
    }

    /**
     * 删除指定ID的计算记录
     * @param id 要删除的记录ID
     * @return 删除的记录数量
     */
    public int deleteCalculation(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_HISTORY, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }
}

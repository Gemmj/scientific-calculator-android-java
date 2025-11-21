package com.example.myapplication1.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "calculator.db"
        private const val DATABASE_VERSION = 1

        private const val TABLE_HISTORY = "calculation_history"
        private const val COLUMN_ID = "id"
        private const val COLUMN_EXPRESSION = "expression"
        private const val COLUMN_RESULT = "result"
        private const val COLUMN_TIMESTAMP = "timestamp"

        private const val CREATE_TABLE_HISTORY = """
            CREATE TABLE $TABLE_HISTORY (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_EXPRESSION TEXT NOT NULL,
                $COLUMN_RESULT TEXT NOT NULL,
                $COLUMN_TIMESTAMP INTEGER NOT NULL
            )
        """
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_HISTORY)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_HISTORY")
        onCreate(db)
    }

    fun insertCalculation(history: CalculationHistory): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_EXPRESSION, history.expression)
            put(COLUMN_RESULT, history.result)
            put(COLUMN_TIMESTAMP, history.timestamp)
        }
        return db.insert(TABLE_HISTORY, null, values)
    }

    fun getAllHistory(): List<CalculationHistory> {
        val historyList = mutableListOf<CalculationHistory>()
        val db = this.readableDatabase
        val cursor = db.query(
            TABLE_HISTORY,
            null,
            null,
            null,
            null,
            null,
            "$COLUMN_TIMESTAMP DESC"
        )

        cursor.use {
            while (it.moveToNext()) {
                val history = CalculationHistory(
                    id = it.getLong(it.getColumnIndexOrThrow(COLUMN_ID)),
                    expression = it.getString(it.getColumnIndexOrThrow(COLUMN_EXPRESSION)),
                    result = it.getString(it.getColumnIndexOrThrow(COLUMN_RESULT)),
                    timestamp = it.getLong(it.getColumnIndexOrThrow(COLUMN_TIMESTAMP))
                )
                historyList.add(history)
            }
        }
        return historyList
    }

    fun clearHistory() {
        val db = this.writableDatabase
        db.delete(TABLE_HISTORY, null, null)
    }

    fun deleteCalculation(id: Long): Int {
        val db = this.writableDatabase
        return db.delete(TABLE_HISTORY, "$COLUMN_ID = ?", arrayOf(id.toString()))
    }
}

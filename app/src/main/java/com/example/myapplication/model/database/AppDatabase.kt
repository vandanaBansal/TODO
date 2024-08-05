package com.example.myapplication.model.database


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.model.dao.TodoDao
import com.example.myapplication.model.data.TodoItem


@Database(entities = [TodoItem::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getTodoDao(): TodoDao
}
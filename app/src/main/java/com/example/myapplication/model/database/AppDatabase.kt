package com.example.myapplication.model.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.model.dao.TodoDao
import com.example.myapplication.model.data.TodoItem
import com.example.myapplication.utils.Constants


@Database(entities = [TodoItem::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getTodoDao(): TodoDao
}
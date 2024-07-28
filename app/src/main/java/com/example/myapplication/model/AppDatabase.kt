package com.example.myapplication.model

import android.content.Context
import android.provider.ContactsContract.Data
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TodoItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun todoDao(): TodoDao

    companion object {
        private var instance: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {

            if (instance == null) {
                synchronized(this) {
                    instance = Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        "todo_db"
                    ).build()
                }
            }
            return instance!!
        }
    }

}
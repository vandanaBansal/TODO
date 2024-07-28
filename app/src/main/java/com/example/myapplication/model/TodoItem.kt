package com.example.myapplication.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class TodoItem(
// Primary Key with Auto-Generate
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    var isDone: Boolean = false)
package com.example.myapplication

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.myapplication.model.dao.TodoDao
import com.example.myapplication.model.data.TodoItem
import com.example.myapplication.model.database.AppDatabase
import com.example.myapplication.model.repository.TodoRepository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain


import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class TodoRepositoryTest {
    private var toDoDao: TodoDao? = null
    private lateinit var toDoDatabase: AppDatabase
    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {

        val context = ApplicationProvider.getApplicationContext<Context>()
        toDoDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .build()
        toDoDao = toDoDatabase.getTodoDao()
        Dispatchers.setMain(testDispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Throws(IOException::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cancel()
        toDoDatabase.close()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    @Throws(Exception::class)
    fun getAllTodos() {
        runTest {
            val userEntity = TodoItem(1, "Demo insert")
            launch {    toDoDao?.insert(userEntity) }

            advanceUntilIdle()
            val todoResult = toDoDao?.getAllTodos()?.toList()
            Assert.assertEquals(1, todoResult?.size)

        }
    }
}
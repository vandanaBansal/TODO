package com.example.myapplication.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.R
import com.example.myapplication.model.data.TodoItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Composable
fun MainScreen(
    todoItemsFlow: Flow<List<TodoItem>> = flowOf(listOf())
) {
    // Flow Data Collection
    val todos = todoItemsFlow.collectAsState(initial = listOf()).value
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    // LazyColumn Setup
    Column {
        MyAppBar()
        SearchScreen(textState)
        if(todos.isEmpty()){
            AddText()
        }
        TodoItemsContainer(todos,textState)
    }

}

@Composable
private fun TodoItemsContainer(todos: List<TodoItem>, state: MutableState<TextFieldValue>) {
    var filteredToDoList: List<TodoItem>
    LazyColumn(
        contentPadding = PaddingValues(dimensionResource(R.dimen.Medium)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.Medium))
    ) {
        val searchedText = state.value.text

        filteredToDoList = if (searchedText.isEmpty()) {
            todos
        } else {
            val resultList = ArrayList<TodoItem>()
            for (value in todos) {
                if (value.title.lowercase()
                        .contains(searchedText.lowercase())
                ) {
                    resultList.add(value)
                }
            }
            resultList
        }
        // Items Rendering
        items(filteredToDoList, key = { it.id }) { item ->
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Text(
                    text = item.title,
                    modifier = Modifier.weight(1f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,

                    )
            }

        }
        //  Layout Adjustment
        item { Spacer(modifier = Modifier.height(dimensionResource(R.dimen.TodoItemHeight))) }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MyAppBar() {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = colorResource(id = R.color.green),
            titleContentColor = Color.White
        ),
        title = {
            Text("Main Screen")
        },

        )
}

@Composable
private fun AddText() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Press the + button to add a TODO \n" +
                    "item"
        )
    }

}

@Preview
@Composable
fun TodoItemsContainerPreview() {
    MainScreen(
        todoItemsFlow = flowOf(
            listOf(
                TodoItem(title = "Todo Item 1", isDone = true),
                TodoItem(title = "Todo Item 2"),
                TodoItem(title = "Todo Item 3"),
                TodoItem(title = "Todo Item 4", isDone = true),
            )
        )
    )
}
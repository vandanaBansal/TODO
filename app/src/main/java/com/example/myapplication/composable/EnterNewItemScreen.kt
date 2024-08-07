package com.example.myapplication.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R


@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    onBackButtonClick: () -> Unit = {},
    onAddButtonClick: (String) -> Unit = {},

    ) {
    // State Management
    val input = rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = modifier,
    ) {
        MyDetailBar(onBackButtonClick)

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            // Data Binding
            value = input.value,
            // Event Handling
            onValueChange = { newText -> input.value = newText },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.todo_input_bar_hint),
                )
            },
            singleLine = true,
        )
        Button(
            onClick = {
                if (input.value.isNotEmpty()) {
                    keyboardController?.hide()
                    onAddButtonClick(input.value)
                    input.value = ""

                }

            },
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.dark_green)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(40.dp),
            shape = RoundedCornerShape(15.dp),
            enabled = true
        ) {

            Text(
                text = stringResource(R.string.add_todo),
                fontSize = 20.sp,
                modifier =
                Modifier
                    .padding(10.dp),
            )

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MyDetailBar(onBackButtonClick: () -> Unit) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = colorResource(id = R.color.green),
            titleContentColor = Color.White
        ),
        title = { Text("Detail Screen") },
        navigationIcon = {
            IconButton(onClick = {
                onBackButtonClick()
            }) {
                Icon(
                    Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White
                )
            }
        }
    )
}



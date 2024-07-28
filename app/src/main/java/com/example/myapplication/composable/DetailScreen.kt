package com.example.myapplication.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    onAddButtonClick: (String) -> Unit = {}
){
    // 1. State Management
    val input = remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = modifier,
    ) {
        OutlinedTextField(
            modifier=Modifier.fillMaxWidth(),
            // 4. Data Binding
            value = input.value,
            // 5. Event Handling
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
                if (input.value.isNotEmpty()){
                    keyboardController?.hide()
                    onAddButtonClick(input.value)
                    input.value = ""
//                    navController.navigate(Screen.MainScreen.route)
                }

            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
            modifier = Modifier
                .fillMaxWidth()
                .padding(40.dp),
            shape = RoundedCornerShape(15.dp),
            enabled = true

        ) {
            Text(
                text = "ADD TODO",
                fontSize = 20.sp,
                modifier =
                Modifier
                    .padding(10.dp),
            )
        }
    }
}
package com.example.myapplication.view

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R

@Composable
fun FAB(modifier: Modifier = Modifier,
        onAddButtonClick: () -> Unit = {}){
    Row(
        modifier = modifier.padding(20.dp),
    ) {

        FloatingActionButton(
            containerColor = colorResource(id = R.color.orange),
            onClick = {
                // 1. Task Submission Logic
                onAddButtonClick()

            },
            // 2. FAB Customization
            shape = CircleShape,
            modifier = Modifier.size(dimensionResource(R.dimen.TodoInputBarFabSize)),
            elevation = FloatingActionButtonDefaults.elevation(
                defaultElevation = 0.dp,
                pressedElevation = 0.dp
            )
        ) {
            Icon(
                Icons.Filled.Add, "Large floating action button"
            )
        }
        Spacer(modifier = Modifier.width(20.dp))
    }

}
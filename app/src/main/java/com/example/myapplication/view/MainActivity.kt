package com.example.myapplication.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.composable.DetailScreen
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.composable.FAB
import com.example.myapplication.composable.LoadingBar
import com.example.myapplication.composable.MainScreen
import com.example.myapplication.model.Screen
import com.example.myapplication.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val mainViewModel = viewModels<MainViewModel>()
                    SetupNavGraph(navController = navController, mainViewModel)
                }
            }
        }
    }
}


@Composable
fun HomeScreen(navController: NavHostController, mainViewModel: Lazy<MainViewModel>) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        MainScreen(
            todoItemsFlow = mainViewModel.value.todos,
        )
        FAB(
            modifier = Modifier.align(Alignment.BottomEnd),
            onAddButtonClick = { navController.navigate(Screen.DetailScreen.route) }
        )

    }
}

@Composable
fun SetupNavGraph(navController: NavHostController, mainViewModel: Lazy<MainViewModel>) {
    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route
    ) {
        composable(route = Screen.DetailScreen.route) {
            DetailScreen(
                modifier = Modifier,
                onBackButtonClick = { navController.navigate(Screen.MainScreen.route) },
                onAddButtonClick = {
                    mainViewModel.value.addTodo(it)
                    mainViewModel.value.longProcess()
                },
            )
            val state = mainViewModel.value.state.collectAsStateWithLifecycle()
            if (state.value.loading) {
                LoadingBar()
                navController.navigate(Screen.MainScreen.route)
            }
        }

        composable(route = Screen.MainScreen.route) {
            HomeScreen(navController, mainViewModel)
        }

    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {

    }
}
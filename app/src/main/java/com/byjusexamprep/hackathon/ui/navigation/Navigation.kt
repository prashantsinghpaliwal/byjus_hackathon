package com.byjusexamprep.hackathon.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.byjusexamprep.hackathon.ui.composables.home_screen.MainScreenComposable
import com.byjusexamprep.hackathon.ui.composables.home_screen.MainViewModel
import com.byjusexamprep.hackathon.ui.composables.name_screen.NameScreenComposable

@Composable
fun Navigation(mainViewModel: MainViewModel) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            MainScreenComposable(navController = navController, viewModel = mainViewModel)
        }

        composable(route = Screen.NameScreen.route) {
            NameScreenComposable(navController = navController)
        }
    }

}
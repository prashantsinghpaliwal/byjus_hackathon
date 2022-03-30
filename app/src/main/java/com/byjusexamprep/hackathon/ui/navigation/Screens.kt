package com.byjusexamprep.hackathon.ui.navigation

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Star
import androidx.compose.ui.graphics.vector.ImageVector


sealed class Screen(val route : String, val title: String = "") {

    object LoginScreen : Screen("login_screen")
    object OtpScreen : Screen("otp_screen")
    object MainScreen : Screen("main_screen")
    object NameScreen : Screen("name_screen")


    // It will work for mandatory params only
    // usage -> navController.navigate(Screen.MainScreen.appendArgs(params, params..)
    fun appendArgs(vararg args: String): String{
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}


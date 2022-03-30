package com.byjusexamprep.hackathon.ui.composables.name_screen

import android.os.CountDownTimer
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.byjusexamprep.hackathon.ui.composables.common.GradientButton
import com.byjusexamprep.hackathon.utils.Constants


@Composable
fun NameScreenComposable(navController: NavController) {
    val nameState = rememberSaveable {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(
            value = nameState.value, onValueChange = {
                nameState.value = it
            },
            label = {
                Text(text = "Enter Name")
            },
            singleLine = true,
            textStyle = TextStyle(
                fontSize = 18.sp,
                background = MaterialTheme.colors.background
            ),
            modifier = Modifier
                .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
                .fillMaxWidth(),
            enabled = true
        )

        GradientButton(
            text = "Start Playing",
            textColor = Color.White,
            disabledTextColor = Color.Gray,
            isEnabled = true,
            gradient = Constants.primaryGradientBrush,
            disabledGradient = Constants.disabledButtonBrush,
            modifier = Modifier
                .height(52.dp)
                .fillMaxWidth()
                .padding(horizontal = 16.dp)

        ) {

        }
    }

}
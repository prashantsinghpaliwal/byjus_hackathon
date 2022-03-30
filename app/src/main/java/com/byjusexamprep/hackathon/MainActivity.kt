package com.byjusexamprep.hackathon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.byjusexamprep.hackathon.ui.composables.home_screen.MainViewModel
import com.byjusexamprep.hackathon.ui.navigation.Navigation


class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation(mainViewModel)
        }
    }

}
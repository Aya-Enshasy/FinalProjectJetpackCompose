package com.ayaenshasy.finalproject.activities

import android.location.Location
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import com.ayaenshasy.finalproject.navigation1.navigation
import com.ayaenshasy.finalproject.ui.theme.FinalProjectTheme
import com.ayaenshasy.finalproject.viewModel.AllWorkViewModel
import com.ayaenshasy.finalproject.viewModel.LoginViewModel

class MainActivity : ComponentActivity() {

    private val loginViewModel by viewModels<LoginViewModel>()
    val liveDataAllWorkModel by viewModels<AllWorkViewModel>()


    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
             navigation( loginViewModel, liveDataAllWorkModel)
        }
    }
}


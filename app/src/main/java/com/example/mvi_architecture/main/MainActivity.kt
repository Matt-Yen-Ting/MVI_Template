package com.example.mvi_architecture.main

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.navigation.compose.rememberNavController
import com.example.mvi_architecture.NavGraph
import dagger.hilt.android.AndroidEntryPoint
import theme.MobileCAndroidRETheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobileCAndroidRETheme {
                SetUpNavGraph()
            }
        }
        initView()
    }

    private fun initView() {
        ViewCompat.setOnApplyWindowInsetsListener(window.decorView) { view, insets ->
            view.setBackgroundColor(Color.BLACK)
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updatePadding(
                top = systemBars.top,
                bottom = systemBars.bottom
            )
            insets
        }

    }

    @Composable
    fun SetUpNavGraph() {
        val navHostController = rememberNavController()
        NavGraph(navHostController)
    }
}
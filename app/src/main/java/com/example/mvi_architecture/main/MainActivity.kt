package com.example.mvi_architecture.main

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalDensity
import androidx.navigation.compose.rememberNavController
import com.example.mvi_architecture.NavGraph
import dagger.hilt.android.AndroidEntryPoint
import theme.MainTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainTheme {
                SetUpNavGraph()
                StatusBarProtection()
//                BottomBarProtection()

            }
        }
    }

    @Composable
    fun StatusBarProtection() {
        val statusBars = androidx.compose.foundation.layout.WindowInsets.statusBars
        val density = LocalDensity.current
        Canvas(Modifier.fillMaxSize()) {
            val calculatedHeight = statusBars.getTop(density).times(1f)
            val gradient = Brush.verticalGradient(
                colors = listOf(
                    androidx.compose.ui.graphics.Color.Black,
                    androidx.compose.ui.graphics.Color.Black,
                    androidx.compose.ui.graphics.Color.Black
                ),
                startY = 0f,
                endY = calculatedHeight
            )
            drawRect(
                brush = gradient,
                size = Size(size.width, calculatedHeight),
            )
        }
    }


    @Composable
    fun SetUpNavGraph() {
        val navHostController = rememberNavController()
        NavGraph(navHostController)
    }
}
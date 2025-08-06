package com.example.mvi_architecture.main

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalDensity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
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
            }
        }
        setNavigationBarColor()
    }

    @Composable
    private fun StatusBarProtection() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM) {
            val statusBars = WindowInsets.statusBars
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
        } else {
            window.statusBarColor = Color.BLACK

        }

    }

    private fun setNavigationBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM) {
            ViewCompat.setOnApplyWindowInsetsListener(window.decorView) { view, windowInset ->
                val insets = windowInset.getInsets(WindowInsetsCompat.Type.navigationBars())
                val height = insets.bottom
                val frameLayout = FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    height
                )
                frameLayout.gravity = Gravity.BOTTOM
                val navigationBarView = View(this.baseContext).apply {
                    layoutParams = frameLayout

                    setBackgroundColor(Color.BLACK)
                }
                addContentView(navigationBarView, navigationBarView.layoutParams)
                windowInset

            }
        } else {
            window.statusBarColor = Color.BLACK
        }
    }


    @Composable
    fun SetUpNavGraph() {
        val navHostController = rememberNavController()
        NavGraph(navHostController)
    }
}
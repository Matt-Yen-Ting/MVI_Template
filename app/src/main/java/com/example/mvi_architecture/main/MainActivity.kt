package com.example.mvi_architecture.main

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavGraph
import androidx.navigation.compose.rememberNavController
import com.example.mvi_architecture.NavGraph
import com.example.mvi_architecture.R
import com.example.mvi_architecture.data.navigation.Screen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
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
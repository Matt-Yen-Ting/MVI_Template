package com.example.mvi_architecture.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mvi_architecture.databinding.SplashFragmentLayoutBinding
import com.example.mvi_architecture.extension.launchAndRepeatWithViewLifecycle
import com.example.mvi_architecture.main.state.MainPageState
import com.example.mvi_architecture.main.state.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class SplashFragment : Fragment() {

    private var _binding: SplashFragmentLayoutBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SplashFragmentLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        viewModel.sendIntent(SplashIntent.GetToken)
        observeData()
    }

    private fun initView() {

    }

    private fun observeData() {
        launchAndRepeatWithViewLifecycle {
            viewModel.mainPageUiState.collectLatest { uiState ->
                when(uiState) {
                    is MainPageState.LoginPage -> {
                        findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
                    }

                    is MainPageState.HomePage -> {
                        findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())

                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
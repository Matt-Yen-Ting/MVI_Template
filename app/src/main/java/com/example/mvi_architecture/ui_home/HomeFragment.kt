package com.example.mvi_architecture.ui_home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.mvi_architecture.databinding.HomeFragmentLayoutBinding
import com.example.mvi_architecture.extension.launchAndRepeatWithViewLifecycle
import dagger.hilt.android.AndroidEntryPoint
import com.example.mvi_architecture.R
import com.example.mvi_architecture.data.ResponseStatus
import com.example.mvi_architecture.ui_home.state.LogoutUiState
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: HomeFragmentLayoutBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeFragmentLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setupBackPress()
        clickHandle()
        observeData()
    }

    private fun observeData() {
        launchAndRepeatWithViewLifecycle {
            viewModel.logoutUiState.collectLatest { uiState ->
                binding.progressLoading.isVisible = uiState.showLoading
                when (uiState.logoutStatus) {
                    ResponseStatus.SUCCESS -> {
                        Snackbar.make(binding.root, "登出成功!", Snackbar.LENGTH_SHORT).show()
                        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToLoginFragment())
                    }

                    ResponseStatus.FAIL -> {
                        Toast.makeText(requireActivity(), "登出失敗!!", Toast.LENGTH_SHORT).show()

                    }

                    ResponseStatus.INIT -> {

                    }
                }
            }
        }
    }

    private fun initView() {

    }

    private fun setupBackPress() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            showWhetherLogoutDialog()
        }
    }

    private fun clickHandle() {
        binding.toolbar.setNavigationOnClickListener {
            showWhetherLogoutDialog()
        }

        binding.btnCheck.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAnnouncementFragment())
        }
    }

    private fun showWhetherLogoutDialog() {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setMessage("是否要登出?")
        builder.setPositiveButton("確定") { dialog, which ->
            viewModel.handleIntent(HomeIntent.Logout())
        }
        builder.setNeutralButton("取消") { dialog, which ->

        }
        builder.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
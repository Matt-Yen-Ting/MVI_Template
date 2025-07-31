package com.example.mvi_architecture.ui_login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.example.mvi_architecture.databinding.LoginFragmentLayoutBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mvi_architecture.extension.launchAndRepeatWithViewLifecycle
import kotlinx.coroutines.flow.collectLatest
import com.example.mvi_architecture.R
import com.example.mvi_architecture.data.ResponseStatus
import com.example.mvi_architecture.ui_login.state.LoginUiState
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.delay


@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var _binding: LoginFragmentLayoutBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LoginFragmentLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setupBackPress()
        clickListener()
        observeData()


    }

    private fun setupBackPress() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {

        }
    }

    private fun observeData() {
        launchAndRepeatWithViewLifecycle {
            viewModel.loginUiState.collectLatest { uiState ->
                binding.progressLoading.isVisible = uiState.showLoading
                when (uiState.loginStatus) {
                    ResponseStatus.SUCCESS -> {
                        Snackbar.make(binding.root, "登入成功！", Snackbar.LENGTH_SHORT).show()
                        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
                    }

                    ResponseStatus.FAIL -> {
                        Toast.makeText(requireActivity(), "登入失敗!!", Toast.LENGTH_SHORT).show()

                    }

                    ResponseStatus.INIT -> {

                    }
                }

            }
        }
    }

    private fun initView() {
        binding.etAccount.doAfterTextChanged { editable ->
            val currentInput = editable.toString()
            binding.btnLogin.isEnabled = currentInput.isNotEmpty()
        }
    }

    private fun clickListener() {
        binding.btnLogin.setOnClickListener {
            if (binding.etAccount.text.isNotEmpty()) {
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
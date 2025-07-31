package com.example.mvi_architecture.ui_announcement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvi_architecture.R
import com.example.mvi_architecture.data.ResponseStatus
import com.example.mvi_architecture.databinding.AnnouncementFragmentLayoutBinding
import com.example.mvi_architecture.extension.launchAndRepeatWithViewLifecycle
import com.example.mvi_architecture.ui_announcement.state.DataListUiState
import com.example.mvi_architecture.ui_home.HomeIntent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class AnnouncementFragment : Fragment() {

    private var _binding: AnnouncementFragmentLayoutBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AnnouncementViewModel by viewModels()

    private lateinit var announcementAdapter: AnnouncementAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AnnouncementFragmentLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        clickHandle()
        observeData()
        viewModel.sendIntent(AnnouncementIntent.GetDataList())

    }

    private fun observeData() {
        launchAndRepeatWithViewLifecycle {
            viewModel.dataListUiState.collectLatest { uiState ->
                binding.progressLoading.isVisible = uiState.showLoading
                when (uiState.getDataStatus) {
                    ResponseStatus.SUCCESS -> {
                        announcementAdapter.submitList(uiState.dataList)

                    }

                    ResponseStatus.FAIL -> {
                        showApiErrorDialog()

                    }

                    ResponseStatus.INIT -> {}
                }
            }
        }
    }

    private fun clickHandle() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initView() {
        announcementAdapter = AnnouncementAdapter { title ->
            findNavController().navigate(
                AnnouncementFragmentDirections.actionAnnouncementFragmentToAnnouncementDetailFragment(
                    title
                )
            )
        }
        binding.rvDataList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = announcementAdapter
        }
    }

    private fun showApiErrorDialog() {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setMessage("Api錯誤")
        builder.setPositiveButton("確定") { dialog, which ->
        }
        builder.show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
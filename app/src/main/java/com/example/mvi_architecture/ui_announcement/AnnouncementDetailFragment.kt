package com.example.mvi_architecture.ui_announcement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mvi_architecture.R
import com.example.mvi_architecture.databinding.AnnouncementDetailFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnnouncementDetailFragment : Fragment() {

    private var _binding: AnnouncementDetailFragmentBinding? = null
    private val binding get() = _binding!!

    private var passedTitle: String = ""

    val args: AnnouncementDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        passedTitle = args.title

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AnnouncementDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.tvTitleContent.text = passedTitle
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
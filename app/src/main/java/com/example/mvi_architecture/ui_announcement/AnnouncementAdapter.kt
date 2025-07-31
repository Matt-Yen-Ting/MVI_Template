package com.example.mvi_architecture.ui_announcement

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mvi_architecture.data.AnnouncementListData
import com.example.mvi_architecture.databinding.AnnouncementItemLayoutBinding

class AnnouncementAdapter(private val itemClick: (title: String) -> Unit) :
    ListAdapter<AnnouncementListData, RecyclerView.ViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return AnnouncementAdapterViewHolder(
            AnnouncementItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        (holder as AnnouncementAdapterViewHolder).bind(getItem(position))
    }

    private inner class AnnouncementAdapterViewHolder(private val binding: AnnouncementItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: AnnouncementListData) {
            binding.tvDate.text = data.posted
            binding.tvTitle.text = data.title
            binding.root.setOnClickListener {
                itemClick(data.title)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<AnnouncementListData>() {
            override fun areItemsTheSame(
                oldItem: AnnouncementListData,
                newItem: AnnouncementListData
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: AnnouncementListData,
                newItem: AnnouncementListData
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}
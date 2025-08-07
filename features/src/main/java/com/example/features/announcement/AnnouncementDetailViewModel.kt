package com.example.features.announcement

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AnnouncementDetailViewModel @Inject constructor() : ViewModel() {

    fun sendIntent(announcementDetailIntent: AnnouncementDetailIntent) {

    }
}
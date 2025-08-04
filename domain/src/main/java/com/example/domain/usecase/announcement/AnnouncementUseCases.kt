package com.example.domain.usecase.announcement

import javax.inject.Inject

data class AnnouncementUseCases @Inject constructor (
    val getAnnouncementList: GetAnnouncementList
)
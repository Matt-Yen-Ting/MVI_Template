package com.example.mvi_architecture

import android.app.Application
import com.example.mvi_architecture.base.SignInViewModelDelegate
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App: Application(

) {

    @Inject
    lateinit var signInViewModelDelegate: SignInViewModelDelegate
}
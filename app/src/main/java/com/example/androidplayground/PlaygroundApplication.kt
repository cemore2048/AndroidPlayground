package com.example.androidplayground

import android.app.Application
import com.example.androidplayground.dagger.DaggerApplicationComponent

class PlaygroundApplication : Application() {

    private val _applicationComponent = DaggerApplicationComponent.builder().application(this).build()
    val applicationComponent = _applicationComponent

}
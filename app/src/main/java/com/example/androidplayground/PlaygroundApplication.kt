package com.example.androidplayground

import android.app.Application
import com.example.androidplayground.dagger.ApplicationComponent
import com.example.androidplayground.dagger.DaggerApplicationComponent
import javax.inject.Singleton

class PlaygroundApplication : Application() {

    companion object {
        private lateinit var applicationComponent: ApplicationComponent

        fun getApplication(): ApplicationComponent {
            return applicationComponent
        }
    }


    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger(): ApplicationComponent {

        applicationComponent = DaggerApplicationComponent.builder().appModule(this).build()
        return applicationComponent
    }
}
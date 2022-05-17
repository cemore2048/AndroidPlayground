package com.example.androidplayground.dagger

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Component
abstract class ApplicationComponent {

    @Component.Builder()
    interface Builder {

        @BindsInstance
        fun appModule(application: Application): Builder
        fun build(): ApplicationComponent
    }

}

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    @Singleton
    fun getApplication() = application
}
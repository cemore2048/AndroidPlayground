package com.example.androidplayground.dagger

import android.app.Application
import com.example.androidplayground.MainActivity
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(activity: MainActivity)

    @Component.Builder()
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun appModule(applicationModule: ApplicationModule): Builder
        fun build(): ApplicationComponent
    }
}

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    @Singleton
    fun getApplication() = application
}
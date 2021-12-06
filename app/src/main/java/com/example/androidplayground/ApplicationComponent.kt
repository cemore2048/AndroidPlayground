package com.example.androidplayground

import dagger.Component
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)

}

private val BASE_URL = "https://eval-backend.cemore2048.repl.co"

@Module
class ApplicationModule {

    @Provides
    fun provideRetrofit(clientBuilder: OkHttpClient.Builder): Retrofit {
        return  Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(clientBuilder.build())
            .build()
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor())
    }

    @Provides
    fun executionEndpoint(retrofit: Retrofit): ExecutionEndpoint {
        return retrofit.create(ExecutionEndpoint::class.java)
    }
    class HttpLoggingInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain
                .request()
                .newBuilder()
                .header("Content-Type", "application/json")
                .build()
            return chain.proceed(request)
        }
    }
}
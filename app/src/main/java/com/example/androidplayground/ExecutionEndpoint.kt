package com.example.androidplayground

import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.PUT
import retrofit2.http.Query

interface ExecutionEndpoint {
    @PUT("/exec")
    fun execute(
        @Body request: CommandRequest
    ): Observable<ExecutionResult>

    companion object Factory {
        private const val BASE_URL = "https://eval-backend.cemore2048.repl.co"
        fun createEndpoint(): ExecutionEndpoint {
            val clientBuilder = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor())

            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(clientBuilder.build())
                .build()

            return retrofit.create(ExecutionEndpoint::class.java)
        }
    }
}

data class ExecutionResult(val result: String)
data class CommandRequest(val language: String, val command: String)

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



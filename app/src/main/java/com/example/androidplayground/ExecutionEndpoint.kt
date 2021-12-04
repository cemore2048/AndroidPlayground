package com.example.androidplayground

import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.http.Headers
import retrofit2.http.PUT
import retrofit2.http.Query

interface ExecutionEndpoint {
    @Headers("{Accept: application/json}")
    @PUT("/exec")
    fun execute(
        @Query("language") language: String,
        @Query("command") command: String
    ): Observable<ExecutionResult>
}

data class ExecutionResult(val result: String)

val client = OkHttpClient().apply {
    networkInterceptors()
        .add(Interceptor { chain ->
            val builder = chain.request().newBuilder()
            builder.header("Content-Type", "application/json")
            chain.proceed(builder.build())
        })
}

const val BASE_URL = "https://eval-backend.cemore2048.repl.co"
private val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(client)
    .build()


val endpoint = retrofit.create(ExecutionEndpoint::class.java)

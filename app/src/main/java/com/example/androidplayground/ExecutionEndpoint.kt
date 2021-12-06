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
}

data class ExecutionResult(val result: String)
data class CommandRequest(val language: String, val command: String)





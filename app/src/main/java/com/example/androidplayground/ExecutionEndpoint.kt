package com.example.androidplayground

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

interface ExecutionEndpoint {

}

private val retrofit: Retrofit = Retrofit.Builder()
   .baseUrl("")
   .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
   .addConverterFactory(GsonConverterFactory.create())
   .build()

val endpoint = retrofit.create(ExecutionEndpoint::class.java)

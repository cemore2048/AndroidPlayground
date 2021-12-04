package com.example.androidplayground

interface ExecutionEndpoint {

   @POST
}

private val retrofit: Retrofit = Retrofit.Builder()
   .baseUrl("")
   .build()

val endpoint = retrofit.create(ExecutionEndpoint::class)
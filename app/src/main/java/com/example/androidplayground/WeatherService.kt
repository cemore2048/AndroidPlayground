package com.example.androidplayground

import com.squareup.moshi.Json
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

interface WeatherService {
    // To fill out
    //@GET("")
    //fun getForecast(): Observable<Call>

}

val service = Retrofit.Builder()
    .baseUrl("")
    .addConverterFactory(MoshiConverterFactory.create())
    .build()
package com.example.gymapp.Retrofit

import com.example.gymapp.Constants.ConstantApp
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val appService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(ConstantApp.URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(ApiService::class.java)
    }
}
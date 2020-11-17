package com.example.gymapp.Retrofit

import com.example.gymapp.Model.PlanResponse
import com.example.gymapp.Model.User
import retrofit2.http.GET

interface ApiService {
    @GET("1R0dS5JziHrCqOVAq7M8w1Y0fayQa4ve6H-3Ns1A0COk/od6/public/values?alt=json")
    suspend fun getPlan(): PlanResponse


}
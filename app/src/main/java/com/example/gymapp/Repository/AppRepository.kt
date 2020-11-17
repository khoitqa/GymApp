package com.example.gymapp.Repository

import com.example.gymapp.Model.PlanResponse
import com.example.gymapp.Retrofit.ApiService
import com.example.gymapp.Retrofit.RetrofitInstance

class AppRepository {
    private var service: ApiService = RetrofitInstance.appService
    suspend fun getPlan(): PlanResponse = service.getPlan()


}
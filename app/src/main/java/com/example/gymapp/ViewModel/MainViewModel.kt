package com.example.gymapp.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gymapp.Model.Plan
import com.example.gymapp.Model.PlanResponse
import com.example.gymapp.Repository.AppRepository
import com.example.gymapp.Model.User
import kotlinx.coroutines.delay
import java.io.IOException

class MainViewModel(private val eventManager: UIEventManager) : ViewModel() {
    private val repository = AppRepository()
    fun getPlan() = androidx.lifecycle.liveData<List<Plan>> {
        try {
            eventManager.showProgressBar()
            delay(1000)
            val planResponse = repository.getPlan()
            val planData = mapList(planResponse)
            eventManager.hideProgressBar()
            emit(planData)
        } catch (e: IOException) {
            eventManager.showToast("IOException")
            eventManager.hideProgressBar()
        } catch (e: Exception) {
            eventManager.showToast("Exception")
            eventManager.hideProgressBar()
        }


    }

    fun mapList(inputs: PlanResponse): List<Plan> {
        val outputs = ArrayList<Plan>()
        val listPlan = inputs?.feed?.entry
        listPlan?.forEach { input: com.example.gymapp.Model.PlanResponse.Feed.Entry ->
            outputs.add(map(input))
        }
        return outputs
    }

    private fun map(input: PlanResponse.Feed.Entry): Plan {
        return Plan(
            input.gsxid?.t.toString(),
            input.gsxname?.t.toString(),
            input.gsximg?.t.toString(),
            input.gsxtime?.t.toString() + " min",
            input.gsxlevel?.t.toString()
        )
    }


}
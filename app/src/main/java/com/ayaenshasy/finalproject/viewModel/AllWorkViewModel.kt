package com.ayaenshasy.finalproject.viewModel

import android.content.Context
import android.preference.PreferenceManager
import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
 import com.ayaenshasy.finalproject.model.Data
import com.ayaenshasy.finalproject.service.ApiService
import com.example.example.ExampleJson2KtKotlin
import kotlinx.coroutines.launch

class AllWorkViewModel  : ViewModel(){

    var liveDataAllWork : List<Data> by  mutableStateOf(listOf())
    var liveDataPendingWork : List<Data> by  mutableStateOf(listOf())
    var liveDataCompleteWork : List<Data> by  mutableStateOf(listOf())
    var liveDataUnCompleteWork : List<Data> by  mutableStateOf(listOf())

    fun getAllWork(){
        viewModelScope.launch {
            val apiService=ApiService.geInstance()


            try {
                val listAllWork=apiService.getAllWorkResponse()
                liveDataAllWork = listAllWork.data
                Log.d("AllWork", "getAllWork: $liveDataAllWork")
            } catch (e: Exception) {
                Log.e("TAG2", e.message.toString())

             }


        }

    }

    fun getUnCompleteOrders(context:Context){
        viewModelScope.launch {
            val apiService=ApiService.geInstance()
            try {
                val listAllWork=apiService.getUnCompleteOrders(getAuthToken(context)!!)
                liveDataUnCompleteWork = listAllWork.data
                Log.d("AllWork", "getAllWork: $liveDataUnCompleteWork")
            } catch (e: Exception) {
                Log.e("TAG2", e.message.toString())

             }


        }

    }

    fun getPendingOrders(context:Context){
        viewModelScope.launch {
            val apiService=ApiService.geInstance()
            try {
                val listAllWork=apiService.getPendingOrders(getAuthToken(context)!!)
                liveDataPendingWork = listAllWork.data
                Log.d("AllWork", "getAllWork: $liveDataPendingWork")
            } catch (e: Exception) {
                Log.e("TAG", e.message.toString())
                Log.e("TAG", e.suppressedExceptions.toString())

                Log.e("TAG", liveDataPendingWork.toString())
            }


        }

    }

    fun getCompleteOrders(context:Context){
        viewModelScope.launch {
            val apiService=ApiService.geInstance()
            try {
                val listAllWork=apiService.getCompleteOrders(getAuthToken(context)!!)
                liveDataCompleteWork = listAllWork.data
                Log.d("AllWork", "getAllWork: $liveDataCompleteWork")
            } catch (e: Exception) {
                Log.e("TAG", e.message.toString())
            }
        }

    }

    fun getAuthToken(context: Context): String? {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)
        return sharedPrefs.getString("auth_token", null)
    }
}

package com.ayaenshasy.finalproject.viewModel

import android.content.Context
import android.preference.PreferenceManager
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
 import com.ayaenshasy.finalproject.model.AuthToken
import com.ayaenshasy.finalproject.model.UserCredentials
import com.ayaenshasy.finalproject.model.UserRegister
import com.ayaenshasy.finalproject.service.ApiService
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {


    fun login(email: String, password: String,navController:NavController,context: Context) {
        viewModelScope.launch {
            val apiService=ApiService.geInstance()
            val response =apiService.login(UserCredentials(email, password))

            if (response.success==true) {
                     saveToken(response.data.token.toString(), context)
                    navController.navigate("main_screen")
                    Toast.makeText(context, response.message.toString(), Toast.LENGTH_LONG).show()
                    Log.e("_authToken", response.data.token.toString())
                    Log.e("email", response.data.name.toString())
                    Log.e("email", response.data.email.toString())
                saveUserData(response.data.name.toString(),response.data.email.toString(),context)
             } else {
                 Toast.makeText(context,response.message.toString(),Toast.LENGTH_LONG).show();
                 Log.e("message",response.message.toString())
            }
        }
    }

    fun register(
        name: String,
        email: String,
        phone: String, password: String,
        navController:NavController,
        context: Context) {
        viewModelScope.launch {
            val apiService=ApiService.geInstance()
            val response =apiService.register(UserRegister(name,email,phone, password))

            if (response.success == true) {
                saveToken(response.data.token.toString(), context)
                navController.navigate("main_screen")
                Toast.makeText(context, response.message.toString(), Toast.LENGTH_LONG).show()
                Log.e("_authToken", response.data.token.toString())
                saveUserData(response.data.name.toString(),response.data.email.toString(),context)

            } else {
                Toast.makeText(context,response.message.toString(),Toast.LENGTH_LONG).show();
                Log.e("message",response.message.toString())
            }
        }
    }
}

fun saveToken(token: String, context: Context) {
    val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)
    val editor = sharedPrefs.edit()
    editor.putString("auth_token", token)
    editor.apply()
}

fun saveUserData(name: String,email: String, context: Context) {
    val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)
    val editor = sharedPrefs.edit()
    editor.putString("name", name)
    editor.putString("email", email)
    editor.apply()
}
package com.example.mealz.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mealz.Entity.LoginRequest
import com.example.mealz.Entity.utilisateur
import com.example.mealz.Retrofit.EndPointRest
import com.google.gson.Gson
import kotlinx.coroutines.*

class LoginModel : ViewModel(){
    var user = MutableLiveData<utilisateur?>()
    val loading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()

    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        CoroutineScope(Dispatchers.Main).launch   {
            loading.value = false
            errorMessage.value = "Une erreur s'est produite  ${throwable.message}"
            Log.d("MY EXCEPTION", "Une erreur s'est produite  ${throwable.message}")
        }
    }
    fun logUser(log:LoginRequest) {
        user.value = null
        if(user.value==null) {
            loading.value = true
            CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
                val response = EndPointRest.createEndpoint().login(log)
                Log.d("My response", response.toString())
                withContext(Dispatchers.Main) {
                    loading.value = false
                    if (response.isSuccessful && response.body() != null) {
                        user.value = response.body()

                    } else {
                        errorMessage.value = "Une erreur s'est produite"
                    }
                }
            }
        }
    }
}
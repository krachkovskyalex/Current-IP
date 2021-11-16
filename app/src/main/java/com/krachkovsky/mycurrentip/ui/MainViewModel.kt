package com.krachkovsky.mycurrentip.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krachkovsky.mycurrentip.api.IPApiRequest
import com.krachkovsky.mycurrentip.api.Source
import com.krachkovsky.mycurrentip.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class MainViewModel : ViewModel() {

    val resultData = MutableLiveData<Source>()
    val visibleView = MutableLiveData<Boolean>()
    val trouble = MutableLiveData<Boolean>()


    fun getIP() {
        visibleView.postValue(false)
        val api = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IPApiRequest::class.java)

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = api.getIP().awaitResponse()
                if (response.isSuccessful) {
                    resultData.postValue(response.body())

                    withContext(Dispatchers.Main) {
                        visibleView.postValue(true)
                    }
                }
                trouble.postValue(false)
            } catch (e: Exception) {
                    trouble.postValue(true)
            }
        }
    }
}
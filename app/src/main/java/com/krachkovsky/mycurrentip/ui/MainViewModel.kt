package com.krachkovsky.mycurrentip.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krachkovsky.mycurrentip.api.ApiService
import com.krachkovsky.mycurrentip.api.Source
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

class MainViewModel(private val apiService: ApiService) : ViewModel() {

    val resultData = MutableLiveData<Source>()
    val visibleView = MutableLiveData<Boolean>()
    val trouble = MutableLiveData<Boolean>()

    fun getIP() {
        visibleView.postValue(false)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiService.createApiRequest().getIP().awaitResponse()
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
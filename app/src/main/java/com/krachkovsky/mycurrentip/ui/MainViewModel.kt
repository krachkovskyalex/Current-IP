//package com.krachkovsky.mycurrentip.ui
//
//import android.content.Context
//import android.util.Log
//import android.view.View
//import android.widget.Toast
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import com.krachkovsky.mycurrentip.api.IPApiRequerest
//import com.krachkovsky.mycurrentip.api.Source
//import com.krachkovsky.mycurrentip.util.Constants
//import kotlinx.android.synthetic.main.activity_main.*
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.GlobalScope
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//import retrofit2.Retrofit
//import retrofit2.awaitResponse
//import retrofit2.converter.gson.GsonConverterFactory
//import java.lang.Exception
//
//class MainViewModel : ViewModel() {
//
//    val resultData = MutableLiveData<Source>()
//
//    init {
//        Log.e("AAA", "VM created")
//    }
//
//    override fun onCleared() {
//        Log.e("AAA", "VM cleared")
//        super.onCleared()
//    }
//
//    fun getLog() {
//        Log.e("AAA", "done")
//    }
//
//    fun getIP() : String {
//        val api = Retrofit.Builder()
//            .baseUrl(Constants.BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(IPApiRequerest::class.java)
//
//        GlobalScope.launch(Dispatchers.IO) {
//            try {
//                val response = api.getIP().awaitResponse()
//                if (response.isSuccessful) {
//                    resultData.postValue(response.body())
//                    Log.d("AAA", resultData.ip)
//
//                    withContext(Dispatchers.Main) {
//                        resultData = data
//                    }
//                }
//            } catch (e: Exception) {
//                withContext(Dispatchers.Main) {
//                    return@withContext "Something went wrong..."
//                }
//            }
//        }
//    }
//
//}
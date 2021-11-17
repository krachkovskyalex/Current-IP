package com.krachkovsky.mycurrentip.api

import com.krachkovsky.mycurrentip.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {

    fun createApiRequest(): IPApiRequest {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IPApiRequest::class.java)
    }

}
package com.krachkovsky.mycurrentip.api

import retrofit2.Call
import retrofit2.http.GET

interface IPApiRequest {

    @GET("awstest-service/")
    fun getIP(): Call<Source>

}
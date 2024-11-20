package com.json.lib_ping.data.api

import com.json.lib_ping.data.model.HostModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {

    @GET(ApiUtil.ENDPOINT_HOST)
    suspend fun getHosts(): List<HostModel>

}

object RetrofitClient {
    val apiService = Retrofit.Builder()
        .baseUrl(ApiUtil.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)
}
package com.json.lib_ping.data.repository

import com.json.lib_ping.data.api.ApiService
import com.json.lib_ping.data.api.ApiUtil
import com.json.lib_ping.data.api.RetrofitClient
import com.json.lib_ping.data.common.PingUti
import com.json.lib_ping.data.model.HostModel

class HostRepository {
    private val apiService: ApiService
        get() = RetrofitClient.apiService

    suspend fun fetchHosts(): List<HostModel> = apiService.getHosts()

    suspend fun calculateLatency(host: String): Long {
        return PingUti.pingHost(host)
    }
}
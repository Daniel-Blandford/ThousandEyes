package com.json.lib_ping.data.common

import okhttp3.Dispatcher

object PingUti {
    //check for async integration
    fun pingHost(
        host: String,
        count: Int = 5
    ): Long {
        var totalLatency = 0L
        var successfulPings = 0
        for (i in 1..count) {
            val start = System.currentTimeMillis()
            val process = Runtime.getRuntime().exec("/system/bin/ping -c 1 $host")
            val exitCode = process.waitFor()
            if (exitCode == 0) {
                successfulPings++
                totalLatency += (System.currentTimeMillis() - start)
            }
        }
        return if (successfulPings > 0) totalLatency / successfulPings else -1
    }

//    suspend fun getPingLatency(host: String): Long = withContext(Dispatcher.IO) {
//        PingUti.pingHost(host)
//    }
}
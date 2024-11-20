package com.json.thousandeyes.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.json.lib_ping.data.model.HostModel
import com.json.lib_ping.data.repository.HostRepository
import kotlinx.coroutines.launch

/**
 * Host view model
 *
 * @property repository
 * @constructor Create empty Host view model
 */
class HostViewModel(
    private val repository: HostRepository
) : ViewModel() {
    private val _hosts = mutableStateOf<List<HostModel>>(emptyList())
    val hosts: State<List<HostModel>> = _hosts

    /**
     * Fetch hosts
     * Starts the service to fetch the host details
     * Launch ->
     * In case of failure we will catch the error
     */
    fun fetchHosts() { //todo handle the loading, success, failure use case
        viewModelScope.launch {
            try {
                _hosts.value = repository.fetchHosts()
            } catch (e: Exception) {
                Log.e("HostViewmodel", "Error fetching hosts: ${e.message}")
            }
        }
    }

    /**
     * Get latency
     *
     * @param host
     * @return
     */
    fun getLatency(host: String): Long {
        var latency = -1L
        viewModelScope.launch {
            latency = repository.calculateLatency(host)
        }
        return latency
    }

}
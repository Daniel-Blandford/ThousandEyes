package com.json.thousandeyes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.json.lib_ping.data.repository.HostRepository
import com.json.thousandeyes.ui.screen.HostListScreen
import com.json.thousandeyes.ui.theme.ThousandEyesTheme
import com.json.thousandeyes.ui.viewmodel.HostViewModel

/**
 * Main activity
 *
 * @constructor Create empty Main activity
 */
class MainActivity : ComponentActivity() {
    private val repository = HostRepository()
    private val viewModel by lazy { HostViewModel(repository) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ThousandEyesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HostListScreen(innerPadding, viewModel)
                    viewModel.fetchHosts()
                }
            }
        }
    }
}
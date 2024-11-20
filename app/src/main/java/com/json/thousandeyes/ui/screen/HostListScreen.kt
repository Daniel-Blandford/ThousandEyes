package com.json.thousandeyes.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.json.lib_ping.data.model.HostModel
import com.json.thousandeyes.ui.viewmodel.HostViewModel
import com.json.thousandeyes.R;

/**
 * Host list screen
 *
 * @param innerPadding
 * @param viewModel
 */
@Composable
fun HostListScreen(
    innerPadding: PaddingValues,
    viewModel: HostViewModel
) {
    val hosts by viewModel.hosts

    LazyColumn(
        modifier = Modifier.padding(innerPadding)
    ) {
        items(hosts) { host ->
            HostItem(host, viewModel)
        }
    }
}

/**
 * Host item
 *
 * @param host
 * @param viewModel
 */
@Composable
fun HostItem(host: HostModel, viewModel: HostViewModel) {
    val latency = remember { mutableStateOf<Long?>(null) }

    LaunchedEffect(host) {
        latency.value = viewModel.getLatency(host.url)
    }

    Row(modifier = Modifier.fillMaxWidth()
        .padding(16.dp, 16.dp)
    ) {
        AsyncImage(
            model = host.icon,
            contentDescription = host.name,
            modifier = Modifier.size(48.dp),
            placeholder = painterResource(R.drawable.ic_downloading_image),
            error = painterResource(R.drawable.ic_image_not_supported)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = host.name, style = MaterialTheme.typography.titleMedium)
            Text(text = latency.value?.let { "$it ms" } ?: "Calculating. . .", style = MaterialTheme.typography.labelMedium, modifier = Modifier.padding(0.dp, 4.dp))
        }
    }
}

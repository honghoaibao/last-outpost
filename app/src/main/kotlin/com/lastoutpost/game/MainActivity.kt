package com.lastoutpost.game

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme(colorScheme = darkColorScheme()) {
                BootScreen()
            }
        }
    }
}

/** Màn hình khởi động tạm của beta-1: hiển thị phiên bản, số file content đóng gói và nút chạy thử mô phỏng. */
@Composable
private fun BootScreen() {
    val assets = LocalContext.current.assets
    val enemyFiles = remember { assets.list("enemies")?.size ?: 0 }
    val missionFiles = remember { assets.list("missions")?.size ?: 0 }
    var simLog by remember { mutableStateOf<List<String>>(emptyList()) }

    Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text(stringResource(R.string.app_name), style = MaterialTheme.typography.headlineMedium)
            Text(stringResource(R.string.build_label, BuildConfig.BUILD_LABEL))
            Text(stringResource(R.string.content_summary, enemyFiles, missionFiles))
            Text(stringResource(R.string.foundation_notice), style = MaterialTheme.typography.bodySmall)
            Button(onClick = { simLog = runHeadlessSmokeTest() }) {
                Text(stringResource(R.string.run_smoke_test))
            }
            simLog.forEach { line -> Text(line, style = MaterialTheme.typography.bodySmall) }
        }
    }
}

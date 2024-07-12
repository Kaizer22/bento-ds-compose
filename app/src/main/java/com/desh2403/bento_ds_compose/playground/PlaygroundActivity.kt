package com.desh2403.bento_ds_compose.playground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.desh2403.bento_ds_compose.uikit.component.input.BentoDSSwitch
import com.desh2403.bento_ds_compose.uikit.component.loading.AreaLoader
import com.desh2403.bento_ds_compose.uikit.component.loading.InlineLoader
import com.desh2403.bento_ds_compose.uikit.theme.BentoDSTheme

class PlaygroundActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BentoDSTheme {
                Scaffold(
                    modifier = Modifier.safeDrawingPadding(),
                ) { paddingValues ->
                    AreaLoader(
                        modifier = Modifier.padding(paddingValues),
                        isLoading = false,
                        text = "Loading..."
                    ) {
                        Column {
                            InlineLoader(
                                text = "Loading..."
                            )
                            var c by remember { mutableStateOf(true) }
                            BentoDSSwitch(checked = c) {
                                c = it
                            }
                        }
                    }
                }
            }
        }
    }
}
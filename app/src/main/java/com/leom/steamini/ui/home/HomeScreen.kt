package com.leom.steamini.ui.home

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val viewModel: HomeViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    val keyboardController = LocalSoftwareKeyboardController.current
    val scrollState = rememberLazyListState()

    HomeLayout(
        modifier = modifier,
        scrollState = scrollState,
        uiState = uiState,
        keyboardController = keyboardController,
        onValueChange = { viewModel.onSteamIdChange(it) },
        onSendToGemini = { viewModel.onSendToGemini() },
    )
}

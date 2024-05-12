package com.leom.steamini

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val viewModel: HomeViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    val scrollState = rememberLazyListState()

    LazyColumn(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        state = scrollState,
    ) {
        item {
            Text(
                modifier = Modifier.padding(bottom = 16.dp),
                text = stringResource(R.string.description),
            )
        }

        item {
            StateTextField(
                modifier = Modifier.padding(bottom = 16.dp),
                value = uiState.steamId,
                title = stringResource(R.string.steam_id),
                keyboardType = KeyboardType.Text,
                onValueChange = { viewModel.onValueChange(it) },
            )
        }

        if (!uiState.showMostPlayed) {
            item {
                Button(
                    modifier = Modifier.padding(bottom = 16.dp),
                    onClick = { viewModel.onSendToGemini() },
                ) {
                    Text(stringResource(R.string.send))
                }
            }
        }

        if (uiState.showMostPlayed) {
            if (uiState.games.isEmpty()) {
                item {
                    CircularProgressIndicator(modifier = Modifier.padding(bottom = 16.dp))
                }
            }
            if (uiState.games.isNotEmpty()) {
                item {
                    Text(
                        modifier = Modifier.padding(bottom = 16.dp),
                        text = "These are your most played games:\n${
                            uiState.games.joinToString(
                                separator = ", ",
                            )
                        }",
                    )
                }
            }

            if (uiState.geminiResponse.isEmpty()) {
                item {
                    Text(
                        text = "Now wait while we search for some recommendations...",
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    CircularProgressIndicator(modifier = Modifier.padding(bottom = 16.dp))
                }
            }

            if (uiState.geminiResponse.isNotEmpty()) {
                item {
                    Text(
                        text = "You should also try these games:\n\n${uiState.geminiResponse}"
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}

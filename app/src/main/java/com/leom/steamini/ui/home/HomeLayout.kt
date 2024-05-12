package com.leom.steamini.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.leom.steamini.R
import com.leom.steamini.ui.components.StateTextField

@Composable
fun HomeLayout(
    modifier: Modifier,
    scrollState: LazyListState,
    uiState: HomeUIState,
    keyboardController: SoftwareKeyboardController?,
    onValueChange: (String) -> Unit,
    onSendToGemini: () -> Unit,
) {
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
                onValueChange = onValueChange,
            )
        }

        if (uiState.showButton) {
            item {
                Button(
                    modifier = Modifier.padding(bottom = 16.dp),
                    onClick = {
                        keyboardController?.hide()
                        onSendToGemini()
                    },
                ) {
                    Text(stringResource(R.string.send))
                }
            }
        }

        if (uiState.showSteamLoading) {
            item {
                CircularProgressIndicator()
            }
        }

        if (uiState.showMostPlayed) {
            item {
                Text(text = "These are your most played games:")
                Surface(
                    modifier =
                    Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.tertiaryContainer)
                        .padding(8.dp),
                ) {
                    Text(
                        modifier = Modifier.background(MaterialTheme.colorScheme.tertiaryContainer),
                        text =
                        uiState.games.joinToString(
                            separator = ", ",
                        ),
                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        if (uiState.showWait) {
            item {
                Column(
                    modifier =
                    Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.tertiaryContainer)
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        modifier =
                        Modifier
                            .background(MaterialTheme.colorScheme.tertiaryContainer)
                            .padding(bottom = 16.dp),
                        text = "Now wait while we search for some recommendations...",
                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                    )

                    CircularProgressIndicator()
                }
            }
        }

        if (uiState.error.isNotEmpty()) {
            item {
                Surface(
                    modifier =
                    Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.tertiaryContainer)
                        .padding(8.dp),
                ) {
                    Text(
                        modifier =
                        Modifier
                            .background(MaterialTheme.colorScheme.tertiaryContainer),
                        text = "An error occurred: ${uiState.error}",
                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        if (uiState.showGeminiResponse) {
            item {
                Text(text = "Here are some recommendations for you:")
                Surface(
                    modifier =
                    Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.tertiaryContainer)
                        .padding(8.dp),
                ) {
                    Text(
                        modifier =
                        Modifier
                            .background(MaterialTheme.colorScheme.tertiaryContainer),
                        text = uiState.geminiResponse,
                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeLayoutPreview() {
    HomeLayout(
        modifier = Modifier,
        scrollState = LazyListState(),
        uiState = HomeUIState(),
        keyboardController = null,
        onValueChange = {},
        onSendToGemini = {},
    )
}

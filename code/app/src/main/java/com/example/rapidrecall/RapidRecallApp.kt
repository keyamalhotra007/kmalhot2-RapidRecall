package com.example.rapidrecall

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.material3.Button
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun RapidRecallApp(session: GameSession) {
    var screen by remember { mutableStateOf("start") }
    var sequenceLength by remember { mutableStateOf(0) }
    when (screen) { //check value of screen and return branch that matches
        "start" -> StartScreen(
            onStartGame = { screen = "setup" },
            onShowLog = { screen = "log" },
            onShowSummary = { screen = "summary" }
        )
        "setup" -> SetupScreen(
            onPlayGame = { length ->
                sequenceLength = length
                screen = "play"
            },
            onBack = { screen = "start" }
        )

        "play" -> PlayScreen(
            length = sequenceLength,
            onFinished = { screen = "start" },
            onPlayAgain = {screen = "setup"},
            session
        )
        "log" -> LogScreen(
            attempts = session.attempts,
            onBack = { screen = "start" }
        )

        "summary" -> SummaryScreen(
            session,
            onBack = { screen = "start"}
        )

    }
}
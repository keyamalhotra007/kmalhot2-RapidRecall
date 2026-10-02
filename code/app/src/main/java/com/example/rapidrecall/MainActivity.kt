package com.example.rapidrecall

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.rapidrecall.ui.theme.RapidrecallTheme
import androidx.compose.ui.Alignment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RapidrecallTheme {
                val session = remember { GameSession() }
                var screen by remember { mutableStateOf("start") }
                var sequenceLength by remember { mutableStateOf(0) }
                when (screen) { //check value of screen and return branch that match
                    "start" -> StartScreen().Content(
                        onStartGame = { screen = "setup" },
                        onShowLog = { screen = "log" },
                        onShowSummary = { screen = "summary" }
                    )

                    "setup" -> SetupScreen().Content(
                        onPlayGame = { length ->
                            sequenceLength = length
                            screen = "play"
                        },
                        onBack = { screen = "start" }
                    )

                    "play" -> PlayScreen().Content(
                        length = sequenceLength,
                        onFinished = { screen = "start" },
                        onPlayAgain = { screen = "setup" },
                        session
                    )

                    "log" -> LogScreen().Content(
                        attempts = session.attempts,
                        onBack = { screen = "start" }
                    )

                    "summary" -> SummaryScreen().Content(
                        session,
                        onBack = { screen = "start" }
                    )

                }
            }
        }
    }
}


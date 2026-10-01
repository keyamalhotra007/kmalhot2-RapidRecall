package com.example.rapidrecall

import android.R.attr.text
import android.opengl.ETC1.isValid
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.clearText
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import kotlinx.coroutines.delay
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun PlayScreen(length: Int, onFinished: () -> Unit, onPlayAgain: () -> Unit, session: GameSession) {

    val sequence by remember { mutableStateOf(DigitSequence(length)) }

    var currentIndex by remember { mutableStateOf(0) }
    var showingSequence by remember { mutableStateOf(true) }
    var gameFinished by remember { mutableStateOf(false) }
    val inputState = rememberTextFieldState()
    var result by remember { mutableStateOf("") }
    var lastAttempt by remember { mutableStateOf<Attempt?>(null) }
    var showDigit by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        for (i in sequence.digits.indices) {
            currentIndex = i
            showDigit = true
            delay(800)
            showDigit = false
            delay(300)
        }
        showingSequence = false
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (showingSequence) {
            if (showDigit) {
                Text(
                    text = sequence.digits[currentIndex].toString(),
                    style = MaterialTheme.typography.displayLarge,
                    textAlign = TextAlign.Center
                )
            }
        } else if (!gameFinished) {
            OutlinedTextField(
                state = inputState,
                textStyle = MaterialTheme.typography.bodyLarge,
                label = { Text("Enter Sequence", style = MaterialTheme.typography.bodyLarge, textAlign = TextAlign.Center) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            AppButton(
                onClick = {
                    val attempt = session.record(sequence, inputState.text.toString())
                    lastAttempt = attempt
                    result = if (attempt.correct) "YOU WON!" else "YOU LOST!"
                    gameFinished = true
                }
            ) {
                Text("Done", style = MaterialTheme.typography.labelLarge, textAlign = TextAlign.Center)
            }
        } else {
            Text(text = result, style = MaterialTheme.typography.headlineLarge, textAlign = TextAlign.Center)
            AppButton(onClick = { onPlayAgain() }) {
                Text("Play Again", style = MaterialTheme.typography.labelLarge, textAlign = TextAlign.Center)
            }
            AppButton(onClick = { onFinished() }) {
                Text("Back to Main Screen", style = MaterialTheme.typography.labelLarge, textAlign = TextAlign.Center)
            }
        }
    }
}


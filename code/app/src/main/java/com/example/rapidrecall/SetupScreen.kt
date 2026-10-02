package com.example.rapidrecall

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

class SetupScreen {
    @Composable
    fun Content(onPlayGame: (Int) -> Unit, onBack: () -> Unit) {
        val inputState = rememberTextFieldState()

        // Read the text from the state for validation
        val inputText = inputState.text.toString()
        val length = inputText.toIntOrNull()
        val isValid = length != null && length in 1..10

        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 3. Use 'state =' instead of 'value' and 'onValueChange'
            OutlinedTextField(
                state = inputState,
                textStyle = MaterialTheme.typography.bodyLarge,
                label = { Text("Sequence length (1-10)", style = MaterialTheme.typography.bodyLarge, textAlign = TextAlign.Center) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), //numeric keypad
                isError = inputText.isNotEmpty() && !isValid
            )

            AppButton(
                onClick = { onPlayGame(length!!) },
                enabled = isValid
            ) { Text("Start Game", style = MaterialTheme.typography.labelLarge, textAlign = TextAlign.Center) }
        }
    }
}

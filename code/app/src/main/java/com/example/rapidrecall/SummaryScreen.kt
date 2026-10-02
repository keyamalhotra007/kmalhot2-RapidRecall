package com.example.rapidrecall

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun SummaryScreen(session: GameSession, onBack: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Total Attempts: ${session.total}", style = MaterialTheme.typography.bodyLarge, textAlign = TextAlign.Center)
        Text("Correct Attempts: ${session.correct}", style = MaterialTheme.typography.bodyLarge, textAlign = TextAlign.Center)
        Text("Accuracy: ${session.accuracy}%", style = MaterialTheme.typography.bodyLarge, textAlign = TextAlign.Center)
        AppButton(onClick = onBack) { Text("Back to Main Menu", style = MaterialTheme.typography.labelLarge, textAlign = TextAlign.Center) }
    }
}
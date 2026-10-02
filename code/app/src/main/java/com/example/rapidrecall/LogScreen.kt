package com.example.rapidrecall

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rapidrecall.ui.theme.RapidrecallTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class LogScreen {
    @Composable
    fun Content(attempts: List<Attempt>, onBack: () -> Unit) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (attempts.isEmpty()) {
                Text(text = "No attempts yet", style = MaterialTheme.typography.bodyLarge, textAlign = TextAlign.Center)
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f).fillMaxWidth().padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(attempts) { attempt ->
                        AttemptItemRow(attempt = attempt)
                    }
                }
            }
            AppButton(onClick = onBack) {
                Text(
                    "Back to Main Menu",
                    style = MaterialTheme.typography.labelLarge,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}


@Composable
fun AttemptItemRow(attempt: Attempt) {
    val formattedTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        .format(Date(attempt.timestamp))

    val background = if (attempt.correct) Color(0xFFC8E6C9) else Color(0xFFFFCDD2)

    Card(
        colors = CardDefaults.cardColors(containerColor = background),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = if (attempt.correct) "Correct" else "Incorrect",
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
            Text(text = "Length: ${attempt.length}", style = MaterialTheme.typography.bodyLarge, textAlign = TextAlign.Center)
            Text(text = "Target: ${attempt.target}", style = MaterialTheme.typography.bodyLarge, textAlign = TextAlign.Center)
            Text(text = "Your input: ${attempt.guess}", style = MaterialTheme.typography.bodyLarge, textAlign = TextAlign.Center)
            Text(text = formattedTime, style = MaterialTheme.typography.bodyLarge, textAlign = TextAlign.Center)
        }
    }
}
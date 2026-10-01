package com.example.rapidrecall

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Text
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/** Shared button wrapper used by the app screens. */
@Composable
fun AppButton(
    onClick: () -> Unit,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = Modifier.fillMaxWidth(0.85f).height(64.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFC2185B),
            contentColor = Color(0xFFFFE4EC),
            disabledContainerColor = Color(0xFFC2185B).copy(alpha = 0.5f),
            disabledContentColor = Color(0xFFFFE4EC).copy(alpha = 0.5f)
        ),
        content = content
    )
}

@Composable
fun StartScreen(
    onStartGame: () -> Unit,
    onShowLog: () -> Unit,
    onShowSummary: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Rapid Recall", style = MaterialTheme.typography.headlineLarge, textAlign = TextAlign.Center)
        AppButton(onClick = onStartGame) { Text("Start", style = MaterialTheme.typography.labelLarge, textAlign = TextAlign.Center) }
        AppButton(onClick = onShowLog) { Text("Log", style = MaterialTheme.typography.labelLarge, textAlign = TextAlign.Center) }
        AppButton(onClick = onShowSummary) { Text("Attempt Summary", style = MaterialTheme.typography.labelLarge, textAlign = TextAlign.Center) }
    }
}
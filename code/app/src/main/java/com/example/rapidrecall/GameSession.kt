package com.example.rapidrecall

import androidx.compose.runtime.mutableStateListOf

class GameSession : ObservableModel<GameSession>(){
    private val _attempts = mutableStateListOf<Attempt>()

    val attempts: List<Attempt> get() = _attempts.toList()

    fun addAttempt(attempt: Attempt) {
        _attempts.add(attempt)
        notifyObservers(this)
    }

    fun record(sequence: DigitSequence, guess: String): Attempt {
        val attempt = Attempt(
            length = sequence.digits.length,
            guess = guess,
            target = sequence.digits,
            correct = sequence.matches(guess),
            timestamp = System.currentTimeMillis()
        )
        addAttempt(attempt)
        return attempt
    }

    val total: Int get() = _attempts.size
    val correct: Int get() = _attempts.count {it.correct}
    val accuracy: Double
        get() = if (total == 0) 0.0 else ((correct * 100.0) / total)

}
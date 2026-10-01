package com.example.rapidrecall

import kotlin.random.Random

class DigitSequence(length: Int) {
    val digits: String = buildString {
        for (i in 0 until length) {
            append(Random.nextInt(0, 10))
        }
    }

    fun matches(guess: String): Boolean {
        return digits == guess
    }
}
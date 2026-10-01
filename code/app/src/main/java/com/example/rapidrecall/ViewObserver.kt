package com.example.rapidrecall

interface ViewObserver<M> {
    fun update(model: M)
}
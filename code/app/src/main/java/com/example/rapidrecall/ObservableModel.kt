package com.example.rapidrecall

// model/ObservableModel.kt
open class ObservableModel<M> {
    private val observers = mutableListOf<ViewObserver<M>>()

    fun addObserver(o: ViewObserver<M>) { observers.add(o) }
    fun removeObserver(o: ViewObserver<M>) { observers.remove(o) }

    protected fun notifyObservers(model: M) {
        observers.forEach { it.update(model) }
    }
}
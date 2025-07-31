package com.example.mvi_architecture.base

interface Reducer<T, I> {
    fun reduce(oldState: T, intent: I): T
}
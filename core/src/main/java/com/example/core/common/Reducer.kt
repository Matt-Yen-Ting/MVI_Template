package com.example.core.common

interface Reducer<T, I> {
    fun reduce(oldState: T, intent: I): T
}
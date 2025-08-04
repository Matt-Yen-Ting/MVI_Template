package com.example.data.common_data

interface Reducer<T, I> {
    fun reduce(oldState: T, intent: I): T
}
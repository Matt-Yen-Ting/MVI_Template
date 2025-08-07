package com.example.data.commondata

interface Reducer<T, I> {
    fun reduce(oldState: T, intent: I): T
}
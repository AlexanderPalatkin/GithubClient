package com.example.githubclient

class CountersModel {
    private val counters = mutableListOf(0, 0, 0)

    private fun getCurrent(index: Int): Int {
        return counters[index]
    }

    fun next(index: Int): Int {
        counters[index]++
        return getCurrent(index)
    }

    fun set(index: Int, value: Int) {
        counters[index] = value
    }

    companion object {
        const val FIRST_ITEM = 0
        const val SECOND_ITEM = 1
        const val THIRD_ITEM = 2
    }
}
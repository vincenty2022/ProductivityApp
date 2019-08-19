package com.example.productivity

import java.util.*

class Task(title: String, description: String, dueDate: Array<Int>) {
    private var title: String
    private var description: String
    private var dueDate: Array<Int>
    private var isOngoing: Boolean = false

    init{
        this.title = title
        this.description = description
        this.dueDate = dueDate
        if (dueDate[0] == -1) this.isOngoing = true
    }

    fun getTitle(): String = title
    fun getDesc(): String = description
    fun getDueArr(): Array<Int> = dueDate
    fun checkOngoing(): Boolean = isOngoing
    fun getCal(): Calendar{
        val c = Calendar.getInstance()
        c.set(dueDate[0], dueDate[1], dueDate[2])
        return c
    }

    // returns days until
    fun until(): Int{
        val current = Calendar.getInstance().timeInMillis
        val due = getCal().timeInMillis
        val difference = due - current

        val sec = 1000
        val min = sec * 60
        val hour = min * 60
        val day = hour * 24

        val daysTill: Long = difference/day

        return daysTill.toInt()
    }
}
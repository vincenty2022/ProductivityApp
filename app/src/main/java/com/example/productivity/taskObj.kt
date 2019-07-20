package com.example.productivity

class Task(title: String, description: String, dueDate: Array<Int>) {
    private var title: String
    private var description: String
    private var dueDate: Array<Int>

    init{
        this.title = title
        this.description = description
        this.dueDate = dueDate
    }

    fun getTitle(): String = title
    fun getDesc(): String = description
    fun getDue(): Array<Int> = dueDate


}
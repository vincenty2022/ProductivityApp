package com.example.productivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.taskview.view.*
import kotlin.math.abs

class mainAdapter(listener: onTaskListener): RecyclerView.Adapter<customViewHolder>() {
    private val taskListener = listener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): customViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellRow = layoutInflater.inflate(R.layout.taskview, parent, false)
        return customViewHolder(cellRow, taskListener)
    }

    override fun getItemCount(): Int {
        return listStor.size
    }

    override fun onBindViewHolder(holder: customViewHolder, position: Int) {
        val tempTask = listStor[position]
        val tempTitle = tempTask.getTitle()
        val tempDue = tempTask.getDueArr()
        val until = tempTask.until()
        val dueText: String

        // ongoing task?
        if(tempTask.checkOngoing()){
            dueText = "Ongoing"
        }
        // due within week, today, or past due
        else if (until < 7){
            dueText = when{
                until == 0 -> "Due: Today"
                until ==1 -> "Due: Tomorrow"
                until < 0 -> "Due: ${abs(until)} Days Ago"
                else -> "Due: $until Days"
            }
        }
        else {
            dueText = "Due: ${dateFormatNumbers(dateFormat, tempDue[0], tempDue[1], tempDue[2])}"
        }

//        val dueText = if(!tempisOngoing) "Due: ${dateFormatNumbers(dateFormat, tempDue[0], tempDue[1], tempDue[2])}"
//            else "Ongoing"

        holder.view.title.setText(tempTitle)
        holder.view.dueView.setText(dueText)
    }
}

class customViewHolder(val view: View, listener: onTaskListener): RecyclerView.ViewHolder(view), View.OnClickListener{
    val taskListener = listener

    init {
        view.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        taskListener.onNoteClick(adapterPosition)
    }
}

interface onTaskListener{
    fun onNoteClick(position: Int)
}

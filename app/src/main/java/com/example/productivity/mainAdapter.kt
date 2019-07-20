package com.example.productivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.taskview.view.*

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
        val tempTitle = listStor[position].getTitle()
        val tempDue = listStor[position].getDue()
        val dueFormat = if(tempDue[0] != -1) "Due: ${dateFormatNumbers(dateForm, tempDue[0], tempDue[1], tempDue[2])}"
            else "Ongoing"

        holder.view.title.setText(tempTitle)
        holder.view.dueView.setText(dueFormat)
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

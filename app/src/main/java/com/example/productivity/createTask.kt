package com.example.productivity

import android.icu.util.Calendar
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.app.DatePickerDialog
import android.content.Intent
import androidx.core.view.isVisible

import kotlinx.android.synthetic.main.activity_create_task.*
import kotlinx.android.synthetic.main.content_create_task.*

class createTask : AppCompatActivity() {

    private var tempDateArr = emptyArray<Int>()
    private val c = Calendar.getInstance()
    private var edit = false
    private var extraValue: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_task)
        setSupportActionBar(createTask_toolbar)

        extraValue = intent.getIntExtra("edit", -1)
        if (extraValue != -1) editConfig(listStor[extraValue])
        else getSupportActionBar()!!.setTitle("Create Task")

        // configure check button
        fab.setOnClickListener {
            val title = Title.text.toString()
            val desc = Description.text.toString()
            // passes error array when ongoing and tempDateArr otherwise
            val due = if(onGoingCheck.isChecked) arrayOf(-1,0,0) else tempDateArr

            // catch empty due date and title
            if (title.isEmpty() && due.isEmpty()) {
                titleLayout.setBackgroundColor(getColor(R.color.warning))
                dateLayout.setBackgroundColor(getColor(R.color.warning))
                val bothWarn = Snackbar.make(it, "Title and due date are required!", Snackbar.LENGTH_LONG)
                bothWarn.show()
            }

            // catch empty title
            else if (title.isEmpty()) {
                titleLayout.setBackgroundColor(getColor(R.color.warning))
                dateLayout.setBackgroundColor(getColor(R.color.bkg))
                val titleWarn = Snackbar.make(it, "Title required!", Snackbar.LENGTH_LONG)
                titleWarn.show()
            }

            // catch empty due date
            else if (due.isEmpty()) {
                dateLayout.setBackgroundColor(getColor(R.color.warning))
                titleLayout.setBackgroundColor(getColor(R.color.bkg))
                val dueWarn = Snackbar.make(it, "Due date required!", Snackbar.LENGTH_LONG)
                dueWarn.show()
            }

            else if (edit) {
                listStor[extraValue] = Task(title, desc, due)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

            else {
                listStor.add(Task(title, desc, due))
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }

        // configure calendar button
        calendarButton.setOnClickListener {
            openCalendar()
        }

        // configures ongong check box
        onGoingCheck.setOnClickListener {
            if (onGoingCheck.isChecked) {
                calendarButton.isVisible = false
                calendarButton.isClickable = false
                Date.setText("Ongoing")
            }
            else {
                calendarButton.isVisible = true
                calendarButton.isClickable = true
                Date.setText("")
            }
        }
    }

    private fun openCalendar(){
        val year: Int
        val month: Int
        val day: Int

        if (tempDateArr.isEmpty()) {
            year = c.get(Calendar.YEAR)
            month = c.get(Calendar.MONTH)
            day = c.get(Calendar.DAY_OF_MONTH)
        }
        else {
            year = tempDateArr[0]
            month = tempDateArr[1]
            day = tempDateArr[2]
        }

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener {_, myear, mmonth, mday ->
            tempDateArr = arrayOf(myear, mmonth, mday)
            Date.setText(dateFormatLetters(dateForm, myear, mmonth, mday))
        }, year, month, day)
        dpd.show()
    }

    private fun editConfig(task: Task) {
        edit = true

        getSupportActionBar()!!.setTitle("Edit Task")
        val title = task.getTitle()
        val description = task.getDesc()
        val date = task.getDue()

        // set default
        Title.setText(title)
        Description.setText(description)
        tempDateArr = date
    }
}

package com.example.productivity

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.android.synthetic.main.home_fragment.view.*
import kotlinx.android.synthetic.main.main_popup.view.*
import java.util.*

// test progressbar fragment
class progressBarFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.progressbar_fragment, container, false)
    }
}

// home_fragment
class homeFragment: Fragment(), onTaskListener {
    var context = getActivity()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.home_fragment, container, false)
        view.main_recView.layoutManager= LinearLayoutManager(context)
        mainRefreshInit(view)
        return view
    }

    // initialize any onClickListeners here
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        context = getActivity()
    }

    override fun onNoteClick(position: Int) {
        dimOverlay.visibility = View.VISIBLE

        val view = layoutInflater.inflate(R.layout.main_popup, null)
        val popup = PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)

        view.x_view.setOnClickListener{
            dimOverlay.visibility = View.GONE
            popup.dismiss()
        }
        val currTask = listStor[position]

        view.title.setText(currTask.getTitle())
        view.descript.setText(currTask.getDesc())

        if (currTask.checkOngoing()){
            view.dueDate.setText("Ongoing")
        }
        else {
            val currCal = currTask.getCal()
            val currYear = currCal.get(Calendar.YEAR)
            val currMonth = currCal.get(Calendar.MONTH)
            val currDay = currCal.get(Calendar.DAY_OF_MONTH)
            view.dueDate.setText(dateFormatLetters(dateFormat, currYear, currMonth, currDay))
        }

        // Delete Button
        view.del_but.setOnClickListener{
            listStor.removeAt(position)
            dimOverlay.visibility = View.GONE
            popup.dismiss()
            mainRefresh()
        }

        // Edit Button
        view.ed_but.setOnClickListener{
            val intent = Intent(context, createTask::class.java)
            intent.putExtra("edit", position)
            startActivity(intent)
        }

        popup.showAtLocation(content_layout, Gravity.CENTER, 0,0)
    }

    // use inside onCreateView
    private fun mainRefreshInit(view:View) {
        view.main_recView.adapter = mainAdapter(this)
        if (listStor.isEmpty()) view.main_emptyInside.visibility = View.VISIBLE
        else view.main_emptyInside.visibility = View.GONE
    }

    // refreshes recyclerview and checks for emptiness (in listStor)
    private fun mainRefresh() {
        main_recView.adapter = mainAdapter(this)
        if (listStor.isEmpty()) main_emptyInside.visibility = View.VISIBLE
        else main_emptyInside.visibility = View.GONE
    }
}
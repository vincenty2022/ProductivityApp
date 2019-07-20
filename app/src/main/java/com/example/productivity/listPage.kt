package com.example.productivity

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.main_popup.view.*
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.android.synthetic.main.activity_list_page.*

class listPage : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_page)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        getSupportActionBar()!!.setTitle("My Tasks")

        // Nav Bar Configuration
        // Nav bar listener
        val listener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeButton -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container, homeFragment()).commit()
                    true
                }
                R.id.settingsButton -> {
                    // settings fragment
                    true
                }
                R.id.blank -> {
                    // figuring it out
                    true
                }
                R.id.newTask -> {
                    val intent = Intent(this, createTask::class.java)
                    startActivity(intent)
                    true
                }
                else -> true
            }
        }
        bottom_nav.setOnNavigationItemSelectedListener(listener)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, homeFragment()).commit()
        }


        // Notifications
        createNotificationChannel(
            this,
            "startEndChannel",
            "Daily Notifications",
            "Start/End of Day Notifications",
            2
        )
        createNotificationChannel(
            this,
            "checkIn",
            "Check-Ins",
            "Reminder Notifications. Frequency can be set in app settings",
            1
        )

        // test notification
        val testNotification = Notification(
            this,
            "startEndChannel",
            R.drawable.test_notification_icon,
            "RAR",
            "Rar",
            1
        )
    }
}

package com.example.productivity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activty_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activty_main)
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
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container, settingsFragment()).commit()
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

    override fun onBackPressed() {
//        super.onBackPressed()
    }
}

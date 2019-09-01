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
                    val intent = Intent(this, SettingsActivity::class.java)
                    startActivity(intent)
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
        bottom_nav.selectedItemId = R.id.homeButton

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, homeFragment()).commit()
        }
    }

    override fun onResume() {
        super.onResume()
        bottom_nav.selectedItemId = R.id.homeButton
    }
}

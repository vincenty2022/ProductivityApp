package com.example.productivity

import android.animation.ValueAnimator
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.animation.AccelerateInterpolator
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.bottomnavigation.BottomNavigationView

import kotlinx.android.synthetic.main.activity_test.*
import kotlinx.android.synthetic.main.progressbar_fragment.*
import kotlin.math.roundToInt
import kotlin.math.abs

class test : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        setSupportActionBar(toolbarTest)

//        val bottomListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
//            when(item.itemId) {
//                R.id.homeButton -> {
//                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container, progressBarFragment()).commit()
//                    true
//                }
//                R.id.settingsButton -> {
//                    Log.i("Results", "Settings")
//                    true
//                }
//                R.id.blank -> {
//                    Log.i("Results", "blank")
//                    true
//                }
//                R.id.newTask -> {
//                    Log.i("Results", "New Task")
//                    true
//                }
//                else -> false
//            }
//        }
//        fab.setOnClickListener {
//            val endValue = numText.text.toString().toFloat()
//            val valueAnimator = ValueAnimator.ofFloat(progressBar.progress.toFloat(), endValue)
//            valueAnimator.duration = 600
//            valueAnimator.interpolator = AccelerateInterpolator(2.0f)
//            valueAnimator.addUpdateListener {
//                var animatedValue = it.animatedValue as Float
//                progressBar.progress = animatedValue.roundToInt()
//            }
//            valueAnimator.start()
//        }
    }
}

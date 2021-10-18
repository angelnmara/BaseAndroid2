package com.lamarrulla.mytiendita.utils

import android.app.ActionBar
import android.app.Activity
import android.app.Application
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lamarrulla.mytiendita.R
import com.lamarrulla.mytiendita.ui.splash.SplashActivity

class Utils(private val context: Context) {
    private val activity = ((context) as Activity)
    public fun fullScreen(window:Window){
        /*if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
        if (Build.VERSION.SDK_INT >= 19) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        if (Build.VERSION.SDK_INT >= 21) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = Color.TRANSPARENT
        }*/
        (context as AppCompatActivity).supportActionBar?.hide()
        var navView: BottomNavigationView? = context?.findViewById(R.id.nav_view)
        navView?.visibility = View.GONE
    }
    fun showBarAndNavView(){
        //var win: Window = activity.window
        //win.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        (activity as AppCompatActivity).supportActionBar?.show()
        var navView: BottomNavigationView? = activity?.findViewById(R.id.nav_view)
        navView?.visibility = View.VISIBLE
    }
    fun showBar(){
        var win: Window = activity.window
        win.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        (activity as AppCompatActivity).supportActionBar?.show()
    }
}
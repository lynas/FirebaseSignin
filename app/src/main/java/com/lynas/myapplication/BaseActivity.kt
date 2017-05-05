package com.lynas.myapplication

import android.support.v7.app.AppCompatActivity

/**
 * Created by lynas on 5/3/2017..
 */

open class BaseActivity : AppCompatActivity() {
    override fun onPause() {
        super.onPause()
        println("On pause")
    }
}
package com.lynas.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import org.jetbrains.anko.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main2)

        relativeLayout {
            button ("Ok"){

            }.lparams {
                alignParentTop()
                alignParentLeft()
                alignParentRight()
            }
        }
    }
}

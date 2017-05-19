package com.lynas.myapplication

import android.content.Intent
import android.os.Bundle
import org.jetbrains.anko.*

class NextPageActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        relativeLayout {
            button("Ok") {
                id = 1
            }.lparams {
                alignParentTop()
                alignParentLeft()
            }.onClick {
                startActivity(Intent(this@NextPageActivity, StudentActivity::class.java))
            }
        }
    }
}

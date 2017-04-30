package com.lynas.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import org.jetbrains.anko.*
import com.google.firebase.database.FirebaseDatabase
import io.realm.Realm
import java.util.*


class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main2)

        relativeLayout {
            button ("Ok"){
                id = 1
            }.lparams {
                alignParentTop()
                alignParentLeft()
                alignParentRight()
            }.onClick {
                val database = FirebaseDatabase.getInstance()
                val myRef = database.getReference("message")

                myRef.setValue("Hello, World!")
            }


            button("Save Person") {

            }.lparams {
                below(1)
            }.onClick {
                Realm.init(context)
                val realm = Realm.getDefaultInstance()
                realm.beginTransaction()
                val person = realm.createObject(Person::class.java, UUID.randomUUID().toString())
                person.name = "Sazzad"
                realm.commitTransaction()
                realm.close()


            }
        }
    }
}

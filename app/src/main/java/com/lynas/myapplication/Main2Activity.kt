package com.lynas.myapplication

import android.content.Intent
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.gson.Gson
import io.realm.Realm
import org.jetbrains.anko.*
import java.util.*


class Main2Activity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val firebaseAuth = FirebaseAuth.getInstance()
        val currentUserId = firebaseAuth.currentUser?.uid ?: "UnAuthenticated"

        relativeLayout {
            button ("Ok"){
                id = 1
            }.lparams {
                alignParentTop()
                alignParentLeft()
                alignParentRight()
            }.onClick {
                val database = FirebaseDatabase.getInstance()
                val myRef = database.getReference(currentUserId)

                //myRef.setValue("Hello, World! again : " + System.currentTimeMillis())

                val person = Person(UUID.randomUUID().toString(), "Name" + System.currentTimeMillis())
                val gson = Gson()
                val toJson: String = gson.toJson(person)
                myRef.setValue(toJson)

            }


            button("Save Person") {
                id = 2
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

            button("Next Page") {

            }.lparams {
                below(2)
            }.onClick {
                toast("hey")
                startActivity(Intent(this@Main2Activity, NextPageActivity::class.java))
            }
        }
    }
}

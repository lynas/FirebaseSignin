package com.lynas.myapplication

import android.support.v7.app.AppCompatActivity
import io.realm.Realm
import java.util.*

/**
 * Created by lynas on 5/3/2017.
 */

open class BaseActivity : AppCompatActivity() {
    override fun onPause() {
        super.onPause()
        println("On pause")

        Realm.init(applicationContext)
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        val person = realm.createObject(Person::class.java, UUID.randomUUID().toString())
        person.name = "Sazzad"
        val listOfPerson = realm.where(Person::class.java).findAll()
        println("#############################################")
        println(listOfPerson.count())

        for (prsn in listOfPerson) {
            println(prsn.id)
        }

        realm.commitTransaction()
        realm.close()
    }
}
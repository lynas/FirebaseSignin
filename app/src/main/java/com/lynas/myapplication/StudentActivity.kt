package com.lynas.myapplication

import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import org.jetbrains.anko.*
import java.util.*
import android.widget.Toast
import com.google.firebase.database.DatabaseError
import android.R.attr.author
import android.widget.ArrayAdapter
import com.google.firebase.database.DataSnapshot
import com.fasterxml.jackson.databind.ObjectMapper





/**
 * Created by lynas on 5/19/2017..
 */

class StudentActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val firebaseAuth = FirebaseAuth.getInstance()
        val currentUserId = firebaseAuth.currentUser?.uid ?: "UnAuthenticated"
        val dbRef = FirebaseDatabase.getInstance()


        relativeLayout {
            val personId = editText {
                id = 1
                hint = "Person Id"
            }.lparams {
                alignParentTop()
                alignParentLeft()
                alignParentRight()
            }

            val personName = editText {
                id = 2
                hint = "Name"
            }.lparams {
                below(1)
            }

            val className = editText {
                id = 3
                hint = "className"
            }.lparams {
                below(2)
            }

            val rollNumber = editText {
                id = 4
                hint = "rollNumber"
            }.lparams {
                below(3)
            }

            button("Save") {
                id = 5
            }.lparams {
                below(4)
            }.onClick {
                val person = Person(UUID.randomUUID().toString(), "Sazzad")
                val student = Student().apply {
                    this.className = "Class 1"
                    this.rollNumber = 1
                    this.personID = person.id

                }


                val myRef = dbRef.getReference(currentUserId)
                doAsync {
                    myRef.child("Student").child(student.studentId).setValue(student)

                }

            }

            val myListView = listView {
                id = 6
            }.lparams {
                below(5)
            }

            val personRef = dbRef.getReference(currentUserId)

            val oMapper = ObjectMapper()

            personRef.child("Student").addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val item: List<Student> = oMapper.convertValue(snapshot.value, HashMap::class.java).values.map {
                        oMapper.convertValue(it, Student::class.java)
                    }
                    val listAdapter = ArrayAdapter<String>(this@StudentActivity, android.R.layout.simple_list_item_1, item.map { it.personID })
                    myListView.adapter = listAdapter
                }
                override fun onCancelled(databaseError: DatabaseError) {}
            })
        }
    }
}



















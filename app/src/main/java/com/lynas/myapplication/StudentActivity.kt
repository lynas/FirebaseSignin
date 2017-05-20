package com.lynas.myapplication

import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import org.jetbrains.anko.*
import java.util.*

/**
 * Created by lynas on 5/19/2017..
 */

class StudentActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val firebaseAuth = FirebaseAuth.getInstance()
        val currentUserId = firebaseAuth.currentUser?.uid ?: "UnAuthenticated"
        val database = FirebaseDatabase.getInstance()


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

            }.lparams {
                below(4)
            }.onClick {
                val person = Person(UUID.randomUUID().toString(), "Sazzad")
                val student = Student(className = "Class 1",
                        rollNumber = 1,
                        personID = person.id)


                val myRef = database.getReference(currentUserId)
                doAsync {
                    myRef.child("Student").child(student.studentId).setValue(student)

                }
            }
        }

    }
}
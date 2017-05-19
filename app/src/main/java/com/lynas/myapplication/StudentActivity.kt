package com.lynas.myapplication

import android.os.Bundle
import com.fasterxml.jackson.databind.ObjectMapper
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
        val mapper = ObjectMapper()


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
                print(personId.text)
                print(personName.text)
                print(className.text)
                print(rollNumber.text)

                var student = Student().apply {
                    this.className = "class1"
                    this.rollNumber = 1
                }


                val database2 = FirebaseDatabase.getInstance()
                val myRef = database2.getReference(currentUserId)
                /*myRef.child("Person")
                        .child(personId.text.toString())
                        .child("ID")
                        .setValue(personId.text.toString())
                myRef.child("Person")
                        .child(personId.text.toString())
                        .child("Name")
                        .setValue(personName.text.toString())*/

                myRef.child("Student")
                        .child(student.studentId)
                        .child("RollNumber")
                        .setValue(student.rollNumber)

                myRef.child("Student")
                        .child(student.studentId)
                        .child("ClassName")
                        .setValue(student.className)

                myRef.child("Student")
                        .child(student.studentId)
                        .child("Other")
                        .setValue(student.toString())


                val users = HashMap<String, Student>()
                users.put("alanisawesome", student)

                val item = HashMap<String, String>()
                item.put("Test", "TEstValue")

                myRef.child("NewTest").setValue(item)

                val maps = mapper.convertValue(student, Map::class.java)
                myRef.child("NewTestMAP").setValue(maps)


            }


        }

    }
}
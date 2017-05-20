package com.lynas.myapplication

import java.util.*

/**
 * Created by lynas on 5/19/2017..
 */

class Student() {
    var studentId: String = UUID.randomUUID().toString()
    var className: String = ""
    var rollNumber: Int = 0
    var personID: String = ""

    constructor(studentId: String, className: String, rollNumber: Int, personID: String) : this() {
        this.studentId = studentId
        this.className = className
        this.rollNumber = rollNumber
        this.personID = personID
    }

    override fun toString(): String {
        return "Student(studentId='$studentId', className='$className', rollNumber=$rollNumber, personID='$personID')"
    }


}
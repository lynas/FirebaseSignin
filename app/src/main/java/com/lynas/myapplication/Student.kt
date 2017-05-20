package com.lynas.myapplication

import java.util.*

/**
 * Created by lynas on 5/19/2017..
 */

data class Student(
        var studentId: String = UUID.randomUUID().toString(),
        var className: String,
        var rollNumber: Int,
        val personID: String
)
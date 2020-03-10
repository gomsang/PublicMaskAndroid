package com.gomsang.lab.publicmask.libs.util

import java.text.ParseException
import java.text.SimpleDateFormat

object TimeUtil {
    fun calcTimeDiff(time: String): String? {
        try {
            val sdf = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
            val date = sdf.parse(time)
            val convertedDate = date.time
            return formatTimeString(convertedDate)
        } catch (e: ParseException) {
            e.printStackTrace()
            return null
        }
        return null
    }

    private object TIME_MAXIMUM {
        const val SEC = 60
        const val MIN = 60
        const val HOUR = 24
        const val DAY = 30
        const val MONTH = 12
    }

    fun formatTimeString(regTime: Long): String? {
        val curTime = System.currentTimeMillis()
        var diffTime = (curTime - regTime) / 1000
        var msg: String? = null
        if (diffTime < TIME_MAXIMUM.SEC) {
            msg = "방금 전"
        } else if (TIME_MAXIMUM.SEC.let { diffTime /= it; diffTime } < TIME_MAXIMUM.MIN) {
            msg = diffTime.toString() + "분 전"
        } else if (TIME_MAXIMUM.MIN.let { diffTime /= it; diffTime } < TIME_MAXIMUM.HOUR) {
            msg = diffTime.toString() + "시간 전"
        } else if (TIME_MAXIMUM.HOUR.let { diffTime /= it; diffTime } < TIME_MAXIMUM.DAY) {
            msg = diffTime.toString() + "일 전"
        } else if (TIME_MAXIMUM.DAY.let { diffTime /= it; diffTime } < TIME_MAXIMUM.MONTH) {
            msg = diffTime.toString() + "달 전"
        } else {
            msg = diffTime.toString() + "년 전"
        }
        return msg
    }
}
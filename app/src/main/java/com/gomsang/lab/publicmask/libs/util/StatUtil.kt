package com.gomsang.lab.publicmask.libs.util

object StatUtil {
    fun convertStatToString(stat: String): String {
        when (stat) {
            "plenty" -> return "100+"
            "some" -> return "30+"
            "few" -> return "1+"
            "empty" -> return "Empty"
            "break" -> return "Break"
        }
        return "ERROR"
    }
    fun convertStatToDetailString(stat : String): String{
        when (stat) {
            "plenty" -> return "100+"
            "some" -> return "30 ~ 99"
            "few" -> return "1 ~ 29"
            "empty" -> return "Empty"
            "break" -> return "Break"
        }
        return "ERROR"
    }

    fun convertTypeToString(type : String): String {
        when(type){
            "01" -> return "Pharmacy"
            "02" -> return "Post office"
            "03" -> return "Nonghyup"
        }
        return "ERROR"
    }
}
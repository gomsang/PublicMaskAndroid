package com.gomsang.lab.publicmask.libs.util

object StatUtil {
    fun convertStatToString(stat: String): String {
        when (stat) {
            "plenty" -> return "100+"
            "some" -> return "30+"
            "few" -> return "1+"
            "empty" -> return "재고소진"
            "break" -> return "일시중지"
        }
        return "에러"
    }
    fun convertStatToDetailString(stat : String): String{
        when (stat) {
            "plenty" -> return "100개 이상"
            "some" -> return "30개 이상 100개 미만"
            "few" -> return "1개 이상 30개 미만"
            "empty" -> return "재고소진"
            "break" -> return "일시 판매 중지"
        }
        return "에러"
    }

    fun convertTypeToString(type : String): String {
        when(type){
            "01" -> return "약국"
            "02" -> return "우체국"
            "03" -> return "농협"
        }
        return "판매처"
    }
}
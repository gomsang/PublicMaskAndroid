package com.gomsang.lab.publicmask.libs.https.api

import com.gomsang.lab.publicmask.libs.constants.WebAddresses
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Kakao {
    val api: KakaoAPI

    constructor() {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(WebAddresses.URL_KAKAO_RESTAPI)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create<KakaoAPI>(KakaoAPI::class.java)
    }
}
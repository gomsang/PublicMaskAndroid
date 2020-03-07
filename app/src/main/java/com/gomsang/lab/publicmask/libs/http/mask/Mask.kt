package com.gomsang.lab.publicmask.libs.http.mask

import com.gomsang.lab.publicmask.libs.constants.WebAddresses
import com.gomsang.lab.publicmask.libs.http.kakao.KakaoAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Mask {
    val api: MaskAPI

    constructor() {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(WebAddresses.URL_MASK_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create<MaskAPI>(MaskAPI::class.java)
    }
}
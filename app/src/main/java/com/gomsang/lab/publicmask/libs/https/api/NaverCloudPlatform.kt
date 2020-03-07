package com.gomsang.lab.publicmask.libs.https.api

import com.gomsang.lab.publicmask.libs.constants.WebAddresses
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NaverCloudPlatform {
    val api: NaverCloudPlatformAPI

    constructor() {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(WebAddresses.URL_NAVEROPENAPI)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create<NaverCloudPlatformAPI>(NaverCloudPlatformAPI::class.java)
    }
}
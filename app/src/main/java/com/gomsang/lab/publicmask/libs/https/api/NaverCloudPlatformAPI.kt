package com.gomsang.lab.publicmask.libs.https.api

import com.gomsang.lab.publicmask.libs.datas.naver.NaverPlaceSearchResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NaverCloudPlatformAPI {
    @GET("map-place/v1/search")
    fun searchPlace(@Header("X-NCP-APIGW-API-KEY-ID") clientId : String,
                    @Header("X-NCP-APIGW-API-KEY") clientSecret : String,
                    @Query("query") keyword: String,
                    @Query("coordinate") coordinate: String): Call<NaverPlaceSearchResult>
}
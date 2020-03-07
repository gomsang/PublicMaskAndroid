package com.gomsang.lab.publicmask.libs.http.kakao

import com.gomsang.lab.publicmask.libs.datas.kakao.PlaceSearchResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface KakaoAPI {
    @GET("v2/local/search/keyword.json")
    fun searchPlace(@Header("Authorization") key : String,
                    @Query("query") keyword: String) : Call<PlaceSearchResult>
}
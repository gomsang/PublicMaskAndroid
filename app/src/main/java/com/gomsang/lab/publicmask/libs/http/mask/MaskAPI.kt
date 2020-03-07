package com.gomsang.lab.publicmask.libs.http.mask

import com.gomsang.lab.publicmask.libs.datas.mask.MaskQueryResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MaskAPI {
    @GET("v1/storesByGeo/json")
    fun searchPlace(@Query("lat") latitude : String, @Query("lng") longitude : String) : Call<MaskQueryResult>
}

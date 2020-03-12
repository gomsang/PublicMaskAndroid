package com.gomsang.lab.publicmask.libs.http.mask

import com.gomsang.lab.publicmask.libs.datas.mask.MaskQueryResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface MaskAPI {
    @GET("/corona19-masks/v2/storesByGeo/json")
    fun searchPlace(@Query("lat") latitude : Double, @Query("lng") longitude : Double, @Query("m") distance : String) : Call<MaskQueryResult>
}

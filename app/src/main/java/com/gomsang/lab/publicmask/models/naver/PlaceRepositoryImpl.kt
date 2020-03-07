package com.gomsang.lab.publicmask.models.naver

import android.util.Log
import com.gomsang.lab.publicmask.libs.constants.Logger
import com.gomsang.lab.publicmask.libs.constants.SensitiveInformation
import com.gomsang.lab.publicmask.libs.datas.naver.NaverPlace
import com.gomsang.lab.publicmask.libs.datas.naver.NaverPlaceSearchResult
import com.gomsang.lab.publicmask.libs.https.api.NaverCloudPlatform
import com.gomsang.lab.publicmask.libs.https.api.NaverCloudPlatformAPI
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Response

class PlaceRepositoryImpl : PlaceRepository {
    private val api: NaverCloudPlatformAPI by lazy {
        NaverCloudPlatform().api
    }

    override fun searchPlaces(keyword: String, coordinate: String): Single<List<NaverPlace>> {
        return Single.create {
            api.searchPlace(
                SensitiveInformation.NAVER_API_CLIENT_ID,
                SensitiveInformation.NAVER_API_CLIENT_SECRET,
                keyword,
                coordinate
            ).enqueue(object : retrofit2.Callback<NaverPlaceSearchResult> {
                override fun onFailure(call: Call<NaverPlaceSearchResult>, t: Throwable) {
                    Logger.log(Logger.LOG_NETWORK_RESULT, t.localizedMessage)
                    it.onError(t)
                }

                override fun onResponse(
                    call: Call<NaverPlaceSearchResult>,
                    response: Response<NaverPlaceSearchResult>
                ) {
                    it.onSuccess(response.body()!!.naverPlaces)
                }
            })
        }
    }

}
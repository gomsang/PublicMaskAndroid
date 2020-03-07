package com.gomsang.lab.publicmask.models.naver

import com.gomsang.lab.publicmask.libs.constants.Logger
import com.gomsang.lab.publicmask.libs.constants.SensitiveInformation
import com.gomsang.lab.publicmask.libs.datas.Place
import com.gomsang.lab.publicmask.libs.datas.kakao.PlaceSearchResult
import com.gomsang.lab.publicmask.libs.https.api.Kakao
import com.gomsang.lab.publicmask.libs.https.api.KakaoAPI
import com.google.gson.Gson
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Response

class PlaceRepositoryImpl : PlaceRepository {
    private val api: KakaoAPI by lazy {
        Kakao().api
    }

    override fun searchPlaces(keyword: String): Single<List<Place>> {
        return Single.create { emitter ->
            api.searchPlace(
                "KakaoAK " + SensitiveInformation.KAKAO_RESTAPI_KEY,
                keyword
            ).enqueue(object : retrofit2.Callback<PlaceSearchResult> {
                override fun onFailure(call: Call<PlaceSearchResult>, t: Throwable) {
                    Logger.log(Logger.LOG_NETWORK_RESULT, t.localizedMessage)
                    emitter.onError(t)
                }

                override fun onResponse(
                    call: Call<PlaceSearchResult>,
                    response: Response<PlaceSearchResult>
                ) {
                    Logger.log(Logger.LOG_NETWORK_RESULT, response.message())

                    Logger.log(Logger.LOG_NETWORK_RESULT, Gson().toJson(response.body()))
                    val result = mutableListOf<Place>()
                    response.body()!!.documents.forEach {
                        result.add(Place.convert(it))
                    }
                    emitter.onSuccess(result)
                }
            })
        }
    }

}
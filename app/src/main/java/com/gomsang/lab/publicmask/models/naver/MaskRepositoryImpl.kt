package com.gomsang.lab.publicmask.models.naver

import com.gomsang.lab.publicmask.libs.constants.Logger
import com.gomsang.lab.publicmask.libs.datas.Stock
import com.gomsang.lab.publicmask.libs.datas.mask.MaskQueryResult
import com.gomsang.lab.publicmask.libs.http.mask.Mask
import com.gomsang.lab.publicmask.libs.http.mask.MaskAPI
import com.google.gson.Gson
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MaskRepositoryImpl : MaskRepository {
    private val api: MaskAPI by lazy {
        Mask().api
    }

    override fun find(lat: Double, lng: Double): Single<List<Stock>> {
        return Single.create { emitter ->
            api.searchPlace(lat, lng, "5000" /* 3km */).enqueue(object : Callback<MaskQueryResult> {
                override fun onFailure(call: Call<MaskQueryResult>, t: Throwable) {
                    Logger.log(Logger.LOG_NETWORK_RESULT, t.localizedMessage)
                    if (!emitter.isDisposed)
                        emitter.onError(t)
                }

                override fun onResponse(
                    call: Call<MaskQueryResult>, response: Response<MaskQueryResult>
                ) {
                    Logger.log(Logger.LOG_NETWORK_RESULT, Gson().toJson(response.body()))
                    val list = mutableListOf<Stock>()
                    response.body()?.stores?.forEach { list.add(Stock.convert(it)) }
                    if (!emitter.isDisposed)
                        emitter.onSuccess(list)
                }

            })
        }
    }

}
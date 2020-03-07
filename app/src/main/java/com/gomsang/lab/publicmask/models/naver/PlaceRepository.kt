package com.gomsang.lab.publicmask.models.naver

import com.gomsang.lab.publicmask.libs.datas.naver.NaverPlace
import io.reactivex.Single

interface PlaceRepository {
    fun searchPlaces(keyword : String, coordinate : String) : Single<List<NaverPlace>>
}
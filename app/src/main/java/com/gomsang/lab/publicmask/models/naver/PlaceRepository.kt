package com.gomsang.lab.publicmask.models.naver

import com.gomsang.lab.publicmask.libs.datas.Place
import io.reactivex.Single

interface PlaceRepository {
    fun searchPlaces(keyword : String) : Single<MutableList<Place>>
}
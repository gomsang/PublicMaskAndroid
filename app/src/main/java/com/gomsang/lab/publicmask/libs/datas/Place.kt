package com.gomsang.lab.publicmask.libs.datas

import android.os.Parcelable
import com.gomsang.lab.publicmask.libs.datas.kakao.Document
import kotlinx.android.parcel.Parcelize

@Parcelize
class Place(
    var name: String? = null,
    var address: String? = null,
    var phoneNumber: String? = null,
    var latitude: String? = null,
    var longitude: String? = null,
    var distance: String? = null
) : Parcelable {

    companion object {
        fun convert(kakao: Document): Place {
            val place = Place()
            place.address = kakao.roadAddressName
            place.distance = kakao.distance
            place.latitude = kakao.y
            place.longitude = kakao.x
            place.phoneNumber = kakao.phone
            place.name = kakao.placeName
            return place
        }
    }
}
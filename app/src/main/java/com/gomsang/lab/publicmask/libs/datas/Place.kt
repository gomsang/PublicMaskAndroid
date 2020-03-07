package com.gomsang.lab.publicmask.libs.datas

import com.gomsang.lab.publicmask.libs.datas.kakao.Document

class Place {
    private var name: String? = null
    private var address: String? = null
    private var phoneNumber: String? = null
    private var latitude: String? = null
    private var longitude: String? = null
    private var distance: String? = null

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getAddress(): String? {
        return address
    }

    fun setAddress(address: String?) {
        this.address = address
    }

    fun getPhoneNumber(): String? {
        return phoneNumber
    }

    fun setPhoneNumber(phoneNumber: String?) {
        this.phoneNumber = phoneNumber
    }

    fun getLatitude(): String? {
        return latitude
    }

    fun setLatitude(latitude: String?) {
        this.latitude = latitude
    }

    fun getLongitude(): String? {
        return longitude
    }

    fun setLongitude(longitude: String?) {
        this.longitude = longitude
    }

    fun getDistance(): String? {
        return distance
    }

    fun setDistance(distance: String?) {
        this.distance = distance
    }

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
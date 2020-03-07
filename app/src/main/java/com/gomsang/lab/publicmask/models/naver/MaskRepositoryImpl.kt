package com.gomsang.lab.publicmask.models.naver

import com.gomsang.lab.publicmask.libs.http.kakao.Kakao
import com.gomsang.lab.publicmask.libs.http.kakao.KakaoAPI
import com.gomsang.lab.publicmask.libs.http.mask.Mask
import com.gomsang.lab.publicmask.libs.http.mask.MaskAPI

class MaskRepositoryImpl : MaskRepository{
    private val api: MaskAPI by lazy {
        Mask().api
    }

    override fun find(lat: String, lng: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
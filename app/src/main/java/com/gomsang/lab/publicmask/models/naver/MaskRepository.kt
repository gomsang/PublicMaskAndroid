package com.gomsang.lab.publicmask.models.naver

import com.gomsang.lab.publicmask.libs.datas.Stock
import com.gomsang.lab.publicmask.libs.http.mask.Mask
import io.reactivex.Single

interface MaskRepository {
    fun find(lat : Double, lng : Double) : Single<List<Stock>>
}
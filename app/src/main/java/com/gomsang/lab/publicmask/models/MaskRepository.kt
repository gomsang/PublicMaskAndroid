package com.gomsang.lab.publicmask.models

import com.gomsang.lab.publicmask.libs.datas.Stock
import io.reactivex.Single

interface MaskRepository {
    fun find(lat : Double, lng : Double) : Single<List<Stock>>
}
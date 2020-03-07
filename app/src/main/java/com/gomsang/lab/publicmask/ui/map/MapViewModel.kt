package com.gomsang.lab.publicmask.ui.map

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gomsang.lab.publicmask.base.BaseViewModel
import com.gomsang.lab.publicmask.libs.datas.Stock
import com.gomsang.lab.publicmask.models.naver.MaskRepository
import com.naver.maps.map.NaverMap
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MapViewModel(val maskRepository: MaskRepository) : BaseViewModel(){
    val stockLiveData = MutableLiveData<List<Stock>>()

    fun query(lat : Double, lng : Double){
        val disposable = maskRepository.find(lat, lng)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                stockLiveData.value = it
            }, {
            })
        addDisposable(disposable)
    }
}
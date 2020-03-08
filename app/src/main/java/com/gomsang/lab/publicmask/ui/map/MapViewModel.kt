package com.gomsang.lab.publicmask.ui.map

import androidx.lifecycle.MutableLiveData
import com.gomsang.lab.publicmask.base.BaseViewModel
import com.gomsang.lab.publicmask.libs.datas.Stock
import com.gomsang.lab.publicmask.models.MaskRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MapViewModel(private val maskRepository: MaskRepository) : BaseViewModel(){
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
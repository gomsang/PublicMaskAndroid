package com.gomsang.lab.publicmask.ui.search

import androidx.lifecycle.MutableLiveData
import com.gomsang.lab.publicmask.base.BaseViewModel
import com.gomsang.lab.publicmask.libs.datas.Place
import com.gomsang.lab.publicmask.models.PlaceRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SearchViewModel(val place: PlaceRepository) : BaseViewModel() {
    var latestSearchKeyword = ""
        private set
    val messageLiveData = MutableLiveData<Any>()

    val placeSearchPlacesLiveData = MutableLiveData<MutableList<Place>>()

    fun search(keyword: String) {
        if (keyword.length < 2) {
            messageLiveData.value = "검색어는 두 글자 이상 입력해주세요"
            return
        }
        latestSearchKeyword = keyword

        val disposable = place.searchPlaces(keyword)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                placeSearchPlacesLiveData.value = it!!
            }, {
            })
        addDisposable(disposable)
    }
}
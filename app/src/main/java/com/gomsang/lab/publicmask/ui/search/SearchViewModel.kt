package com.gomsang.lab.publicmask.ui.search

import android.content.Context
import android.location.Location
import androidx.lifecycle.MutableLiveData
import com.gomsang.lab.publicmask.base.BaseViewModel
import com.gomsang.lab.publicmask.libs.datas.Place
import com.gomsang.lab.publicmask.models.PlaceRepository
import io.nlopez.smartlocation.SmartLocation
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SearchViewModel(val place: PlaceRepository) : BaseViewModel() {
    var latestSearchKeyword = ""
        private set
    val messageLiveData = MutableLiveData<Any>()

    val placeSearchPlacesLiveData = MutableLiveData<MutableList<Place>>()

    val locationLiveData = MutableLiveData<Location>()

    fun search(keyword: String) {
        if (keyword.length < 2) {
            messageLiveData.value = "Please enter at least two letters for the search term."
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

    fun requestLocation(context : Context){
        SmartLocation.with(context).location()
            .oneFix()
            .start {
                locationLiveData.value = it
            }
    }
}
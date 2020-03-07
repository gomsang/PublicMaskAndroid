package com.gomsang.lab.publicmask.ui.search

import android.util.Log
import com.gomsang.lab.publicmask.base.BaseViewModel
import com.gomsang.lab.publicmask.libs.constants.Logger
import com.gomsang.lab.publicmask.models.naver.PlaceRepository
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SearchViewModel(val place: PlaceRepository) : BaseViewModel() {


    fun search(keyword: String) {
        val disposable = place.searchPlaces(keyword)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
            }, {
            })
    }
}
package com.gomsang.lab.publicmask.ui.main

import android.Manifest
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.gomsang.lab.publicmask.base.BaseViewModel
import com.gun0912.tedpermission.TedPermissionResult
import com.tedpark.tedpermission.rx2.TedRx2Permission
import io.reactivex.disposables.CompositeDisposable

class MainViewModel : BaseViewModel() {
    val compositeDisposable = CompositeDisposable()
    val permissionRequest = MutableLiveData<Boolean>()

    fun permissionCheck(context : Context) {
        val disposable = TedRx2Permission.with(context)
            .setRationaleTitle("Request Permission")
            .setRationaleMessage("Permission is required to provide information that is close to you.")
            .setPermissions(
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            .request()
            .subscribe(
                { tedPermissionResult: TedPermissionResult ->
                   permissionRequest.value = tedPermissionResult.isGranted
                },
                { }
            )
        compositeDisposable.add(disposable)
    }
}
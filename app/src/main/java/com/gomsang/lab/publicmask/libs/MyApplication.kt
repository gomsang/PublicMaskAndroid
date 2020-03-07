package com.gomsang.lab.publicmask.libs

import android.app.Application
import com.gomsang.lab.publicmask.R
import com.naver.maps.map.NaverMapSdk
import com.naver.maps.map.NaverMapSdk.NaverCloudPlatformClient
import org.koin.android.ext.android.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(applicationContext, myDiModule)

        NaverMapSdk.getInstance(this).client =
            NaverCloudPlatformClient(getString(R.string.naver_client_id))
    }
}
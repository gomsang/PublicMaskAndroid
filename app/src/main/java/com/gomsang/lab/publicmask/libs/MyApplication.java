package com.gomsang.lab.publicmask.libs;

import android.app.Application;

import com.gomsang.lab.publicmask.R;
import com.naver.maps.map.NaverMapSdk;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        NaverMapSdk.getInstance(this).setClient(
                new NaverMapSdk.NaverCloudPlatformClient(getString(R.string.naver_client_id)));
    }
}
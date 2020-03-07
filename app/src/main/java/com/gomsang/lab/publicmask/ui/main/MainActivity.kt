package com.gomsang.lab.publicmask.ui.main

import android.Manifest
import android.widget.Toast
import com.gomsang.lab.publicmask.R
import com.gomsang.lab.publicmask.base.BaseActivity
import com.gomsang.lab.publicmask.databinding.ActivityMainBinding
import com.gun0912.tedpermission.TedPermissionResult
import com.tedpark.tedpermission.rx2.TedRx2Permission
import io.reactivex.functions.Consumer
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_main
    override val viewModel: MainViewModel by viewModel()

    override fun initStartView() {
        TedRx2Permission.with(this)
            .setRationaleTitle("권한요청")
            .setRationaleMessage("사용자의 가까운 위치의 정보를 제공하기 위해 권한 허용이 필요합니다.")
            .setPermissions(
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            .request()
            .subscribe(
                { tedPermissionResult: TedPermissionResult ->
                    if (tedPermissionResult.isGranted) {
                    } else {
                        Toast.makeText(
                            this,
                            "권한 거부됨\n" + tedPermissionResult.deniedPermissions.toString(),
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                },
                { }
            )
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
    }
}
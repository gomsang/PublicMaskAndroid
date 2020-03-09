package com.gomsang.lab.publicmask.ui.main

import android.Manifest
import android.content.DialogInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.gomsang.lab.publicmask.R
import com.gomsang.lab.publicmask.base.BaseActivity
import com.gomsang.lab.publicmask.databinding.ActivityMainBinding
import com.gun0912.tedpermission.TedPermissionResult
import com.tedpark.tedpermission.rx2.TedRx2Permission
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
                        finish()
                    }
                },
                { }
            )
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
        val wv = WebView(this)
        wv.loadUrl("https://gomsang.github.io/notice_mask19")
        wv.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }
        }
        val builder = AlertDialog.Builder(this@MainActivity)
        builder.setView(wv)
            .setPositiveButton("확인",
                DialogInterface.OnClickListener { dialog, id ->
                })
        builder.create().show()

    }
}
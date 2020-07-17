package com.gomsang.lab.publicmask.ui.main

import android.Manifest
import android.content.DialogInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
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
        viewModel.permissionRequest.observe(this, Observer {
            if (it) {
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                finish()
            }
        })
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
            .setPositiveButton(
                "Confirm"
            ) { _, _ ->
            }
        builder.create().show()

    }
}
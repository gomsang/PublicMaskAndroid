package com.gomsang.lab.publicmask.ui.main

import android.Manifest
import android.content.DialogInterface
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
        val builder = AlertDialog.Builder(this@MainActivity)
        builder.setMessage(
            "이용자 여러분께,\n" +
                    "\n" +
                    "마스크19 애플리케이션을 이용해주셔서 감사합니다. 현재 간단하게 공적 마스크 재고를 알려드리고 있습니다. 특정 위치를 검색하시거나 현재 위치로 검색을 누르셔서 주변 점포들의 공적 마스크 재고를 파악할 수 있습니다.\n" +
                    "\n" +
                    "마스크 재고 상태의 경우 5-10분 마다 업데이트 되오니 재고 파악에 참고하시기 바랍니다. 대학생 개인이 개발하고 있는 애플리케이션으로, 오류에 대한 대처가 늦더라도 너른 양해 부탁드립니다.\n" +
                    "\n" +
                    "건강 잘 챙기시길 바랍니다."
        )
            .setPositiveButton("확인",
                DialogInterface.OnClickListener { dialog, id ->
                    // FIRE ZE MISSILES!
                })
        builder.create().show()

    }
}
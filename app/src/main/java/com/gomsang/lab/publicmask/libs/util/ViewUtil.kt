package com.gomsang.lab.publicmask.libs.util

import android.view.ViewGroup

object ViewUtil {
    fun disableEnableControls(enable: Boolean, vg: ViewGroup) {
        for (i in 0 until vg.childCount) {
            val child = vg.getChildAt(i)
            child.isEnabled = enable
            if (child is ViewGroup) {
                disableEnableControls(
                    enable,
                    child
                )
            }
        }
    }
}
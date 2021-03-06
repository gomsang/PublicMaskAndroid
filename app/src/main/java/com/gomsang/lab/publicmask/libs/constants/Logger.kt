package com.gomsang.lab.publicmask.libs.constants

import android.util.Log
import com.gomsang.lab.publicmask.BuildConfig

object Logger {
    val LOG_NETWORK_RESULT = "LOG_NETWORK_RESULT"

    fun log(tag: String, value: String): Boolean {
        return if(BuildConfig.DEBUG){
            Log.d(tag, value)
            true
        }else {
            false
        }
    }
}
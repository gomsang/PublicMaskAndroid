package com.gomsang.lab.publicmask.ui.preference

import android.content.Intent
import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.gomsang.lab.publicmask.R
import com.gomsang.lab.publicmask.ui.webview.WebViewActivity

class PreferenceFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)

        (findPreference("license_opensource") as Preference?)?.let {
            it.setOnPreferenceClickListener {
                openWebView("file:///android_asset/license_opensource.html")
                return@setOnPreferenceClickListener false
            }
        }
    }

    private fun openWebView(url: String) {
        val webViewIntent = Intent(context, WebViewActivity::class.java)
        webViewIntent.putExtra("url", url)
        startActivity(webViewIntent)
    }
}
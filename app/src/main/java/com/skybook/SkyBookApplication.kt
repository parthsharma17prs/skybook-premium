package com.skybook

import android.app.Application
import com.skybook.utils.PrefsManager

class SkyBookApplication : Application() {
    lateinit var prefsManager: PrefsManager

    override fun onCreate() {
        super.onCreate()
        prefsManager = PrefsManager(this)
    }
}

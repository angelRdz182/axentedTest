package com.itn.myapplicationaxen

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import dagger.hilt.android.HiltAndroidApp
import java.io.File

@HiltAndroidApp
class BaseApplication: Application() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate() {
        super.onCreate()
        val dexOutputDir: File = codeCacheDir
        dexOutputDir.setReadOnly()
    }
}
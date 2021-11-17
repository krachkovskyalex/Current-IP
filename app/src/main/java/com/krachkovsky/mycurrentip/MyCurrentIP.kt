package com.krachkovsky.mycurrentip

import android.app.Application
import com.krachkovsky.mycurrentip.koin.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyCurrentIP : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyCurrentIP)
            modules(appModule)
        }

    }

}
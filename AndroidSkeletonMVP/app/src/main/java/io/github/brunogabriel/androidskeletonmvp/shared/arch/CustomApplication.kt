package io.github.brunogabriel.androidskeletonmvp.shared.arch

import android.app.Application
import com.facebook.stetho.Stetho
import io.github.brunogabriel.androidskeletonmvp.BuildConfig
import io.github.brunogabriel.androidskeletonmvp.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by brunosantos on 2019-10-03.
 */
class CustomApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
        startKoin {
            androidContext(this@CustomApplication)
            modules(databaseModule + networkModule + repositoryModule + mainModule)
        }
    }
}
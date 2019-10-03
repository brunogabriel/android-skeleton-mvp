package io.github.brunogabriel.androidskeletonmvp.di

import androidx.annotation.VisibleForTesting
import io.github.brunogabriel.androidskeletonmvp.BuildConfig
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by brunosantos on 2019-10-03.
 */

@VisibleForTesting
var baseUrl: String? = null

val networkModule = module {
    factory {
        OkHttpClient.Builder()
            .readTimeout(60L, TimeUnit.SECONDS)
            .connectTimeout(40L, TimeUnit.SECONDS)
            .build()
    }
    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(baseUrl ?: BuildConfig.API_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
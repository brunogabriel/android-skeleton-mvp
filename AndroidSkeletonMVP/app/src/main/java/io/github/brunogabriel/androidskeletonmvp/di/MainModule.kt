package io.github.brunogabriel.androidskeletonmvp.di

import io.github.brunogabriel.androidskeletonmvp.main.MainContract
import io.github.brunogabriel.androidskeletonmvp.main.MainPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.dsl.module

/**
 * Created by brunosantos on 2019-10-03.
 */
val mainModule = module {
    factory<MainContract.Presenter> { (view: MainContract.View) ->
        MainPresenter(
            view,
            get(),
            Schedulers.io(),
            AndroidSchedulers.mainThread()
        )
    }
}
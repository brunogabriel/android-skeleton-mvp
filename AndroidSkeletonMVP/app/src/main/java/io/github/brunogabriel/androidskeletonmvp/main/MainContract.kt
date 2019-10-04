package io.github.brunogabriel.androidskeletonmvp.main

import io.github.brunogabriel.androidskeletonmvp.shared.arch.BaseView
import io.github.brunogabriel.androidskeletonmvp.shared.arch.LoadingView
import io.github.brunogabriel.androidskeletonmvp.shared.database.model.Photo

/**
 * Created by brunosantos on 2019-10-03.
 */
interface MainContract {
    interface View : BaseView<Presenter>, LoadingView {
        fun showPhotos(photos: List<Photo>)
        fun showError()
    }

    interface Presenter {
        fun initialize()
        fun onViewWillFinish()
        fun onClickAtTryAgain()
    }
}
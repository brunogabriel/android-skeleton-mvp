package io.github.brunogabriel.androidskeletonmvp.main

import io.github.brunogabriel.androidskeletonmvp.shared.repository.PhotoRepository
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by brunosantos on 2019-10-03.
 */
class MainPresenter(
    private val view: MainContract.View,
    private val photoRepository: PhotoRepository,
    private val subscribeScheduler: Scheduler,
    private val observeScheduler: Scheduler
) : MainContract.Presenter {
    private val compositeDisposable = CompositeDisposable()

    override fun initialize() {
        requestData()
    }

    override fun onViewWillFinish() {
        compositeDisposable.clear()
    }

    override fun onClickAtTryAgain() {
        requestData()
    }

    private fun requestData() {
        compositeDisposable.add(photoRepository.findPhotos()
            .subscribeOn(subscribeScheduler)
            .observeOn(observeScheduler)
            .doOnSubscribe { view.showLoading() }
            .doAfterTerminate { view.dismissLoading() }
            .subscribe({
                view.showPhotos(it)
            }, {
                view.showError()
            })
        )
    }
}
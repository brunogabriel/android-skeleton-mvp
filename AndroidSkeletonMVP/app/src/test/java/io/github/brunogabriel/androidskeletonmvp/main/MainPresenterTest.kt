package io.github.brunogabriel.androidskeletonmvp.main

import android.accounts.NetworkErrorException
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.github.brunogabriel.androidskeletonmvp.shared.database.model.Photo
import io.github.brunogabriel.androidskeletonmvp.shared.repository.PhotoRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test

/**
 * Created by brunosantos on 2019-10-04.
 */
class MainPresenterTest {
    private lateinit var presenter: MainContract.Presenter
    private lateinit var photoRepository: PhotoRepository
    private lateinit var view: MainContract.View

    @Before
    fun setup() {
        view = mock()
        photoRepository = mock()
        presenter =
            MainPresenter(view, photoRepository, Schedulers.trampoline(), Schedulers.trampoline())
    }

    @Test
    fun shouldShowPhotosWhenInitialize() {
        // given
        val photos = listOf(Photo(1L, "title", "url", "thumb"))
        whenever(photoRepository.findPhotos()).doReturn(Single.just(photos))

        // when
        presenter.initialize()

        // then
        verify(view).showLoading()
        verify(view).showPhotos(photos)
        verify(view).dismissLoading()
    }

    @Test
    fun shouldShowErrorWhenInitialize() {
        // given
        whenever(photoRepository.findPhotos()).doReturn(Single.error(NetworkErrorException("Network error")))

        // when
        presenter.initialize()

        // then
        verify(view).showLoading()
        verify(view).showError()
        verify(view).dismissLoading()
    }

    @Test(expected = Test.None::class)
    fun shouldDisposeWhenViewWillFinish() {
        presenter.onViewWillFinish()
    }

    @Test
    fun shouldFindPhotosWhenOnClickAtTryAgain() {
        // given
        val photos = listOf(Photo(1L, "title", "url", "thumb"))
        whenever(photoRepository.findPhotos()).doReturn(Single.just(photos))

        // when
        presenter.onClickAtTryAgain()

        // then
        verify(view).showLoading()
        verify(view).showPhotos(photos)
        verify(view).dismissLoading()
    }
}
package io.github.brunogabriel.androidskeletonmvp.shared.repository

import com.nhaarman.mockitokotlin2.*
import io.github.brunogabriel.androidskeletonmvp.shared.database.dao.PhotoDao
import io.github.brunogabriel.androidskeletonmvp.shared.database.model.Photo
import io.github.brunogabriel.androidskeletonmvp.shared.network.PhotoService
import io.reactivex.Completable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

/**
 * Created by brunosantos on 2019-10-04.
 */
class PhotoRepositoryTest {
    private lateinit var photoRepository: PhotoRepository
    private lateinit var photoService: PhotoService
    private lateinit var photoDao: PhotoDao

    @Before
    fun setup() {
        photoService = mock()
        photoDao = mock()
        photoRepository = PhotoRepositoryImpl(
            photoDao,
            photoService
        )
    }

    @Test
    fun shouldFindPhotosFromPhotoDao() {
        // given
        val photos = listOf(
            Photo(1L, "title", "url", "thumb"),
            Photo(2L, "title", "url", "thumb")
        )
        whenever(photoDao.findAll()).doReturn(Single.just(photos))

        // when
        val expected = photoRepository.findPhotos()

        // then
        expected.test()
            .assertValueAt(0, photos)
            .assertComplete()
            .dispose()
    }

    @Test
    fun shouldFetchPhotosFromServiceAndSaveInDatabase() {
        // given
        val photos = listOf(
            Photo(1L, "title", "url", "thumb"),
            Photo(2L, "title", "url", "thumb")
        )
        whenever(photoDao.findAll()).doReturn(Single.just(emptyList()), Single.just(photos))
        whenever(photoService.fetchPhotos()).doReturn(Single.just(photos))
        whenever(photoDao.insertAll(photos)).doReturn(Completable.complete())

        // when
        val expected = photoRepository.findPhotos()

        // then
        expected.test()
            .assertValueAt(0, photos)
            .assertComplete()
            .dispose()
    }
}
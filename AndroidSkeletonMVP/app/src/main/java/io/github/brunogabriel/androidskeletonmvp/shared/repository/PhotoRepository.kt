package io.github.brunogabriel.androidskeletonmvp.shared.repository

import io.github.brunogabriel.androidskeletonmvp.shared.database.dao.PhotoDao
import io.github.brunogabriel.androidskeletonmvp.shared.database.model.Photo
import io.github.brunogabriel.androidskeletonmvp.shared.network.PhotoService
import io.reactivex.Single

/**
 * Created by brunosantos on 2019-10-03.
 */
interface PhotoRepository {
    fun findPhotos(): Single<List<Photo>>
}

class PhotoRepositoryImpl(
    private val photoDao: PhotoDao,
    private val photoService: PhotoService
) : PhotoRepository {

    override fun findPhotos(): Single<List<Photo>> {
        return photoDao.findAll().flatMap { photos ->
            if (photos.isNotEmpty()) {
                Single.just(photos)
            } else {
                fetchPhotosFromService()
            }
        }
    }

    private fun fetchPhotosFromService(): Single<List<Photo>> {
        return photoService.fetchPhotos().flatMap { photos ->
            photoDao.insertAll(photos).andThen(photoDao.findAll())
        }
    }
}
package io.github.brunogabriel.androidskeletonmvp.helper

import io.github.brunogabriel.androidskeletonmvp.shared.database.dao.PhotoDao
import io.github.brunogabriel.androidskeletonmvp.shared.database.model.Photo

/**
 * Created by brunosantos on 2019-10-04.
 */
fun mockPhotos(photoDao: PhotoDao, numberOfPhotos: Int) {
    photoDao.insertAll((1..numberOfPhotos).toList().map {
        Photo(it.toLong(), "title$it", "url$it", "thumb$it")
    }).blockingAwait()
}
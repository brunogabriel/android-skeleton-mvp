package io.github.brunogabriel.androidskeletonmvp.shared.network

import io.github.brunogabriel.androidskeletonmvp.shared.database.model.Photo
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Created by brunosantos on 2019-10-03.
 */
interface PhotoService {
    @GET("/photos")
    fun fetchPhotos(): Single<List<Photo>>
}
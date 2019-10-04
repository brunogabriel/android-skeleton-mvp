package io.github.brunogabriel.androidskeletonmvp.di

import io.github.brunogabriel.androidskeletonmvp.shared.network.PhotoService
import io.github.brunogabriel.androidskeletonmvp.shared.repository.PhotoRepository
import io.github.brunogabriel.androidskeletonmvp.shared.repository.PhotoRepositoryImpl
import org.koin.dsl.module
import retrofit2.Retrofit

/**
 * Created by brunosantos on 2019-10-03.
 */
val repositoryModule = module {
    factory<PhotoRepository> {
        val retrofit = get<Retrofit>()
        PhotoRepositoryImpl(get(), retrofit.create(PhotoService::class.java))
    }
}
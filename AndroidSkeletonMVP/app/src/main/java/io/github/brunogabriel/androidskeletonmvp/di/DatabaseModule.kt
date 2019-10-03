package io.github.brunogabriel.androidskeletonmvp.di

import androidx.annotation.VisibleForTesting
import androidx.room.Room
import io.github.brunogabriel.androidskeletonmvp.shared.database.AppDatabase
import org.koin.dsl.module

/**
 * Created by brunosantos on 2019-10-03.
 */
@VisibleForTesting
var inMemoryDatabase = false

val databaseModule = module {
    single {
        if (inMemoryDatabase) {
            Room.inMemoryDatabaseBuilder(get(), AppDatabase::class.java).build()
        } else {
            Room.databaseBuilder(get(), AppDatabase::class.java, "app-database").build()
        }
    }

    factory { get<AppDatabase>().photoDao() }
}

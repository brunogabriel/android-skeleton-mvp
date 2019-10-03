package io.github.brunogabriel.androidskeletonmvp.shared.database

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.brunogabriel.androidskeletonmvp.shared.database.dao.PhotoDao
import io.github.brunogabriel.androidskeletonmvp.shared.database.model.Photo

/**
 * Created by brunosantos on 2019-10-03.
 */
@Database(
    version = 1,
    exportSchema = false,
    entities = [Photo::class]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun photoDao(): PhotoDao
}
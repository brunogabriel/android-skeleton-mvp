package io.github.brunogabriel.androidskeletonmvp.shared.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.brunogabriel.androidskeletonmvp.shared.database.model.Photo
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by brunosantos on 2019-10-03.
 */
@Dao
interface PhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(photos: List<Photo>): Completable

    @Query("SELECT * FROM photos")
    fun findAll(): Single<List<Photo>>
}
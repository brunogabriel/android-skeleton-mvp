package io.github.brunogabriel.androidskeletonmvp.shared.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by brunosantos on 2019-10-03.
 */
@Entity(tableName = "photos")
data class Photo(
    @PrimaryKey
    val id: Long = 0L,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)
package io.github.brunogabriel.androidskeletonmvp.shared.database.dao

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.github.brunogabriel.androidskeletonmvp.shared.database.AppDatabase
import io.github.brunogabriel.androidskeletonmvp.shared.database.model.Photo
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 * Created by brunosantos on 2019-10-04.
 */
@RunWith(AndroidJUnit4::class)
class PhotoDaoTest {
    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var photoDao: PhotoDao
    private lateinit var appDatabase: AppDatabase

    @Before
    fun setup() {
        appDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext<Context>(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        photoDao = appDatabase.photoDao()
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        appDatabase.close()
    }

    @Test
    fun shouldInsertAndFindPhotos() {
        // given
        val photos = listOf(
            Photo(title = "title1", url = "url1", thumbnailUrl = "thumbnailUrl1")
        )

        // then
        photoDao.insertAll(photos).test()
            .assertComplete()
            .dispose()

        photoDao.findAll().test()
            .assertComplete()
            .assertValue { it[0].title == photos[0].title }
            .assertValue { it[0].url == photos[0].url }
            .assertValue { it[0].thumbnailUrl == photos[0].thumbnailUrl }
            .dispose()

    }
}
package io.github.brunogabriel.androidskeletonmvp.main

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import io.github.brunogabriel.androidskeletonmvp.helper.InstrumentalActivityKoinTest
import io.github.brunogabriel.androidskeletonmvp.helper.RobotsRule
import io.github.brunogabriel.androidskeletonmvp.helper.mockPhotos
import io.github.brunogabriel.androidskeletonmvp.shared.database.dao.PhotoDao
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.koin.test.inject

/**
 * Created by brunosantos on 2019-10-04.
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest : InstrumentalActivityKoinTest() {
    @get: Rule
    val activityRule = ActivityTestRule(MainActivity::class.java, true, false)

    @get: Rule
    val mainActivityRobots = RobotsRule(MainActivityRobots(activityRule))

    private val photoDao: PhotoDao by inject()

    @Before
    fun setup() {
        mainActivityRobots.setup()
        loadDefaultModules()
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun shouldShowPhotos() {
        mainActivityRobots {
            mockPhotos(photoDao, 6)
            startScreen()
            checkNumberOfItemsDisplayed(6)
        }
    }

    @Test
    fun shouldDisplayPhotoCorrectly() {
        mainActivityRobots {
            mockPhotos(photoDao, 6)
            startScreen()
            checkDisplayPhotoTitle(0, "title1")
        }
    }

    @Test
    fun shouldDisplayTryAgain() {
        mainActivityRobots {
            startScreen()
            checkDisplayTryAgainAfterClick()
        }
    }
}
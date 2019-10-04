package io.github.brunogabriel.androidskeletonmvp.main

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.github.tomakehurst.wiremock.junit.WireMockRule
import io.github.brunogabriel.androidskeletonmvp.R
import io.github.brunogabriel.androidskeletonmvp.di.inMemoryDatabase
import io.github.brunogabriel.androidskeletonmvp.di.urlTest
import io.github.brunogabriel.androidskeletonmvp.helper.Robots
import io.github.brunogabriel.androidskeletonmvp.helper.atPosition
import io.github.brunogabriel.androidskeletonmvp.helper.hasItemCount
import org.junit.Rule
import androidx.test.espresso.assertion.ViewAssertions.matches
import com.github.tomakehurst.wiremock.client.WireMock

/**
 * Created by brunosantos on 2019-10-04.
 */
class MainActivityRobots(
    private val rule: ActivityTestRule<MainActivity>
) : Robots {
    @get: Rule
    val mockServer = WireMockRule()

    override fun setup() {
        urlTest = "http://127.0.0.1:8080"
        inMemoryDatabase = true
    }

    fun startScreen() {
        rule.launchActivity(null)
    }

    fun checkNumberOfItemsDisplayed(numberOfItems: Int) {
        onView(withId(R.id.photos_recycler_view)).check(hasItemCount(numberOfItems))
    }

    fun checkDisplayPhotoTitle(position: Int, title: String) {
        onView(withId(R.id.photos_recycler_view))
            .check(matches(atPosition(position, hasDescendant(withText(title)))))
    }

    fun checkDisplayTryAgainAfterClick() {
        mockServerErrorResponse()
        onView(withId(R.id.try_again_view)).check(matches(isDisplayed()))
        onView(withId(R.id.try_again_button)).perform(click())
        onView(withId(R.id.try_again_view)).check(matches(isDisplayed()))
    }

    private fun mockServerErrorResponse() {
        mockServer.stubFor(
            WireMock.get(WireMock.urlPathEqualTo("/photos"))
                .willReturn(
                    WireMock.aResponse()
                        .withStatus(404)
                )
        )
    }
}
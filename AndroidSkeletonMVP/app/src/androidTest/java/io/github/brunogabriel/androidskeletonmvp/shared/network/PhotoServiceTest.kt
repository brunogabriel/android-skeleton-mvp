package io.github.brunogabriel.androidskeletonmvp.shared.network

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.junit.WireMockRule
import io.github.brunogabriel.androidskeletonmvp.di.networkModule
import io.github.brunogabriel.androidskeletonmvp.di.urlTest
import io.github.brunogabriel.androidskeletonmvp.shared.database.model.Photo
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.get
import retrofit2.Retrofit

/**
 * Created by brunosantos on 2019-10-04.
 */
@RunWith(AndroidJUnit4::class)
class PhotoServiceTest : KoinTest {
    @get: Rule
    val mockServer = WireMockRule()

    private lateinit var service: PhotoService
    private lateinit var retrofit: Retrofit

    @Before
    fun setup() {
        loadKoinModules(module { networkModule })
        urlTest = "http://127.0.0.1:8080"
        retrofit = get()
        service = retrofit.create(PhotoService::class.java)
    }

    @Test
    fun shouldFetchPhotos() {
        // given
        val expected = listOf(
            Photo(1L, "title", "url", "thumb")
        )

        // when
        mockServer.stubFor(
            get(urlPathEqualTo("/photos"))
                .willReturn(
                    aResponse()
                        .withStatus(200)
                        .withBody(
                            "[" +
                                    "    {" +
                                    "        \"id\": 1," +
                                    "        \"title\": \"title\"," +
                                    "        \"url\": \"url\"," +
                                    "        \"thumbnailUrl\": \"thumb\"" +
                                    "    }" +
                                    "]"
                        )
                )
        )

        // then
        service.fetchPhotos().test()
            .assertComplete()
            .assertValueAt(0, expected)
            .dispose()
    }
}
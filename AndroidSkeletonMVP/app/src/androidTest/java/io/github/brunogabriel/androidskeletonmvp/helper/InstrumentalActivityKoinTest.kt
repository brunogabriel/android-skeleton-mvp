package io.github.brunogabriel.androidskeletonmvp.helper

import io.github.brunogabriel.androidskeletonmvp.di.databaseModule
import io.github.brunogabriel.androidskeletonmvp.di.networkModule
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import org.koin.test.KoinTest

/**
 * Created by brunosantos on 2019-10-04.
 */
open class InstrumentalActivityKoinTest : KoinTest {
    fun loadDefaultModules() {
        loadKoinModules(module { networkModule })
        loadKoinModules(module { databaseModule })
    }
}
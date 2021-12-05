package com.example.androidplayground

import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito.mock
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExecutionRepositoryTest {


    private val executionRepository = ExecutionRepository(mock())

    @Test
    fun executeTest() {
        executionRepository.execute("", "").test()


    }
}

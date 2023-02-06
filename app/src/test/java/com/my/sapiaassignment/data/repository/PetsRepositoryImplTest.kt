package com.my.sapiaassignment.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.my.sapiaassignment.datalayer.ApiService
import com.my.sapiaassignment.datalayer.repository.petslist.PetsRepository
import com.my.sapiaassignment.datalayer.repository.petslist.PetsRepositoryImpl
import com.my.sapiaassignment.utils.MockWebServerBaseTest
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.net.HttpURLConnection

@RunWith(JUnit4::class)
class PetsRepositoryImplTest : MockWebServerBaseTest() {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private lateinit var petsRepository: PetsRepository
    private lateinit var apiService: ApiService

    override fun isMockServerEnabled() = true

    @Before
    fun start() {
        apiService = provideTestApiService()
        petsRepository = PetsRepositoryImpl(apiService)
    }

    @Test
    fun `given response ok when fetching results then return a list with elements`() {
        runBlocking {
            mockHttpResponse("json/pets_item_list.json", HttpURLConnection.HTTP_OK)
            val apiResponse = apiService.getPetsList()
            assertNotNull(apiResponse)
            assertEquals(apiResponse.body()?.pets?.size, 10)
        }
    }

    @Test
    fun `given response ok when fetching empty results then return an empty list`() {
        runBlocking {
            mockHttpResponse("json/pets_empty_list.json", HttpURLConnection.HTTP_OK)
            val apiResponse = apiService.getPetsList()
            assertNotNull(apiResponse)
            assertEquals(apiResponse.body()?.pets?.size, 0)
        }
    }

}
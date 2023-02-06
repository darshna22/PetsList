package com.my.sapiaassignment.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.my.sapiaassignment.base.network.DataResult
import com.my.sapiaassignment.datalayer.apimodel.PetItem
import com.my.sapiaassignment.datalayer.repository.petslist.PetsRepository
import com.my.sapiaassignment.uilayer.petslist.PetsViewModel
import com.my.sapiaassignment.utils.FakeData
import com.my.sapiaassignment.utils.TestCoroutineRule
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.HttpException
import org.mockito.Mockito.`when` as whenever


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class PetsViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()
    private lateinit var viewModel: PetsViewModel

    @Mock
    private lateinit var petsRepository: PetsRepository

    @Mock
    private lateinit var petsListObserver: Observer<DataResult<List<PetItem>?>>

    @Before
    fun setUp() {
        viewModel = PetsViewModel(petsRepository)
    }

    @Test
    fun `when fetching results ok then return a empty list successfully`() {
        val emptyList = listOf<PetItem>()
        testCoroutineRule.runBlockingTest {
            viewModel.petsList.observeForever(petsListObserver)
            whenever(petsRepository.fetchPetsData()).thenAnswer {
                DataResult.Success(emptyList)
            }
            viewModel.fetchPetsListData()
            assertNotNull(viewModel.petsList.value)
            assertEquals(DataResult.Success(emptyList), viewModel.petsList.value)
        }
    }

    @Test
    fun `when fetching results ok then return list of pets successfully`() {
        val petsList = listOf(FakeData.getPet())
        testCoroutineRule.runBlockingTest {
            viewModel.petsList.observeForever(petsListObserver)
            whenever(petsRepository.fetchPetsData()).thenAnswer {
                DataResult.Success(petsList)
            }
            viewModel.fetchPetsListData()
            assertNotNull(viewModel.petsList.value)
            assertEquals(DataResult.Success(petsList), viewModel.petsList.value)
        }
    }

    @Test
    fun `when calling for results then return loading`() {
        testCoroutineRule.runBlockingTest {
            viewModel.petsList.observeForever(petsListObserver)
            viewModel.fetchPetsListData()
            verify(petsListObserver).onChanged(DataResult.Loading)
        }
    }

    @Test
    fun `when fetching results fails then return an error`() {
        val exception = mock(HttpException::class.java)
        testCoroutineRule.runBlockingTest {
            viewModel.petsList.observeForever(petsListObserver)
            whenever(petsRepository.fetchPetsData()).thenAnswer {
                DataResult.Error(exception)
            }
            viewModel.fetchPetsListData()
            assertNotNull(viewModel.petsList.value)
            assertEquals(DataResult.Error(exception), viewModel.petsList.value)
        }
    }

    @After
    fun tearDown() {
        viewModel.petsList.removeObserver(petsListObserver)
    }


}
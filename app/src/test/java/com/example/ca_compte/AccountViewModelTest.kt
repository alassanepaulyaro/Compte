package com.example.ca_compte

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.ca_compte.data.model.Bank
import com.example.ca_compte.presentation.AccountViewModel
import com.example.ca_compte.useCase.BankUseCase
import com.example.ca_compte.utils.DataMock.Companion.mockBanks
import com.example.ca_compte.utils.Resource
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class AccountViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    val dispatcher = TestCoroutineDispatcher()

    @Mock
    private lateinit var bankUseCase: BankUseCase
    private lateinit var accountViewModel: AccountViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        MockitoAnnotations.openMocks(this)
        accountViewModel = AccountViewModel(bankUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun getBankDataReturnSuccess():Unit = runTest {
        val expectedResponse = Resource.Success(mockBanks)
       whenever(bankUseCase.execute()).thenReturn(expectedResponse)

       accountViewModel.getBankData()

        assertEquals(expectedResponse, accountViewModel.bankData.value)
        verify(bankUseCase).execute()
    }

    @Test
    fun getBankDataReturnError() = runTest {
        val errorMessage = "An unknown error occurred"
        val expectedResponse = Resource.Error<List<Bank>>(errorMessage)
        whenever(bankUseCase.execute()).thenReturn(expectedResponse) //Resource.Error(errorMessage)

        accountViewModel.getBankData()

        assertEquals(expectedResponse, accountViewModel.bankData.value)
        verify(bankUseCase).execute()
    }

    @Test
    fun getBankDataReturnsLoading() = runTest {
        val expected = Resource.Loading<List<Bank>>()
        whenever(bankUseCase.execute()).thenReturn(expected)

        accountViewModel.getBankData()

        assertEquals(expected, accountViewModel.bankData.value)
        verify(bankUseCase).execute()
    }
}
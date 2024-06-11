package com.example.ca_compte

import com.example.ca_compte.data.model.Bank
import com.example.ca_compte.di.BankService
import com.example.ca_compte.repository.BankRepositoryImpl
import com.example.ca_compte.utils.DataMock.Companion.mockBanks
import com.example.ca_compte.utils.Resource
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import retrofit2.Response

@ExperimentalCoroutinesApi
class BankRepositoryImplTest {

    @Mock
    private lateinit var bankService: BankService

    private lateinit var bankRepository: BankRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        bankRepository = BankRepositoryImpl(bankService)
    }

    @Test
    fun getBanksReturnsSuccess(): Unit = runTest {
        val expectedResponse = Response.success(mockBanks)
        `when`(bankService.getBanks()).thenReturn(expectedResponse)

        val result = bankRepository.getBanks()

        assertTrue(result is Resource.Success)
        assertEquals(mockBanks, result.data)
        verify(bankService).getBanks()
    }

    @Test
    fun getBanksReturnsError () = runTest {
        val expectedResponse = Response.error<List<Bank>>(400, ResponseBody.create(null, "Error"))
        `when`(bankService.getBanks()).thenReturn(expectedResponse)

        val result = bankRepository.getBanks()

        assertTrue(result is Resource.Error)
        assertEquals(expectedResponse.message(), result.message)
        verify(bankService).getBanks()
    }

    @Test
    fun getBanksReturnsException (): Unit = runBlocking {
        val exceptionMessage = "An unknown error occurred"
        `when`(bankService.getBanks()).thenThrow(RuntimeException(exceptionMessage))

        val result = bankRepository.getBanks()

        assertTrue(result is Resource.Error)
        assertEquals(exceptionMessage, result.message)
        verify(bankService).getBanks()
    }
}
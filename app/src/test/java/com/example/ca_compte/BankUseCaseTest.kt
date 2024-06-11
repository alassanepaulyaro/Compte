package com.example.ca_compte

import com.example.ca_compte.repository.BankRepositoryImpl
import com.example.ca_compte.useCase.BankUseCase
import com.example.ca_compte.utils.DataMock.Companion.mockBanks
import com.example.ca_compte.utils.Resource
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class BankUseCaseTest {
    @Mock
    private lateinit var bankRepository: BankRepositoryImpl

    private lateinit var bankUseCase: BankUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        bankUseCase = BankUseCase(bankRepository)
    }

    @Test
    fun executeShouldReturnSuccess() = runTest {
        val expectedResponse = Resource.Success(mockBanks)
        `when`(bankRepository.getBanks()).thenReturn(expectedResponse)

        val result = bankUseCase.execute()

        assertTrue(result is Resource.Success)
        assertEquals(expectedResponse.data, result.data)
        verify(bankRepository).getBanks()
    }

    @Test
    fun executeShouldReturnError() = runTest {
        val expectedResponse = "Network Failure"
        `when`(bankRepository.getBanks()).thenReturn(Resource.Error(expectedResponse ))

        val result = bankUseCase.execute()

        assertTrue(result is Resource.Error)
        assertEquals(expectedResponse, result.message)
        verify(bankRepository).getBanks()
    }
}
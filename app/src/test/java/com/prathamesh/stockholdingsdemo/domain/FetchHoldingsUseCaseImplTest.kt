package com.prathamesh.stockholdingsdemo.domain

import com.prathamesh.stockholdingsdemo.domain.model.HoldingDomainModel
import com.prathamesh.stockholdingsdemo.domain.repository.PortfolioRepository
import com.prathamesh.stockholdingsdemo.domain.usecase.FetchHoldingsUseCaseImpl
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class FetchHoldingsUseCaseImplTest {

    private lateinit var portfolioRepository: PortfolioRepository
    private lateinit var useCase: FetchHoldingsUseCaseImpl

    @Before
    fun setUp() {
        portfolioRepository = mock(PortfolioRepository::class.java)
        useCase = FetchHoldingsUseCaseImpl(portfolioRepository)
    }

    @Test
    fun `invoke should return flow from portfolioRepository`(): Unit = runBlocking {
        // Arrange
        val expected = listOf(
            HoldingDomainModel("TCS", 10.0, 3000.0, 3050.0, 3100.0, 1000.0)
        )

        `when`(portfolioRepository.getHoldings()).thenReturn(flowOf(expected))

        // Act
        val result = useCase.invoke().first()

        // Assert
        assertEquals(expected, result)
        verify(portfolioRepository).getHoldings()
    }
}
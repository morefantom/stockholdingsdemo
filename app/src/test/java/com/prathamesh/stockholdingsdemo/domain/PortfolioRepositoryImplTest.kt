package com.prathamesh.stockholdingsdemo.domain

import com.prathamesh.stockholdingsdemo.data.remote.model.HoldingDataModel
import com.prathamesh.stockholdingsdemo.data.remote.model.PortfolioDataModel
import com.prathamesh.stockholdingsdemo.data.remote.model.PortfolioResponse
import com.prathamesh.stockholdingsdemo.data.remote.source.PortfolioService
import com.prathamesh.stockholdingsdemo.domain.mapper.HoldingDataToDomainMapper
import com.prathamesh.stockholdingsdemo.domain.model.HoldingDomainModel
import com.prathamesh.stockholdingsdemo.domain.repository.PortfolioRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.test.resetMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class PortfolioRepositoryImplTest {

    private lateinit var portfolioService: PortfolioService
    private lateinit var mapper: HoldingDataToDomainMapper
    private lateinit var repository: PortfolioRepositoryImpl

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        MockitoAnnotations.openMocks(this)

        portfolioService = mock(PortfolioService::class.java)
        mapper = mock(HoldingDataToDomainMapper::class.java)

        repository = PortfolioRepositoryImpl(portfolioService, mapper)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getHoldings should emit mapped HoldingDomainModel list`(): Unit = runBlocking {
        // Arrange
        val holdingDataList = listOf(
            HoldingDataModel("TCS", 10.0, 3000.0, 3050.0, 3100.0),
            HoldingDataModel("INFY", 5.0, 1500.0, 1480.0, 1490.0)
        )

        val domainModelList = listOf(
            HoldingDomainModel("TCS", 10.0, 3000.0, 3050.0, 3100.0, 1000.0),
            HoldingDomainModel("INFY", 5.0, 1500.0, 1480.0, 1490.0, -50.0)
        )

        val response = PortfolioResponse(
            data = PortfolioDataModel(
                userHolding = holdingDataList
            )
        )

        `when`(portfolioService.getPortfolio()).thenReturn(response)
        `when`(mapper.map(holdingDataList[0])).thenReturn(domainModelList[0])
        `when`(mapper.map(holdingDataList[1])).thenReturn(domainModelList[1])

        // Act
        val result = repository.getHoldings().first()

        // Assert
        assertEquals(domainModelList, result)

        verify(portfolioService).getPortfolio()
        verify(mapper).map(holdingDataList[0])
        verify(mapper).map(holdingDataList[1])
    }
}
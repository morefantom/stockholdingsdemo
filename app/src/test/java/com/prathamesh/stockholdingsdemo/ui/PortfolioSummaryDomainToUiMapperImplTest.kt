package com.prathamesh.stockholdingsdemo.ui

import com.prathamesh.stockholdingsdemo.domain.model.PortfolioSummaryDomainModel
import com.prathamesh.stockholdingsdemo.ui.commons.mapper.PortfolioSummaryDomainToUiMapper
import com.prathamesh.stockholdingsdemo.ui.commons.mapper.PortfolioSummaryDomainToUiMapperImpl
import com.prathamesh.stockholdingsdemo.ui.commons.usecase.ConvertToMoneyStringUseCase
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class PortfolioSummaryDomainToUiMapperImplTest {

    private lateinit var convertToMoneyStringUseCase: ConvertToMoneyStringUseCase
    private lateinit var mapper: PortfolioSummaryDomainToUiMapper

    @Before
    fun setUp() {
        convertToMoneyStringUseCase = mock(ConvertToMoneyStringUseCase::class.java)
        mapper = PortfolioSummaryDomainToUiMapperImpl(convertToMoneyStringUseCase)
    }

    @Test
    fun `map should convert domain model to ui model with formatted values`() {
        // Arrange
        val domain = PortfolioSummaryDomainModel(
            currentValue = 50000.0,
            totalInvestment = 45000.0,
            totalPNL = 5000.0,
            todaysPNL = 200.0
        )

        `when`(convertToMoneyStringUseCase.invoke(50000.0)).thenReturn("₹50,000.00")
        `when`(convertToMoneyStringUseCase.invoke(45000.0)).thenReturn("₹45,000.00")
        `when`(convertToMoneyStringUseCase.invoke(5000.0)).thenReturn("₹5,000.00")
        `when`(convertToMoneyStringUseCase.invoke(200.0)).thenReturn("₹200.00")

        // Act
        val result = mapper.map(domain)

        // Assert
        assertEquals("₹50,000.00", result.currentValue)
        assertEquals("₹45,000.00", result.totalInvestment)
        assertEquals("₹5,000.00", result.totalPNL)
        assertEquals("₹200.00", result.todaysPNL)

        verify(convertToMoneyStringUseCase).invoke(50000.0)
        verify(convertToMoneyStringUseCase).invoke(45000.0)
        verify(convertToMoneyStringUseCase).invoke(5000.0)
        verify(convertToMoneyStringUseCase).invoke(200.0)
    }
}
package com.prathamesh.stockholdingsdemo.domain

import com.prathamesh.stockholdingsdemo.domain.model.HoldingDomainModel
import com.prathamesh.stockholdingsdemo.domain.usecase.CalculatePortfolioSummaryUseCaseImpl
import org.junit.Assert.assertEquals
import org.junit.Test

class CalculatePortfolioSummaryUseCaseImplTest {

    private val useCase = CalculatePortfolioSummaryUseCaseImpl()

    @Test
    fun `invoke should correctly calculate portfolio summary`() {
        // Arrange
        val holdings = listOf(
            HoldingDomainModel("TCS", 10.0, 3000.0, 3050.0, 3100.0, 1000.0),
            HoldingDomainModel("INFY", 5.0, 1500.0, 1480.0, 1490.0, -50.0)
        )

        val expectedCurrentValue = (3100.0 * 10) + (1490.0 * 5)       // 31000 + 7450 = 38450
        val expectedInvestment = (3000.0 * 10) + (1500.0 * 5)         // 30000 + 7500 = 37500
        val expectedTotalPNL = expectedCurrentValue - expectedInvestment // 950
        val expectedTodaysPNL = ((3100.0 - 3050.0) * 10) + ((1490.0 - 1480.0) * 5) // 500 + 50 = 550

        // Act
        val result = useCase.invoke(holdings)

        // Assert
        assertEquals(expectedCurrentValue, result.currentValue, 0.001)
        assertEquals(expectedInvestment, result.totalInvestment, 0.001)
        assertEquals(expectedTotalPNL, result.totalPNL, 0.001)
        assertEquals(expectedTodaysPNL, result.todaysPNL, 0.001)
    }

    @Test
    fun `invoke should return zeros for empty holdings`() {
        // Act
        val result = useCase.invoke(emptyList())

        // Assert
        assertEquals(0.0, result.currentValue, 0.001)
        assertEquals(0.0, result.totalInvestment, 0.001)
        assertEquals(0.0, result.totalPNL, 0.001)
        assertEquals(0.0, result.todaysPNL, 0.001)
    }
}
package com.prathamesh.stockholdingsdemo.ui

import com.prathamesh.stockholdingsdemo.domain.model.HoldingDomainModel
import com.prathamesh.stockholdingsdemo.ui.commons.mapper.HoldingDomainToUiMapper
import com.prathamesh.stockholdingsdemo.ui.commons.mapper.HoldingDomainToUiMapperImpl
import com.prathamesh.stockholdingsdemo.ui.commons.usecase.ConvertToMoneyStringUseCase
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class HoldingDomainToUiMapperImplTest {

    private lateinit var convertToMoneyStringUseCase: ConvertToMoneyStringUseCase
    private lateinit var mapper: HoldingDomainToUiMapper

    @Before
    fun setUp() {
        convertToMoneyStringUseCase = mock(ConvertToMoneyStringUseCase::class.java)
        mapper = HoldingDomainToUiMapperImpl(convertToMoneyStringUseCase)
    }

    @Test
    fun `map should convert domain model to ui model with formatted values`() {
        // Arrange
        val domain = HoldingDomainModel(
            symbol = "TCS",
            quantity = 10.0,
            avgPrice = 3000.0,
            close = 3050.0,
            ltp = 3100.0,
            pnl = 1000.0
        )

        `when`(convertToMoneyStringUseCase.invoke(3100.0)).thenReturn("₹3,100.00")
        `when`(convertToMoneyStringUseCase.invoke(1000.0)).thenReturn("₹1,000.00")

        // Act
        val result = mapper.map(domain)

        // Assert
        assertEquals("TCS", result.symbol)
        assertEquals("10.0", result.quantity)
        assertEquals("₹3,100.00", result.ltp)
        assertEquals("₹1,000.00", result.pnl)

        verify(convertToMoneyStringUseCase).invoke(3100.0)
        verify(convertToMoneyStringUseCase).invoke(1000.0)
    }
}
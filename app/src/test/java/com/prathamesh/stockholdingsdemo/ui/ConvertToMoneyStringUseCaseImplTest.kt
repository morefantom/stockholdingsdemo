package com.prathamesh.stockholdingsdemo.ui

import com.prathamesh.stockholdingsdemo.ui.commons.usecase.ConvertToMoneyStringUseCaseImpl
import org.junit.Assert.assertEquals
import org.junit.Test

class ConvertToMoneyStringUseCaseImplTest {

    private val useCase = ConvertToMoneyStringUseCaseImpl()

    @Test
    fun `invoke should format negative amount correctly with minus and currency symbol`() {
        val result = useCase.invoke(-98765.432)
        assertEquals("-₹98,765.43", result)
    }

    @Test
    fun `invoke should format zero correctly`() {
        val result = useCase.invoke(0.0)
        assertEquals("₹0.00", result)
    }

    @Test
    fun `invoke should handle small decimal values correctly`() {
        val result = useCase.invoke(12.1)
        assertEquals("₹12.10", result)
    }
}
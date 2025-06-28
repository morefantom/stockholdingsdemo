package com.prathamesh.stockholdingsdemo.ui

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.prathamesh.stockholdingsdemo.ui.commons.usecase.ConvertToMoneyStringUseCaseImpl
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ConvertToMoneyStringUseCaseImplTest {
    private val useCase = ConvertToMoneyStringUseCaseImpl()

    @Test
    fun testInvokeShouldFormatPositiveAmountCorrectly() {
        val result = useCase.invoke(123456.789)
        assertEquals("₹1,23,456.79", result)
    }

    @Test
    fun testInvokeShouldHandleLargeValuesWithProperCommas() {
        val result = useCase.invoke(123456789.0)
        assertEquals("₹12,34,56,789.00", result)
    }
}
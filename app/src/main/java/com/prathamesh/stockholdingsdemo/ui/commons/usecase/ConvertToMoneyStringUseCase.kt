package com.prathamesh.stockholdingsdemo.ui.commons.usecase

import java.text.NumberFormat
import java.util.Locale
import kotlin.math.abs

interface ConvertToMoneyStringUseCase {
    fun invoke(amount: Double): String
}

class ConvertToMoneyStringUseCaseImpl : ConvertToMoneyStringUseCase {
    override fun invoke(amount: Double): String {
        val formatter = NumberFormat.getCurrencyInstance(Locale("en", "IN"))
        formatter.minimumFractionDigits = 2
        formatter.maximumFractionDigits = 2

        val absAmount = abs(amount)
        val formatted = formatter.format(absAmount)

        return if (amount < 0) "-$formatted" else formatted
    }
}
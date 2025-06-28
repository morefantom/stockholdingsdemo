package com.prathamesh.stockholdingsdemo.ui.commons.usecase

import java.text.NumberFormat
import java.util.Locale

interface ConvertToMoneyStringUseCase {
    fun invoke(amount: Double): String
}

class ConvertToMoneyStringUseCaseImpl : ConvertToMoneyStringUseCase {
    override fun invoke(amount: Double): String {
        val formatter = NumberFormat.getNumberInstance(Locale("en", "IN"))
        formatter.minimumFractionDigits = 2
        formatter.maximumFractionDigits = 2

        val absAmount = kotlin.math.abs(amount)
        val formatted = formatter.format(absAmount)

        return if (amount < 0) "-₹$formatted" else "₹$formatted"
    }
}
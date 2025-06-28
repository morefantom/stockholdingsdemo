package com.prathamesh.stockholdingsdemo.domain.usecase

import com.prathamesh.stockholdingsdemo.domain.model.HoldingDomainModel
import com.prathamesh.stockholdingsdemo.domain.model.PortfolioSummaryDomainModel

interface CalculatePortfolioSummaryUseCase {
    fun invoke(holdings: List<HoldingDomainModel>): PortfolioSummaryDomainModel
}

class CalculatePortfolioSummaryUseCaseImpl: CalculatePortfolioSummaryUseCase {
    override fun invoke(holdings: List<HoldingDomainModel>): PortfolioSummaryDomainModel {
        var currentValue = 0.0
        var totalInvestment = 0.0
        var todaysPNL = 0.0

        for (h in holdings) {
            currentValue += h.ltp * h.quantity
            totalInvestment += h.avgPrice * h.quantity
            todaysPNL += (h.ltp - h.close) * h.quantity
        }

        return PortfolioSummaryDomainModel(
            currentValue = currentValue,
            totalInvestment = totalInvestment,
            totalPNL = currentValue - totalInvestment,
            todaysPNL = todaysPNL
        )
    }
}
package com.prathamesh.stockholdingsdemo.ui.commons.mapper

import com.prathamesh.stockholdingsdemo.domain.model.PortfolioSummaryDomainModel
import com.prathamesh.stockholdingsdemo.ui.commons.usecase.ConvertToMoneyStringUseCase
import com.prathamesh.stockholdingsdemo.ui.portfolio.model.PortfolioSummaryUiModel

interface PortfolioSummaryDomainToUiMapper {
    fun map(domain: PortfolioSummaryDomainModel): PortfolioSummaryUiModel
}

class PortfolioSummaryDomainToUiMapperImpl(
    private val convertToMoneyStringUseCase: ConvertToMoneyStringUseCase,
): PortfolioSummaryDomainToUiMapper {
    override fun map(domain: PortfolioSummaryDomainModel): PortfolioSummaryUiModel {
        return PortfolioSummaryUiModel(
            currentValue = convertToMoneyStringUseCase.invoke(domain.currentValue),
            totalInvestment = convertToMoneyStringUseCase.invoke(domain.totalInvestment),
            totalPNL = convertToMoneyStringUseCase.invoke(domain.totalPNL),
            todaysPNL = convertToMoneyStringUseCase.invoke(domain.todaysPNL),
        )
    }
}
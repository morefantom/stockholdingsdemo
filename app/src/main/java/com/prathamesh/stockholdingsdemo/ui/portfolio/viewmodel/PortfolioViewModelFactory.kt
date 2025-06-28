package com.prathamesh.stockholdingsdemo.ui.portfolio.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.prathamesh.stockholdingsdemo.domain.usecase.CalculatePortfolioSummaryUseCase
import com.prathamesh.stockholdingsdemo.domain.usecase.FetchHoldingsUseCase
import com.prathamesh.stockholdingsdemo.ui.commons.mapper.HoldingDomainToUiMapper
import com.prathamesh.stockholdingsdemo.ui.commons.mapper.PortfolioSummaryDomainToUiMapper

@Suppress("UNCHECKED_CAST")
class PortfolioViewModelFactory(
    private val fetchHoldingsUseCase: FetchHoldingsUseCase,
    private val holdingDomainToUiMapper: HoldingDomainToUiMapper,
    private val calculatePortfolioSummaryUseCase: CalculatePortfolioSummaryUseCase,
    private val portfolioSummaryDomainToUiMapper: PortfolioSummaryDomainToUiMapper,
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(PortfolioViewModel::class.java)) {
            PortfolioViewModel(
                fetchHoldingsUseCase = fetchHoldingsUseCase,
                holdingDomainToUiMapper = holdingDomainToUiMapper,
                calculatePortfolioSummaryUseCase = calculatePortfolioSummaryUseCase,
                portfolioSummaryDomainToUiMapper = portfolioSummaryDomainToUiMapper,
            ) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}
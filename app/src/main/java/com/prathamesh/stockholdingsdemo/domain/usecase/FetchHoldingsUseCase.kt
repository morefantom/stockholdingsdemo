package com.prathamesh.stockholdingsdemo.domain.usecase

import com.prathamesh.stockholdingsdemo.domain.model.HoldingDomainModel
import com.prathamesh.stockholdingsdemo.domain.repository.PortfolioRepository
import kotlinx.coroutines.flow.Flow

interface FetchHoldingsUseCase {
    fun invoke(): Flow<List<HoldingDomainModel>>
}

class FetchHoldingsUseCaseImpl(
    private val portfolioRepository: PortfolioRepository,
) : FetchHoldingsUseCase {
    override fun invoke(): Flow<List<HoldingDomainModel>> = portfolioRepository
        .getHoldings()
}
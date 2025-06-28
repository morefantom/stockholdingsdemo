package com.prathamesh.stockholdingsdemo.domain.repository

import com.prathamesh.stockholdingsdemo.data.remote.source.PortfolioService
import com.prathamesh.stockholdingsdemo.domain.mapper.HoldingDataToDomainMapper
import com.prathamesh.stockholdingsdemo.domain.model.HoldingDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface PortfolioRepository {
    fun getHoldings(): Flow<List<HoldingDomainModel>>
}

class PortfolioRepositoryImpl(
    private val portfolioService: PortfolioService,
    private val holdingDataToDomainMapper: HoldingDataToDomainMapper,
): PortfolioRepository {
    override fun getHoldings(): Flow<List<HoldingDomainModel>> = flow {
        val result = portfolioService
            .getPortfolio()
            .data
            .userHolding
            .map { holdingDataToDomainMapper.map(it) }
        emit(result)
    }.flowOn(Dispatchers.IO)
}
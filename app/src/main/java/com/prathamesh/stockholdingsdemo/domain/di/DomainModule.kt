package com.prathamesh.stockholdingsdemo.domain.di

import com.prathamesh.stockholdingsdemo.data.remote.source.PortfolioService
import com.prathamesh.stockholdingsdemo.domain.mapper.HoldingDataToDomainMapper
import com.prathamesh.stockholdingsdemo.domain.mapper.HoldingDataToDomainMapperImpl
import com.prathamesh.stockholdingsdemo.domain.repository.PortfolioRepository
import com.prathamesh.stockholdingsdemo.domain.repository.PortfolioRepositoryImpl
import com.prathamesh.stockholdingsdemo.domain.usecase.CalculatePortfolioSummaryUseCase
import com.prathamesh.stockholdingsdemo.domain.usecase.CalculatePortfolioSummaryUseCaseImpl
import com.prathamesh.stockholdingsdemo.domain.usecase.FetchHoldingsUseCase
import com.prathamesh.stockholdingsdemo.domain.usecase.FetchHoldingsUseCaseImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {

    @Singleton
    @Provides
    fun provideHoldingDataToDomainMapper()
    : HoldingDataToDomainMapper = HoldingDataToDomainMapperImpl()

    @Singleton
    @Provides
    fun providePortfolioRepository(
        portfolioService: PortfolioService,
        holdingDataToDomainMapper: HoldingDataToDomainMapper,
    ): PortfolioRepository = PortfolioRepositoryImpl(
        portfolioService = portfolioService,
        holdingDataToDomainMapper = holdingDataToDomainMapper,
    )

    @Singleton
    @Provides
    fun provideFetchHoldingsUseCase(
        portfolioRepository: PortfolioRepository,
    ): FetchHoldingsUseCase = FetchHoldingsUseCaseImpl(portfolioRepository)

    @Singleton
    @Provides
    fun provideCalculatePortfolioSummaryUseCase()
    : CalculatePortfolioSummaryUseCase = CalculatePortfolioSummaryUseCaseImpl()
}
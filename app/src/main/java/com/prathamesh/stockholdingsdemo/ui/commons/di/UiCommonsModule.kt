package com.prathamesh.stockholdingsdemo.ui.commons.di

import com.prathamesh.stockholdingsdemo.ui.commons.mapper.HoldingDomainToUiMapper
import com.prathamesh.stockholdingsdemo.ui.commons.mapper.HoldingDomainToUiMapperImpl
import com.prathamesh.stockholdingsdemo.ui.commons.mapper.PortfolioSummaryDomainToUiMapper
import com.prathamesh.stockholdingsdemo.ui.commons.mapper.PortfolioSummaryDomainToUiMapperImpl
import com.prathamesh.stockholdingsdemo.ui.commons.usecase.ConvertToMoneyStringUseCase
import com.prathamesh.stockholdingsdemo.ui.commons.usecase.ConvertToMoneyStringUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
class UiCommonsModule {

    @Provides
    fun provideConvertToMoneyString(): ConvertToMoneyStringUseCase =
        ConvertToMoneyStringUseCaseImpl()

    @Provides
    fun provideConvertHoldingDomainToUiUseCase(
        convertToMoneyStringUseCase: ConvertToMoneyStringUseCase
    ): HoldingDomainToUiMapper = HoldingDomainToUiMapperImpl(
        convertToMoneyStringUseCase = convertToMoneyStringUseCase,
    )

    @Provides
    fun provideConvertPortfolioSummaryDomainToUiUseCase(
        convertToMoneyStringUseCase: ConvertToMoneyStringUseCase
    ): PortfolioSummaryDomainToUiMapper = PortfolioSummaryDomainToUiMapperImpl(
        convertToMoneyStringUseCase = convertToMoneyStringUseCase,
    )
}
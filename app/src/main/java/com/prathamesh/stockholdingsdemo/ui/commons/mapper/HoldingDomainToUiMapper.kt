package com.prathamesh.stockholdingsdemo.ui.commons.mapper

import com.prathamesh.stockholdingsdemo.domain.model.HoldingDomainModel
import com.prathamesh.stockholdingsdemo.ui.commons.usecase.ConvertToMoneyStringUseCase
import com.prathamesh.stockholdingsdemo.ui.portfolio.model.HoldingUiModel

interface HoldingDomainToUiMapper {
    fun map(domainHolding: HoldingDomainModel): HoldingUiModel
}

class HoldingDomainToUiMapperImpl(
    private val convertToMoneyStringUseCase: ConvertToMoneyStringUseCase,
): HoldingDomainToUiMapper {
    override fun map(
        domainHolding: HoldingDomainModel
    ): HoldingUiModel {
        return with(domainHolding) {
            HoldingUiModel(
                symbol = symbol,
                quantity = quantity.toString(),
                ltp = convertToMoneyStringUseCase.invoke(ltp),
                pnl = convertToMoneyStringUseCase.invoke(pnl),
            )
        }
    }
}
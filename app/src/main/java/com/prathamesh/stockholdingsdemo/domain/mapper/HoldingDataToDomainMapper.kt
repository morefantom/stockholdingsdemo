package com.prathamesh.stockholdingsdemo.domain.mapper

import com.prathamesh.stockholdingsdemo.data.remote.model.HoldingDataModel
import com.prathamesh.stockholdingsdemo.domain.model.HoldingDomainModel

interface HoldingDataToDomainMapper {
    fun map(data: HoldingDataModel): HoldingDomainModel
}

class HoldingDataToDomainMapperImpl: HoldingDataToDomainMapper {
    override fun map(data: HoldingDataModel): HoldingDomainModel {
        return with(data) {
             HoldingDomainModel(
                 symbol = symbol,
                 quantity = quantity,
                 avgPrice = avgPrice,
                 close = close,
                 ltp = ltp,
                 pnl = (ltp * quantity) - (avgPrice * quantity),
             )
        }
    }
}
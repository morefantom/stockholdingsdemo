package com.prathamesh.stockholdingsdemo.domain.model

data class HoldingDomainModel (
    val symbol: String = "",
    val quantity: Double = 0.0,
    val avgPrice: Double = 0.0,
    val close: Double = 0.0,
    val ltp: Double = 0.0,
    val pnl: Double = 0.0,
)
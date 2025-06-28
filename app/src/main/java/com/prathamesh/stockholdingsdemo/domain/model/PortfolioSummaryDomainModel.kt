package com.prathamesh.stockholdingsdemo.domain.model

data class PortfolioSummaryDomainModel(
    val currentValue: Double,
    val totalInvestment: Double,
    val totalPNL: Double,
    val todaysPNL: Double
)
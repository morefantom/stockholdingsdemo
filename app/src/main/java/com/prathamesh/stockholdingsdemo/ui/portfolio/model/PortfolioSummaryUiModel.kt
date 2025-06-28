package com.prathamesh.stockholdingsdemo.ui.portfolio.model

data class PortfolioSummaryUiModel(
    val currentValue: String,
    val totalInvestment: String,
    val totalPNL: String,
    val todaysPNL: String
)
package com.prathamesh.stockholdingsdemo.ui.portfolio.intent

import com.prathamesh.stockholdingsdemo.ui.portfolio.model.HoldingUiModel

sealed class PortfolioAction {
    object ClickExpandCollapse: PortfolioAction()
    data class ClickHolding(
        val holding: HoldingUiModel
    ): PortfolioAction()
    data class ClickTab(
        val tabClicked: PortfolioTab
    ): PortfolioAction()
}
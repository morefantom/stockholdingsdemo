package com.prathamesh.stockholdingsdemo.ui.portfolio.intent

import androidx.annotation.StringRes
import com.prathamesh.stockholdingsdemo.R
import com.prathamesh.stockholdingsdemo.ui.portfolio.model.HoldingUiModel

enum class PortfolioTab(
    @StringRes val title: Int,
) {
    POSITIONS(title = R.string.portfolio_positions_tab_pager_title,),
    HOLDINGS(title = R.string.portfolio_holdings_tab_pager_title,),
}

sealed class PortfolioState {
    object Loading: PortfolioState()
    sealed class Loaded(
        val tabState: PortfolioTab,
    ): PortfolioState() {
        object Positions: Loaded(tabState = PortfolioTab.POSITIONS)
        data class Holdings(
            val holdings: List<HoldingUiModel>,
        ): Loaded(tabState = PortfolioTab.HOLDINGS)
    }

    val tabList = PortfolioTab.entries.toList()
}

sealed class PortfolioSummaryState {
    data class Collapse(
        val totalPNL: String = "",
    ): PortfolioSummaryState()
    data class Expand(
        val currentValue: String = "",
        val totalInvestment: String = "",
        val todayPNL: String = "",
        val totalPNL: String = "",
    ): PortfolioSummaryState()
    object Loading: PortfolioSummaryState()
}
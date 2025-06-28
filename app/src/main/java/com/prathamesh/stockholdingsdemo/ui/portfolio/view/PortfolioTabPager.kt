package com.prathamesh.stockholdingsdemo.ui.portfolio.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.prathamesh.stockholdingsdemo.ui.portfolio.intent.PortfolioAction
import com.prathamesh.stockholdingsdemo.ui.portfolio.intent.PortfolioState
import com.prathamesh.stockholdingsdemo.ui.portfolio.intent.PortfolioTab
import com.prathamesh.stockholdingsdemo.ui.portfolio.model.HoldingUiModel

@Composable
fun PortfolioTabPager(
    state: PortfolioState.Loaded,
    modifier: Modifier = Modifier,
    onAction: (PortfolioAction) -> Unit
) {
    val pagerState = rememberPagerState(
        initialPage = state.tabState.ordinal
    ) { state.tabList.size }

    LaunchedEffect(state.tabState) {
        pagerState.animateScrollToPage(state.tabState.ordinal)
    }

    Column(
        modifier = modifier,
    ) {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
        ) {
            state.tabList.forEachIndexed { index, tab ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = { onAction(PortfolioAction.ClickTab(tab)) },
                    text = { Text(stringResource(tab.title)) },
                )
            }
        }

        HorizontalPager(
            state = pagerState,
        ) { pageIndex ->
            val currentTab = state.tabList[pageIndex]
            when (currentTab) {
                PortfolioTab.POSITIONS -> Text(stringResource(currentTab.title))
                PortfolioTab.HOLDINGS -> (state as? PortfolioState.Loaded.Holdings)
                    ?.let {
                        HoldingScreen(
                            holdingList = state.holdings,
                            onClickHolding = { onAction(PortfolioAction.ClickHolding(it)) }
                        )
                    }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PortfolioTabPagerPreviewPositions() {
    PortfolioTabPager(
        state = PortfolioState.Loaded.Positions,
        modifier = Modifier
            .fillMaxSize(),
    ) {}
}

@Preview(showBackground = true)
@Composable
fun PortfolioTabPagerPreviewHoldings() {
    PortfolioTabPager(
        state = PortfolioState.Loaded.Holdings(
            holdings = listOf(
                HoldingUiModel(
                    symbol = "AAPL",
                    quantity = "₹10.0",
                    ltp = "₹100.0",
                    pnl = "₹10.0",
                ),
                HoldingUiModel(
                    symbol = "AAPL",
                    quantity = "₹10.0",
                    ltp = "₹100.0",
                    pnl = "₹10.0",
                ),
                HoldingUiModel(
                    symbol = "AAPL",
                    quantity = "₹10.0",
                    ltp = "₹100.0",
                    pnl = "₹10.0",
                ),
                HoldingUiModel(
                    symbol = "AAPL",
                    quantity = "₹10.0",
                    ltp = "₹100.0",
                    pnl = "₹10.0",
                ),
                HoldingUiModel(
                    symbol = "AAPL",
                    quantity = "₹10.0",
                    ltp = "₹100.0",
                    pnl = "₹10.0",
                ),
                HoldingUiModel(
                    symbol = "AAPL",
                    quantity = "₹10.0",
                    ltp = "₹100.0",
                    pnl = "₹10.0",
                ),
            )
        ),
        modifier = Modifier
            .fillMaxSize(),
    ) {}
}
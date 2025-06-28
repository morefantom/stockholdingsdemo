package com.prathamesh.stockholdingsdemo.ui.portfolio.view

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.prathamesh.stockholdingsdemo.ui.portfolio.intent.PortfolioSummaryState

@Composable
fun PortfolioSummary(
    state: PortfolioSummaryState,
    modifier: Modifier = Modifier,
    onClickExpandCollapse: () -> Unit,
) {
    Card(
        modifier = modifier
            .clickable { onClickExpandCollapse() }
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = FastOutSlowInEasing
                )
            ),
    ) {
        when (state) {
            is PortfolioSummaryState.Collapse -> PortfolioCommonCollapsed(
                totalPNL = state.totalPNL,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
            )
            is PortfolioSummaryState.Expand -> PortfolioCommonExpanded(
                currentValue = state.currentValue,
                totalInvestment = state.totalInvestment,
                todayPNL = state.todayPNL,
                totalPNL = state.totalPNL,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
            )
            PortfolioSummaryState.Loading -> PortfolioCommonLoading()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PortfolioSummaryPreviewLoading() {
    PortfolioSummary(
        state = PortfolioSummaryState.Loading,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        onClickExpandCollapse = { },
    )
}

@Preview(showBackground = true)
@Composable
fun PortfolioSummaryPreviewCollapse() {
    PortfolioSummary(
        state = PortfolioSummaryState.Collapse(
            totalPNL = "-₹10,000.00",
        ),
        onClickExpandCollapse = { },
    )
}

@Preview(showBackground = true)
@Composable
fun PortfolioSummaryPreviewExpand() {
    PortfolioSummary(
        state = PortfolioSummaryState.Expand(
            currentValue = "₹27,893.65",
            totalInvestment = "₹28,590.71",
            todayPNL = "-₹235.65",
            totalPNL = "-₹697.06",
        ),
        onClickExpandCollapse = { },
    )
}
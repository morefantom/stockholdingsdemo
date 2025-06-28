package com.prathamesh.stockholdingsdemo.ui.portfolio.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.prathamesh.stockholdingsdemo.R

@Composable
fun PortfolioCommonExpanded(
    currentValue: String,
    totalInvestment: String,
    todayPNL: String,
    totalPNL: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        PortfolioCommonNonClickRow(
            labelRes = R.string.portfolio_common_current_value_label,
            value = currentValue,
            modifier = modifier
                .fillMaxWidth(),
        )

        PortfolioCommonNonClickRow(
            labelRes = R.string.portfolio_common_total_investment_label,
            value = totalInvestment,
            modifier = modifier
                .fillMaxWidth(),
        )

        PortfolioCommonNonClickRow(
            labelRes = R.string.portfolio_common_today_profit_and_loss_value_label,
            value = todayPNL,
            modifier = modifier
                .fillMaxWidth(),
        )

        HorizontalDivider()

        PortfolioCommonClickRow(
            labelRes = R.string.portfolio_common_profit_and_loss_label,
            value = totalPNL,
            isClicked = true,
            modifier = modifier
                .fillMaxWidth(),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PortfolioCommonExpandedPreview() {
    PortfolioCommonExpanded(
        currentValue = "₹27,893.65",
        totalInvestment = "₹28,590.71",
        todayPNL = "-₹235.65",
        totalPNL = "-₹697.06",
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    )
}
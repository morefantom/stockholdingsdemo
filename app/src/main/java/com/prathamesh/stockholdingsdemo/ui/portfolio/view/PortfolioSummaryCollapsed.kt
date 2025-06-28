package com.prathamesh.stockholdingsdemo.ui.portfolio.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.prathamesh.stockholdingsdemo.R

@Composable
fun PortfolioCommonCollapsed(
    totalPNL: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        PortfolioCommonClickRow(
            labelRes = R.string.portfolio_common_profit_and_loss_label,
            value = totalPNL,
            isClicked = false,
            modifier = modifier
                .fillMaxWidth(),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PortfolioCommonCollapsedPreview() {
    PortfolioCommonCollapsed(
        totalPNL = "-₹10,000.00",
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    )
}
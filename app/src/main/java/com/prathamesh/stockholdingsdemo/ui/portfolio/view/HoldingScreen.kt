package com.prathamesh.stockholdingsdemo.ui.portfolio.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.prathamesh.stockholdingsdemo.ui.portfolio.model.HoldingUiModel

@Composable
fun HoldingScreen(
    holdingList: List<HoldingUiModel>,
    modifier: Modifier = Modifier,
    onClickHolding: (HoldingUiModel) -> Unit
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(count = holdingList.size) { index ->
            HoldingRow(
                holdingModel = holdingList[index],
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable { onClickHolding(holdingList[index]) },
            )
            HorizontalDivider()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HoldingScreenPreview() {
    HoldingScreen(
        holdingList = listOf(
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
        ),
        modifier = Modifier
            .fillMaxWidth(),
    ){}
}
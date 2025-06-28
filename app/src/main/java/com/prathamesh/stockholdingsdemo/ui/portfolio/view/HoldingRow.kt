package com.prathamesh.stockholdingsdemo.ui.portfolio.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.prathamesh.stockholdingsdemo.R
import com.prathamesh.stockholdingsdemo.ui.portfolio.model.HoldingUiModel

@Composable
fun HoldingRow(
    holdingModel: HoldingUiModel,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(holdingModel.symbol)
            Spacer(
                modifier = Modifier
                    .height(8.dp),
            )
            Row {
                Text(stringResource(R.string.portfolio_holdings_quantity_label))
                Text(holdingModel.quantity)
            }
        }
        Column {
            Row {
                Text(stringResource(R.string.portfolio_holdings_ltp_label))
                Text(holdingModel.ltp)
            }
            Spacer(
                modifier = Modifier
                    .height(8.dp),
            )
            Row {
                Text(stringResource(R.string.portfolio_holdings_pnl_label))
                Text(holdingModel.pnl)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HoldingsRowPreview() {
    HoldingRow(
        holdingModel = HoldingUiModel(
            symbol = "AAPL",
            quantity = "₹10.0",
            ltp = "₹100.0",
            pnl = "₹10.0",
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    )
}
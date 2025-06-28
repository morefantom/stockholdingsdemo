package com.prathamesh.stockholdingsdemo.ui.portfolio.view

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.prathamesh.stockholdingsdemo.R

@Composable
fun PortfolioCommonClickRow(
    @StringRes labelRes: Int,
    value: String,
    isClicked: Boolean,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(stringResource(labelRes) + " " + if (isClicked) "▼" else "▲")
        Text(value)
    }
}

@Preview(showBackground = true)
@Composable
fun PortfolioCommonClickRowPreviewClicked() {
    PortfolioCommonClickRow(
        labelRes = R.string.portfolio_common_current_value_label,
        value = "-₹10,000.00",
        isClicked = true,
        modifier = Modifier
            .fillMaxWidth(),
    )
}

@Preview(showBackground = true)
@Composable
fun PortfolioCommonClickRowPreviewUnClicked() {
    PortfolioCommonClickRow(
        labelRes = R.string.portfolio_common_current_value_label,
        value = "-₹10,000.00",
        isClicked = false,
        modifier = Modifier
            .fillMaxWidth(),
    )
}
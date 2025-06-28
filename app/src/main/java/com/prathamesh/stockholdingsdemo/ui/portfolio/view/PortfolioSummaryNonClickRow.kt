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
fun PortfolioCommonNonClickRow(
    @StringRes labelRes: Int,
    value: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(stringResource(labelRes))
        Text(value)
    }
}

@Preview(showBackground = true)
@Composable
fun PortfolioCommonNonClickRowPreview() {
    PortfolioCommonNonClickRow(
        labelRes = R.string.portfolio_common_current_value_label,
        value = "-₹10,000.00",
        modifier = Modifier
            .fillMaxWidth(),
    )
}
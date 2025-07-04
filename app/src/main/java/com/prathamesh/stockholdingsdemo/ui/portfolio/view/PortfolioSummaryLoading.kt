package com.prathamesh.stockholdingsdemo.ui.portfolio.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PortfolioCommonLoading(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Spacer(
            modifier = Modifier
                .height(8.dp),
        )
        CircularProgressIndicator()
        Spacer(
            modifier = Modifier
                .height(8.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PortfolioCommonLoadingPreview() {
    PortfolioCommonLoading()
}
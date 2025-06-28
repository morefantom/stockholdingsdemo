package com.prathamesh.stockholdingsdemo.ui.portfolio.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.prathamesh.stockholdingsdemo.domain.usecase.CalculatePortfolioSummaryUseCase
import com.prathamesh.stockholdingsdemo.domain.usecase.FetchHoldingsUseCase
import com.prathamesh.stockholdingsdemo.ui.commons.mapper.HoldingDomainToUiMapper
import com.prathamesh.stockholdingsdemo.ui.commons.mapper.PortfolioSummaryDomainToUiMapper
import com.prathamesh.stockholdingsdemo.ui.portfolio.intent.PortfolioAction
import com.prathamesh.stockholdingsdemo.ui.portfolio.intent.PortfolioState
import com.prathamesh.stockholdingsdemo.ui.portfolio.viewmodel.PortfolioViewModel
import com.prathamesh.stockholdingsdemo.ui.portfolio.viewmodel.PortfolioViewModelFactory

@Composable
fun PortfolioRoute(
    fetchHoldingsUseCase: FetchHoldingsUseCase,
    holdingDomainToUiMapper: HoldingDomainToUiMapper,
    calculatePortfolioSummaryUseCase: CalculatePortfolioSummaryUseCase,
    portfolioSummaryDomainToUiMapper: PortfolioSummaryDomainToUiMapper,
    modifier: Modifier = Modifier,
) {

    val viewModel = viewModel<PortfolioViewModel>(
        factory = PortfolioViewModelFactory(
            fetchHoldingsUseCase = fetchHoldingsUseCase,
            holdingDomainToUiMapper = holdingDomainToUiMapper,
            calculatePortfolioSummaryUseCase = calculatePortfolioSummaryUseCase,
            portfolioSummaryDomainToUiMapper = portfolioSummaryDomainToUiMapper,
        )
    )

    val state = viewModel.state.collectAsState()
    val summaryState = viewModel.summaryState.collectAsState()

    Column(
        modifier = modifier,
    ) {
        (state.value as? PortfolioState.Loaded)?.let {
            PortfolioTabPager(
                state = it,
                modifier = Modifier.weight(1f),
                onAction = { viewModel.onAction(it) }
            )
        } ?: PortfolioLoading(modifier = Modifier.weight(1f))

        PortfolioSummary(
            state = summaryState.value,
            modifier = Modifier.fillMaxWidth(),
            onClickExpandCollapse = { viewModel.onAction(PortfolioAction.ClickExpandCollapse) },
        )
    }
}
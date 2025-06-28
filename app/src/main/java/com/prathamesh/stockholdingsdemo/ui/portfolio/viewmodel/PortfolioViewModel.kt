package com.prathamesh.stockholdingsdemo.ui.portfolio.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prathamesh.stockholdingsdemo.domain.usecase.CalculatePortfolioSummaryUseCase
import com.prathamesh.stockholdingsdemo.domain.usecase.FetchHoldingsUseCase
import com.prathamesh.stockholdingsdemo.ui.commons.mapper.HoldingDomainToUiMapper
import com.prathamesh.stockholdingsdemo.ui.commons.mapper.PortfolioSummaryDomainToUiMapper
import com.prathamesh.stockholdingsdemo.ui.portfolio.intent.PortfolioSummaryState
import com.prathamesh.stockholdingsdemo.ui.portfolio.intent.PortfolioSummaryState.*
import com.prathamesh.stockholdingsdemo.ui.portfolio.intent.PortfolioAction
import com.prathamesh.stockholdingsdemo.ui.portfolio.intent.PortfolioState
import com.prathamesh.stockholdingsdemo.ui.portfolio.intent.PortfolioTab
import com.prathamesh.stockholdingsdemo.ui.portfolio.model.HoldingUiModel
import com.prathamesh.stockholdingsdemo.ui.portfolio.model.PortfolioSummaryUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch

class PortfolioViewModel(
    private val fetchHoldingsUseCase: FetchHoldingsUseCase,
    private val holdingDomainToUiMapper: HoldingDomainToUiMapper,
    private val calculatePortfolioSummaryUseCase: CalculatePortfolioSummaryUseCase,
    private val portfolioSummaryDomainToUiMapper: PortfolioSummaryDomainToUiMapper,
): ViewModel() {

    private val _state: MutableStateFlow<PortfolioState> = MutableStateFlow(PortfolioState.Loading)
    val state: StateFlow<PortfolioState> = _state.asStateFlow()

    private val _summaryState: MutableStateFlow<PortfolioSummaryState> = MutableStateFlow(Loading)
    val summaryState: StateFlow<PortfolioSummaryState> = _summaryState.asStateFlow()

    var portfolioSummary: PortfolioSummaryUiModel? = null
    var holdings: List<HoldingUiModel>? = null

    var sharedHoldingsFlow = fetchHoldingsUseCase
        .invoke()
        .catch {  }
        .shareIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            replay = 1
        )

    init {
        loadAndShowHoldings()
        loadAndShowCollapseSummary()
    }

    fun onAction(action: PortfolioAction) {
        when (action) {
            PortfolioAction.ClickExpandCollapse -> expandCollapseSummary()
            is PortfolioAction.ClickHolding -> navigateToHoldingDetails(action.holding)
            is PortfolioAction.ClickTab -> showHoldingsOrPositionsTab(action.tabClicked)
        }
    }

    private fun navigateToHoldingDetails(holding: HoldingUiModel) {}

    private fun showHoldingsOrPositionsTab(tab: PortfolioTab) {
        when (tab) {
            PortfolioTab.POSITIONS -> showPositions()
            PortfolioTab.HOLDINGS -> loadAndShowHoldings()
        }
    }

    private fun loadAndShowHoldings() {
        sharedHoldingsFlow
            .onStart { showLoading() }
            .map { domainHoldings ->
                domainHoldings.map { holdingDomainToUiMapper.map(it) }
            }
            .onEach { holdings = it }
            .onEach { showHoldings() }
            .catch {  }
            .launchIn(viewModelScope)
    }

    private fun showHoldings() {
        holdings?.let {
            _state.value = PortfolioState.Loaded.Holdings(it)
        }
    }

    private fun showPositions() {
        _state.value = PortfolioState.Loaded.Positions
    }

    private fun showLoading() {
        _state.value = PortfolioState.Loading
    }

    private fun loadAndShowCollapseSummary() {
        sharedHoldingsFlow
            .onStart { showLoadingSummary() }
            .map { domainHoldings ->
                calculatePortfolioSummaryUseCase
                    .invoke(domainHoldings)
            }
            .map { portfolioSummaryDomainToUiMapper.map(it) }
            .onEach { portfolioSummary = it }
            .onEach { showCollapseSummary() }
            .catch {  }
            .launchIn(viewModelScope)
    }

    private fun expandCollapseSummary() {
        when (_summaryState.value) {
            is Collapse -> showExpandSummary()
            is Expand -> showCollapseSummary()
            Loading -> showLoadingSummary()
        }
    }

    private fun showExpandSummary() {
        portfolioSummary?.let {
            _summaryState.value = Expand(
                currentValue = it.currentValue,
                totalInvestment = it.totalInvestment,
                todayPNL = it.todaysPNL,
                totalPNL = it.totalPNL,
            )
        }
    }

    private fun showCollapseSummary() {
        portfolioSummary?.let {
            _summaryState.value = Collapse(
                totalPNL = it.totalPNL,
            )
        }
    }

    private fun showLoadingSummary() {
        portfolioSummary?.let {
            _summaryState.value = Loading
        }
    }
}
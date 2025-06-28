package com.prathamesh.stockholdingsdemo.ui

import com.prathamesh.stockholdingsdemo.domain.model.HoldingDomainModel
import com.prathamesh.stockholdingsdemo.domain.model.PortfolioSummaryDomainModel
import com.prathamesh.stockholdingsdemo.domain.usecase.CalculatePortfolioSummaryUseCase
import com.prathamesh.stockholdingsdemo.domain.usecase.FetchHoldingsUseCase
import com.prathamesh.stockholdingsdemo.ui.commons.mapper.HoldingDomainToUiMapper
import com.prathamesh.stockholdingsdemo.ui.commons.mapper.PortfolioSummaryDomainToUiMapper
import com.prathamesh.stockholdingsdemo.ui.portfolio.intent.PortfolioAction
import com.prathamesh.stockholdingsdemo.ui.portfolio.intent.PortfolioState
import com.prathamesh.stockholdingsdemo.ui.portfolio.intent.PortfolioSummaryState
import com.prathamesh.stockholdingsdemo.ui.portfolio.intent.PortfolioTab
import com.prathamesh.stockholdingsdemo.ui.portfolio.model.HoldingUiModel
import com.prathamesh.stockholdingsdemo.ui.portfolio.model.PortfolioSummaryUiModel
import com.prathamesh.stockholdingsdemo.ui.portfolio.viewmodel.PortfolioViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class PortfolioViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    private lateinit var fetchHoldingsUseCase: FetchHoldingsUseCase
    private lateinit var holdingDomainToUiMapper: HoldingDomainToUiMapper
    private lateinit var calculatePortfolioSummaryUseCase: CalculatePortfolioSummaryUseCase
    private lateinit var portfolioSummaryDomainToUiMapper: PortfolioSummaryDomainToUiMapper

    private lateinit var viewModel: PortfolioViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        fetchHoldingsUseCase = mock()
        holdingDomainToUiMapper = mock()
        calculatePortfolioSummaryUseCase = mock()
        portfolioSummaryDomainToUiMapper = mock()

        // Mock response for domain holdings
        val holdings = listOf(
            HoldingDomainModel("TCS", 10.0, 3000.0, 3050.0, 3100.0, 1000.0)
        )
        val summary = PortfolioSummaryDomainModel(31000.0, 30000.0, 1000.0, 500.0)
        val uiHoldings = listOf(
            HoldingUiModel("TCS", "10", "₹3,100.00", "₹1,000.00")
        )
        val uiSummary = PortfolioSummaryUiModel("₹31,000.00", "₹30,000.00", "₹1,000.00", "₹500.00")

        whenever(fetchHoldingsUseCase.invoke()).thenReturn(flowOf(holdings))
        whenever(holdingDomainToUiMapper.map(any())).thenReturn(uiHoldings[0])
        whenever(calculatePortfolioSummaryUseCase.invoke(holdings)).thenReturn(summary)
        whenever(portfolioSummaryDomainToUiMapper.map(summary)).thenReturn(uiSummary)

        viewModel = PortfolioViewModel(
            fetchHoldingsUseCase,
            holdingDomainToUiMapper,
            calculatePortfolioSummaryUseCase,
            portfolioSummaryDomainToUiMapper
        )

        testScope.advanceUntilIdle()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state should load holdings and summary correctly`() = testScope.runTest {
        val state = viewModel.state.value
        val summary = viewModel.summaryState.value

        assertTrue(state is PortfolioState.Loaded.Holdings)
        assertTrue(summary is PortfolioSummaryState.Collapse)

        val holdings = (state as PortfolioState.Loaded.Holdings).holdings
        val summaryData = (summary as PortfolioSummaryState.Collapse)

        assertEquals("₹1,000.00", summaryData.totalPNL)
        assertEquals("TCS", holdings[0].symbol)
    }

    @Test
    fun `ClickTab(HOLDINGS) should reload holdings`() = testScope.runTest {
        viewModel.onAction(PortfolioAction.ClickTab(PortfolioTab.HOLDINGS))
        testScope.advanceUntilIdle()

        val state = viewModel.state.value
        assertTrue(state is PortfolioState.Loaded.Holdings)
    }

    @Test
    fun `ClickTab(POSITIONS) should update state to Positions`() = testScope.runTest {
        viewModel.onAction(PortfolioAction.ClickTab(PortfolioTab.POSITIONS))
        testScope.advanceUntilIdle()

        val state = viewModel.state.value
        assertTrue(state is PortfolioState.Loaded.Positions)
    }

    @Test
    fun `ClickExpandCollapse toggles summary state`() = testScope.runTest {
        // Initial: Collapse
        assertTrue(viewModel.summaryState.value is PortfolioSummaryState.Collapse)

        // Expand
        viewModel.onAction(PortfolioAction.ClickExpandCollapse)
        testScope.advanceUntilIdle()
        assertTrue(viewModel.summaryState.value is PortfolioSummaryState.Expand)

        // Collapse again
        viewModel.onAction(PortfolioAction.ClickExpandCollapse)
        testScope.advanceUntilIdle()
        assertTrue(viewModel.summaryState.value is PortfolioSummaryState.Collapse)
    }
}
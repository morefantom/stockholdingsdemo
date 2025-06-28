package com.prathamesh.stockholdingsdemo.ui

import androidx.lifecycle.ViewModel
import com.prathamesh.stockholdingsdemo.domain.usecase.CalculatePortfolioSummaryUseCase
import com.prathamesh.stockholdingsdemo.domain.usecase.FetchHoldingsUseCase
import com.prathamesh.stockholdingsdemo.ui.commons.mapper.HoldingDomainToUiMapper
import com.prathamesh.stockholdingsdemo.ui.commons.mapper.PortfolioSummaryDomainToUiMapper
import com.prathamesh.stockholdingsdemo.ui.portfolio.viewmodel.PortfolioViewModel
import com.prathamesh.stockholdingsdemo.ui.portfolio.viewmodel.PortfolioViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.*

import org.mockito.Mockito.*

class PortfolioViewModelFactoryTest {

    private val testDispatcher = UnconfinedTestDispatcher()

    private lateinit var fetchHoldingsUseCase: FetchHoldingsUseCase
    private lateinit var holdingDomainToUiMapper: HoldingDomainToUiMapper
    private lateinit var calculatePortfolioSummaryUseCase: CalculatePortfolioSummaryUseCase
    private lateinit var portfolioSummaryDomainToUiMapper: PortfolioSummaryDomainToUiMapper
    private lateinit var factory: PortfolioViewModelFactory

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        fetchHoldingsUseCase = mock(FetchHoldingsUseCase::class.java)
        holdingDomainToUiMapper = mock(HoldingDomainToUiMapper::class.java)
        calculatePortfolioSummaryUseCase = mock(CalculatePortfolioSummaryUseCase::class.java)
        portfolioSummaryDomainToUiMapper = mock(PortfolioSummaryDomainToUiMapper::class.java)

        factory = PortfolioViewModelFactory(
            fetchHoldingsUseCase,
            holdingDomainToUiMapper,
            calculatePortfolioSummaryUseCase,
            portfolioSummaryDomainToUiMapper
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `create should return PortfolioViewModel when modelClass is PortfolioViewModel`() {
        // Act
        val viewModel = factory.create(PortfolioViewModel::class.java)

        // Assert
        Assert.assertNotNull(viewModel)
        Assert.assertTrue(viewModel is PortfolioViewModel)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `create should throw exception when unknown ViewModel class is requested`() {
        factory.create(FakeViewModel::class.java)
    }

    class FakeViewModel : ViewModel()
}
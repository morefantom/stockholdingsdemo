package com.prathamesh.stockholdingsdemo.ui.main.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.prathamesh.stockholdingsdemo.domain.usecase.CalculatePortfolioSummaryUseCase
import com.prathamesh.stockholdingsdemo.domain.usecase.FetchHoldingsUseCase
import com.prathamesh.stockholdingsdemo.ui.StockHoldingsDemoApp
import com.prathamesh.stockholdingsdemo.ui.commons.mapper.HoldingDomainToUiMapper
import com.prathamesh.stockholdingsdemo.ui.commons.mapper.PortfolioSummaryDomainToUiMapper
import com.prathamesh.stockholdingsdemo.ui.portfolio.view.PortfolioRoute
import com.prathamesh.stockholdingsdemo.ui.main.di.MainActivityComponent
import com.prathamesh.stockholdingsdemo.ui.main.intent.BottomNavState
import com.prathamesh.stockholdingsdemo.ui.theme.PrathameshStockHoldingsDemoTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    lateinit var activityComponent: MainActivityComponent

    @Inject
    lateinit var fetchHoldingsUseCase: FetchHoldingsUseCase

    @Inject
    lateinit var calculatePortfolioSummaryUseCase: CalculatePortfolioSummaryUseCase

    @Inject
    lateinit var holdingDomainToUiMapper: HoldingDomainToUiMapper

    @Inject
    lateinit var portfolioSummaryDomainToUiMapper: PortfolioSummaryDomainToUiMapper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityComponent = (application as StockHoldingsDemoApp)
            .appComponent
            .mainActivityComponent()
            .create()

        activityComponent.inject(this)

        enableEdgeToEdge()
        setContent {
            val navHostController = rememberNavController()
            val navBackStackEntry by navHostController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            PrathameshStockHoldingsDemoTheme {
                Scaffold(
                    modifier = Modifier.Companion.fillMaxSize(),
                    topBar = {
                        MainAppBar(
                            title = currentRoute,
                        )
                    },
                    bottomBar = {
                        MainBottomNavBar(
                            currentRoute = currentRoute,
                            navHostController = navHostController,
                        )
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navHostController,
                        startDestination = BottomNavState.PORTFOLIO.route,
                        modifier = Modifier.Companion.padding(innerPadding)
                    ) {
                        composable(BottomNavState.WATCHLIST.route) {
                            Text(it.destination.route ?: "null")
                        }
                        composable(BottomNavState.ORDERS.route) {
                            Text(it.destination.route ?: "null")
                        }
                        composable(BottomNavState.PORTFOLIO.route) {
                            PortfolioRoute(
                                fetchHoldingsUseCase = fetchHoldingsUseCase,
                                holdingDomainToUiMapper = holdingDomainToUiMapper,
                                calculatePortfolioSummaryUseCase = calculatePortfolioSummaryUseCase,
                                portfolioSummaryDomainToUiMapper = portfolioSummaryDomainToUiMapper,
                            )
                        }
                        composable(BottomNavState.FUNDS.route) {
                            Text(it.destination.route ?: "null")
                        }
                        composable(BottomNavState.INVEST.route) {
                            Text(it.destination.route ?: "null")
                        }
                    }
                }
            }
        }
    }
}
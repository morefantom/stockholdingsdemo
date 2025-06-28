package com.prathamesh.stockholdingsdemo.ui.main.intent

import androidx.annotation.StringRes
import com.prathamesh.stockholdingsdemo.R

enum class BottomNavState(
    @StringRes val label: Int,
    val route: String,
) {
    WATCHLIST(label = R.string.main_bottom_nav_watchlist, route = "watchlist"),
    ORDERS(label = R.string.main_bottom_nav_orders, route = "orders"),
    PORTFOLIO(label = R.string.main_bottom_nav_portfolio, route = "portfolio"),
    FUNDS(label = R.string.main_bottom_nav_funds, route = "funds"),
    INVEST(label = R.string.main_bottom_nav_invest, route = "invest"),
}
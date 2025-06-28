package com.prathamesh.stockholdingsdemo.ui.main.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.prathamesh.stockholdingsdemo.ui.main.intent.BottomNavState

@Composable
fun MainBottomNavBar(
    currentRoute: String?,
    navHostController: NavHostController,
) {
    Box(
        modifier = Modifier
            .padding(start = 4.dp, end = 4.dp)
    ) {
        NavigationBar {
            BottomNavState.entries.forEach { item ->
                NavigationBarItem(
                    selected = currentRoute == item.route,
                    onClick = {
                        if (currentRoute != item.route) {
                            navHostController.navigate(item.route) {
                                popUpTo(navHostController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    icon = {
                        Text(stringResource(item.label))
                    }
                )
            }
        }
    }
}

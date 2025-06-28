package com.prathamesh.stockholdingsdemo.ui.main.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppBar(
    title: String?,
) {
    Box(
        modifier = Modifier
            .padding(start = 4.dp, end = 4.dp),
    ) {
        TopAppBar(
            title = { title?.let { Text(title) } }
        )
    }

}
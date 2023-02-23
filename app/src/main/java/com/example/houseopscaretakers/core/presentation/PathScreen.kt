package com.example.houseopscaretakers.core.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.houseopscaretakers.core.presentation.components.PathItem

@Composable
fun PathScreen(
    navHostController: NavHostController
) {

    val lazyListState = rememberLazyListState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary),
    ) {

        LazyColumn(
            content = {
                items(
                    items = com.example.houseopscaretakers.core.Constants.routePaths
                ) {
                    PathItem(
                        title = it.title,
                        icon = it.icon,
                        onClick = {
                            //  open spcific path
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .height(250.dp)
                    )
                    
                    Spacer(modifier = Modifier.height(24.dp))
                }
            },
            state = lazyListState,
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )

    }

}
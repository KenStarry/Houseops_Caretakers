package com.example.houseopscaretakers.core.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ModeNight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.houseopscaretakers.core.presentation.components.PathItem
import com.example.houseopscaretakers.core.presentation.components.path_item.PathGreetings

@Composable
fun PathScreen(
    navHostController: NavHostController
) {

    val lazyListState = rememberLazyListState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        //  greeting section
        PathGreetings(
            icon = Icons.Outlined.ModeNight,
            title = "Good Evening,"
        )

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
                            .fillMaxWidth(0.7f)
                            .height(200.dp)
                    )
                    
                    Spacer(modifier = Modifier.height(24.dp))
                }
            },
            state = lazyListState,
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        )

        //  next button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.End
        ) {



        }

    }

}
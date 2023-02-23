package com.example.houseopscaretakers.core.presentation.components.path_item

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowRight
import androidx.compose.material.icons.outlined.ArrowRightAlt
import androidx.compose.material.icons.outlined.ModeNight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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
            title = "Good Evening,",
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
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
                            //  select path
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
                .fillMaxWidth()
                .fillMaxHeight(0.9f)
                .weight(6f)
        )

        //  next button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.End
        ) {

            Button(
                onClick = { /*TODO*/ },
                contentPadding = PaddingValues(
                    horizontal = 16.dp
                ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = Color.White,
                    disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                    disabledContentColor = Color.White.copy(alpha = 0.5f)
                ),
                enabled = false
            ) {
                Text(
                    text = "Next",
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.width(8.dp))

                Icon(
                    imageVector = Icons.Outlined.ArrowRightAlt,
                    contentDescription = "Arrow right",
                    tint = Color.White
                )
            }

        }

    }

}
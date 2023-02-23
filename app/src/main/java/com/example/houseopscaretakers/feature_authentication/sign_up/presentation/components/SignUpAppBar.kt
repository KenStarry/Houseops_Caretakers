package com.example.houseopscaretakers.feature_authentication.sign_up.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.houseopscaretakers.core.Constants
import com.example.houseopscaretakers.core.presentation.components.HomePillBtns
import com.example.houseopscaretakers.core.presentation.model.RoutePath

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpAppBar(
    startIcon: ImageVector?,
    title: String?,
    userType: String?,
    onBackPressed: () -> Unit
) {

    LargeTopAppBar(
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = title ?: "",
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    fontWeight = FontWeight.Bold
                )

                //  user type
                userType?.let { user ->

                    val routePath: RoutePath = Constants.routePaths.filter { it.title == user }[0]

                    HomePillBtns(
                        icon = routePath.icon,
                        title = routePath.title,
                        primaryColor = MaterialTheme.colorScheme.primary,
                        tertiaryColor = MaterialTheme.colorScheme.tertiary,
                        onClick = {}
                    )
                }

            }
        },
        navigationIcon = {
            startIcon?.let {
                IconButton(onClick = {
                    onBackPressed()
                }) {
                    Icon(
                        imageVector = it,
                        contentDescription = "Start Icon"
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}
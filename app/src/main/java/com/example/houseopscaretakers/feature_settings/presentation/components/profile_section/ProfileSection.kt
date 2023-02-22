package com.example.houseops_revamped.feature_settings.presentation.components.profile_section

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Update
import androidx.compose.material.icons.outlined.Verified
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.example.houseopscaretakers.R
import com.example.houseopscaretakers.core.domain.model.UsersCollection
import com.example.houseopscaretakers.core.presentation.components.CoilImage
import com.example.houseopscaretakers.core.presentation.components.HomePillBtns
import com.example.houseopscaretakers.feature_settings.presentation.viewmodel.SettingsViewModel

@Composable
fun ProfileSection(
    modifier: Modifier = Modifier,
    userDetails: UsersCollection?,
    context: Context,
    settingsViewModel: SettingsViewModel,
    primaryColor: Color,
    tertiaryColor: Color
) {

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onSecondary,
            contentColor = MaterialTheme.colorScheme.onSecondary
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp)
        ) {

            //  user image
            Column(
                modifier = Modifier
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                CoilImage(
                    context = context,
                    imageUri = userDetails?.userImageUri?.toUri(),
                    placeholder = R.drawable.houseops_dark_final,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(80.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = userDetails?.userName ?: "",
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                )

            }

            Column(
                modifier = Modifier
                    .weight(2f)
                    .padding(start = 16.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {

                HomePillBtns(
                    icon = Icons.Outlined.Verified,
                    title = "Verified user",
                    primaryColor = primaryColor,
                    tertiaryColor = tertiaryColor,
                    onClick = {}
                )

                Spacer(modifier = Modifier.height(16.dp))

                //  email address
                Row(
                    modifier = Modifier
                        .wrapContentSize(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(30.dp)
                            .background(MaterialTheme.colorScheme.onPrimary),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Email,
                            contentDescription = "icon",
                            tint = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.6f),
                            modifier = Modifier
                                .size(16.dp)
                        )
                    }

                    Text(
                        text = userDetails?.userEmail ?: "",
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                    )

                }

                Spacer(modifier = Modifier.height(16.dp))

                //  update button
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    HomePillBtns(
                        icon = Icons.Outlined.Update,
                        title = "Update Details",
                        primaryColor = primaryColor,
                        tertiaryColor = tertiaryColor,
                        onClick = {}
                    )
                }
            }

        }

    }

}
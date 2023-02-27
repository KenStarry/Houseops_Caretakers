package com.example.houseopscaretakers.core.presentation.components.path_screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.houseopscaretakers.core.domain.model.CoreEvents
import com.example.houseopscaretakers.core.presentation.components.PathItem
import com.example.houseopscaretakers.core.presentation.viewmodel.CoreViewModel
import com.example.houseopscaretakers.navigation.Direction
import com.example.houseopscaretakers.navigation.Screen
import kotlinx.coroutines.flow.collect
import java.util.Calendar

@Composable
fun PathScreen(
    navHostController: NavHostController
) {

    val lazyListState = rememberLazyListState()
    val coreVM: CoreViewModel = hiltViewModel()
    val context = LocalContext.current
    val direction = Direction(navHostController)

    val calendar = Calendar.getInstance()
    val savedUser = coreVM.userTypeFlow.collectAsState(initial = null).value

    val currentHour by remember {
        mutableStateOf(calendar.get(Calendar.HOUR_OF_DAY))
    }

    val greetingsText by remember {
        mutableStateOf(
            when (currentHour) {
                in 0..12 -> {
                    "Good Morning"
                }
                in 12..16 -> {
                    "Good Afternoon"
                }
                else -> {
                    "Good Evening"
                }
            }
        )
    }

    val greetingsIcon by remember {
        mutableStateOf(
            when (currentHour) {
                in 0..12 -> {
                    Icons.Outlined.WbSunny
                }
                in 12..16 -> {
                    Icons.Outlined.WbCloudy
                }
                else -> {
                    Icons.Outlined.ModeNight
                }
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        //  greeting section
        PathGreetings(
            icon = greetingsIcon,
            title = greetingsText,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        )

        LazyColumn(
            content = {
                items(
                    items = com.example.houseopscaretakers.core.Constants.routePaths
                ) {

                    val currentPath = it

                    PathItem(
                        title = it.title,
                        icon = it.icon,

                        containerColor = if (currentPath == coreVM.pathSelected.value)
                            MaterialTheme.colorScheme.tertiary
                        else
                            MaterialTheme.colorScheme.onSecondary,

                        elevation = if (currentPath.equals(coreVM.pathSelected))
                            8.dp
                        else
                            0.dp,

                        onClick = {
                            //  select path
                            coreVM.onEvent(CoreEvents.SelectPath(it))
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
                .fillMaxHeight(0.8f)
        )

        //  next button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.End
        ) {

            TextButton(onClick = { /*TODO*/ }) {
                Text(
                    text = "Exit",
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = {
                    //  save user type to datastore
                    coreVM.pathSelected.value?.title?.let { userType ->
                        coreVM.onEvent(CoreEvents.DatastoreSaveUserType(userType))

                        //  navigate to login activity
                        direction.navigateToRoute(Screen.Login.passUserType(user = userType))
                    }

                },
                contentPadding = PaddingValues(
                    horizontal = 16.dp
                ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = Color.White,
                    disabledContainerColor = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.3f),
                    disabledContentColor = Color.White.copy(alpha = 0.5f)
                ),
                enabled = coreVM.pathSelected.value != null
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
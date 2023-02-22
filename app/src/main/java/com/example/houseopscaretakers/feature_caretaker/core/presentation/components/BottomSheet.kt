package com.example.houseopscaretakers.feature_caretaker.core.presentation.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheet(
    sheetBackgroundColor: Color,
    sheetContent: @Composable (state: ModalBottomSheetState, scope: CoroutineScope) -> Unit,
    sheetScope: @Composable (state: ModalBottomSheetState, scope: CoroutineScope) -> Unit,
    closeBottomSheet: (state: ModalBottomSheetState, scope: CoroutineScope) -> Unit
) {

    //  initial bottom sheet state
    val modalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )
    val scope = rememberCoroutineScope()

    //  our botom sheet layout
    ModalBottomSheetLayout(
        sheetContent = { sheetContent(modalBottomSheetState, scope) },
        sheetState = modalBottomSheetState,
        sheetElevation = 0.dp,
        sheetBackgroundColor = sheetBackgroundColor,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    ) {
        //  what will be enclosed by our bottom sheet
        sheetScope(modalBottomSheetState, scope)
    }

    //  on back pressed
    BackHandler(
        enabled = modalBottomSheetState.isVisible,
        onBack = {
            //  close bottomsheet
            closeBottomSheet(modalBottomSheetState, scope)
        }
    )

}




















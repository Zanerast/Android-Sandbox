package com.astrick.compose.bottomsheet

import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun LockedModalExample() {
    LockedModal {
        Column(modifier = Modifier.fillMaxSize(0.8f)) {
            Text(text = "This can't be cancelled")
        }
    }
}

/**
 * An example of a bottom sheet that cannot be swiped away
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LockedModal(
    sheetContent: @Composable () -> Unit
) {
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { false },
        skipHalfExpanded = true,
        animationSpec = tween(500)
    )

    val scope = rememberCoroutineScope()
    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetGesturesEnabled = false,
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        sheetContent = {
            sheetContent()
        },
        content = {
            Box(modifier = Modifier.fillMaxSize()) {
                Button(
                    onClick = {
                        scope.launch {
                            modalSheetState.show()
                        }
                    }
                ) {
                    Text(text = "Click me")
                }
            }
        }
    )
}

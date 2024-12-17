package com.astrick.sandbox.compose.bottomsheet

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

/**
 * Displays a button to trigger a locked bottom sheet.
 *
 * This example demonstrates a modal bottom sheet that cannot be dismissed
 * by tapping outside. The bottom sheet must be dismissed programmatically.
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LockedModalBottomSheetExample() {
    var showBottomSheet by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text("Show bottom sheet") },
                icon = { Icon(Icons.Filled.Add, contentDescription = "") },
                onClick = {
                    showBottomSheet = true
                }
            )
        }
    ) { _ ->
        LockedModalBottomSheet(
            showBottomSheet = showBottomSheet,
            onDismiss = {
                showBottomSheet = false
            }
        )
    }
}

/**
 * A modal bottom sheet that cannot be dismissed by tapping outside.
 *
 * @param showBottomSheet Controls whether the bottom sheet is shown.
 * @param onDismiss Callback triggered when the bottom sheet is dismissed.
 *
 * Reference: developer.android.com/develop/ui/compose/components/bottom-sheets
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LockedModalBottomSheet(
    showBottomSheet: Boolean,
    onDismiss: () -> Unit = {}
) {

    val sheetState = rememberModalBottomSheetState(
        confirmValueChange = { false } // Prevents dismissing the sheet by outside interaction
    )

    val scope = rememberCoroutineScope()
    if (showBottomSheet) {
        ModalBottomSheet(
            sheetState = sheetState,
            content = {
                Box(Modifier.fillMaxSize()) {
                    Button(onClick = {
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                onDismiss()
                            }
                        }
                    }) {
                        Text("Hide bottom sheet")
                    }
                }
            },
            onDismissRequest = onDismiss
        )
    }
}

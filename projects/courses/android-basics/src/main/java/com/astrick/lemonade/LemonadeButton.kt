package com.astrick.lemonade

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.astrick.core.ui.theme.Grey100
import com.astrick.core.ui.theme.Spacing

@Composable
fun LemonadeButton(
    uiState: LemonadeUiState,
    onClick: (LemonadeUiState) -> Unit
) {
    val requiredClicks = (2..4).random()
    var clicks = 0
    Button(
        onClick = {
            val newUiState = when (uiState) {
                LemonadeUiState.LemonTree -> LemonadeUiState.Lemon
                LemonadeUiState.Lemon -> {
                    clicks++
                    if (clicks >= requiredClicks)
                        LemonadeUiState.Lemonade
                    else
                        LemonadeUiState.Lemon
                }
                
                LemonadeUiState.Lemonade -> LemonadeUiState.EmptyGlass
                LemonadeUiState.EmptyGlass -> LemonadeUiState.LemonTree
            }
            onClick(newUiState)
        },
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.Grey100,
            containerColor = Color.Transparent,
        ),
    ) {
        Image(
            painter = painterResource(id = uiState.imageResId),
            contentDescription = stringResource(id = uiState.contentDescriptionId),
            modifier = Modifier
                .padding(
                    horizontal = Spacing.Normal,
                    vertical = Spacing.Large
                )
        )
    }
}

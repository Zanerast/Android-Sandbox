package com.astrick.compose.navigation.samples.lunch_tray

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.astrick.compose.R
import com.astrick.compose.navigation.samples.lunch_tray.ui.theme.LunchTrayTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LunchTrayTopAppBar(
    title: String,
    navigateUp: Boolean,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = {
            if (navigateUp) {
                IconButton(
                    onClick = onBack
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.back_button)
                    )
                }
            }
        },
        modifier = modifier
    )
}

// Previews
@Preview
@Composable
fun LunchTrayTopAppBarPreview() {
    LunchTrayTheme {
        LunchTrayTopAppBar(
            "OrderCheckout",
            false,
            {}
        )
    }
}

@file:OptIn(ExperimentalMaterial3Api::class)

package com.astrick.unit3.woof

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.astrick.androidbasicscompose.R
import com.astrick.unit3.woof.data.dogs
import com.astrick.unit3.woof.theme.WoofTheme

@Composable
fun WoofApp() {
    WoofTheme {
        Scaffold(
            topBar = {
                WoofTopAppBar()
            }
        ) { padding ->
            LazyColumn(contentPadding = padding) {
                items(dogs.size) {
                    DogItem(
                        dog = dogs[it],
                        modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
                    )
                }
            }
        }
    }
}

@Composable
fun WoofTopAppBar(
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(dimensionResource(id = R.dimen.image_size))
                        .padding(dimensionResource(id = R.dimen.padding_small)),
                    painter = painterResource(R.drawable.ic_woof_logo),
                    contentDescription = null
                )
                Text(
                    text = stringResource(R.string.woof),
                    style = MaterialTheme.typography.displayLarge,
                )
            }
        },
        modifier = modifier
    )
}

/**
 * Composable that displays what the UI of the app looks like in light theme in the design tab.
 */
@Preview(showSystemUi = true)
@Composable
fun WoofPreview() {
    WoofTheme(darkTheme = false) {
        WoofApp()
    }
}

@Preview(showSystemUi = true)
@Composable
fun DarkWoofPreview() {
    WoofTheme(darkTheme = true) {
        WoofApp()
    }
}

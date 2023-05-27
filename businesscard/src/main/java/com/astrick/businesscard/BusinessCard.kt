package com.astrick.businesscard

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.astrick.core.ui.theme.BaseComposeTheme

@Composable
fun BusinessCard() {

}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun PreviewBusinessCard() {
    BaseComposeTheme {
        BusinessCard()
    }
}

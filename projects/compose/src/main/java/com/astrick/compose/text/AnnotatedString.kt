package com.astrick.compose.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.astrick.core.ui.theme.BaseComposeTheme

@Composable
fun AnnotatedStringExample() {
    val text = buildAnnotatedString {
        append("Hello there.")
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append(" This is bold")
        }
    }
    Text(text = text)
}

@Preview
@Composable
private fun MainPreview() {
    BaseComposeTheme {
        AnnotatedStringExample()
    }
}

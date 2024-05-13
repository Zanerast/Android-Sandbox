package com.astrick.compose.text

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun TextInputSelectAllOnFocus(
    text: String,
    onValueChanged: (value: String) -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    var textState by remember { mutableStateOf(TextFieldValue(text = text)) }

    LaunchedEffect(isFocused) {
        val endRange = if (isFocused) textState.text.length else 0
        textState = textState.copy(
            selection = TextRange(
                start = 0,
                end = endRange
            )
        )
    }
    BasicTextField(
        value = textState,
        onValueChange = {
            onValueChanged(it.text)
            textState = it
        },
        interactionSource = interactionSource
    )
}

package com.astrick.sandbox.compose.text

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue

/**
 * A composable TextField that automatically selects all text when focused.
 *
 * @param input The initial text input.
 * @param onValueChanged Callback to handle text value changes.
 */
@Composable
fun TextFieldWithAutoSelectAllOnFocus(
    input: String,
    onValueChanged: (value: String) -> Unit,
    modifier: Modifier = Modifier
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    var textRange by remember { mutableStateOf(TextRange(0)) }
    var textFieldValue by remember(input) { mutableStateOf(TextFieldValue(text = input, selection = textRange)) }

    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    LaunchedEffect(isFocused) {
        val endRange = if (isFocused) textFieldValue.text.length else 0
        textFieldValue = textFieldValue.copy(
            selection = TextRange(
                start = 0,
                end = endRange
            )
        )
    }

    OutlinedTextField(
        value = textFieldValue,
        onValueChange = { value ->
            onValueChanged(value.text)
            textRange = value.selection
            textFieldValue = value
        },
        interactionSource = interactionSource,
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                focusManager.clearFocus(force = true)
            }
        ),
        modifier = modifier,
    )
}

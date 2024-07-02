package com.astrick.compose.text

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import com.astrick.core.extensions.checkIfInt
import com.astrick.core.extensions.remove
import com.astrick.core.extensions.removeLetters
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.Locale

@Composable
fun AutoSelectAllOnTouchTextField(
    input: String,
    onValueChanged: (value: String) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    // A whole bunch of remembers required to get this working correctly
    var text by remember(input) { mutableStateOf(input) }
    // Selection is in a remember as the cursor was going back to position 0 after typing
    // and this seems to fix it (no idea why though)
    var selection by remember { mutableStateOf(TextRange(0)) }
    var textState by remember(text) { mutableStateOf(TextFieldValue(text = text, selection)) }

    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

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
        onValueChange = { value ->
            onValueChanged(value.text)
            selection = value.selection
            textState = value
        },
        interactionSource = interactionSource,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        modifier = Modifier.onFocusChanged { focusState ->
            if (!focusState.hasFocus) {
                textState = TextFieldValue(text = text, selection)
            }
        },
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                focusManager.clearFocus(force = true)
            }
        ),
        maxLines = 1
    )
}

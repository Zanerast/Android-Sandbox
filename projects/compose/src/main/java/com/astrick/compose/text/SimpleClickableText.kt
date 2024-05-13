package com.astrick.compose.text

import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.astrick.compose.annotations.SandboxPreviews
import com.astrick.core.ui.theme.SandboxTheme

@Composable
fun SimpleClickableText(
    text: String,
    textToHighlight: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val tag = "on_click_tag"
    val spannable = text.createClickableAnnotatedString(textToHighlight = textToHighlight, tag = tag)
    ClickableText(
        text = spannable,
        onClick = {offset ->
          spannable.getStringAnnotations(offset, offset)
              .firstOrNull {it.tag == tag}
              ?.let {
                  onClick()
              }
        },
        modifier = modifier
    )
}

@Composable
private fun String.createClickableAnnotatedString(
    textToHighlight: String,
    tag: String
): AnnotatedString {
    val startIndex = indexOf(textToHighlight, ignoreCase = true)
    val endIndex = startIndex.plus(textToHighlight.length)
    if (startIndex == -1) {
        return buildAnnotatedString {
            append(this@createClickableAnnotatedString)
        }
    }
    
    val firstPart = substring(0, startIndex)
    val lastPart = substring(endIndex, length)
    
    return buildAnnotatedString {
        withStyle(style = SandboxTheme.font.spannableStyle) {
            append(firstPart)
        }
        pushStringAnnotation(
            tag = tag,
            annotation = substring(startIndex, endIndex)
        )
        withStyle(style = SandboxTheme.font.spannableStyleLink) {
            append(substring(startIndex, endIndex))
        }
        pop()
        withStyle(style = SandboxTheme.font.spannableStyle) {
            append(lastPart)
        }
    }
}

@SandboxPreviews
@Composable
private fun MainPreviews() {
    SandboxTheme {
        SimpleClickableText(
            text = "This is a text with a link in the middle",
            textToHighlight = "text with a link",
            onClick = {
            
            }
        )
    }
}

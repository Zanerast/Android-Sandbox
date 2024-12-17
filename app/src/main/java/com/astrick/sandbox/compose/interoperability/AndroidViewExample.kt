package com.astrick.sandbox.compose.interoperability

import android.os.Build
import android.widget.TextView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

/**
 * Displays a `TextView` in Compose using `AndroidView`.
 *
 * @param modifier A [Modifier] for styling or layout purposes. Defaults to [Modifier].
 * @param text The content to be displayed in the TextView
 */
@Composable
fun AndroidViewSample(
    modifier: Modifier = Modifier,
    text: String = "Something",
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            TextView(context).apply {
                textSize = 15f

                val lineHeight = (22 * resources.displayMetrics.scaledDensity).toInt()
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P)
                    setLineHeight(lineHeight)
            }
        },
        update = { textView ->
            textView.text = text
        }
    )
}

package com.astrick.compose.interoperability

import android.os.Build
import android.widget.TextView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat

@Composable
fun HtmlTextView(
    modifier: Modifier = Modifier,
    instructions: String = "Something",
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
        update = {
            it.text = HtmlCompat.fromHtml(
                instructions,
                HtmlCompat.FROM_HTML_MODE_COMPACT
            )
        }
    )
}

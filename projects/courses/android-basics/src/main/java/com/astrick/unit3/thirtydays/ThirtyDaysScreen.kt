package com.astrick.unit3.thirtydays

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.astrick.androidbasicscompose.R
import com.astrick.unit3.thirtydays.data.ThirtyDaysRepo
import com.astrick.unit3.thirtydays.theme.ThirtyDaysTheme

@Composable
fun ThirtyDayScreen() {
    val quote = ThirtyDaysRepo.quotes
    ThirtyDaysTheme {
        Scaffold(topBar = {
            Text(
                text = stringResource(id = R.string.thirty_days_name),
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier.padding(start = 8.dp)
            )
        }) { padding ->
            LazyColumn(
                contentPadding = padding,
                modifier = Modifier.background(MaterialTheme.colorScheme.background)
            ) {
                items(quote.size) {
                    QuoteCard(
                        quote = quote[it],
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
        
    }
}

// Previews
@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun ThirtyDayScreenPreview() {
    ThirtyDaysTheme {
        ThirtyDayScreen()
    }
}

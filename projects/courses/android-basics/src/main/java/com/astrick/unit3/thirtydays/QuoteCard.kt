package com.astrick.unit3.thirtydays

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.astrick.androidbasicscompose.R
import com.astrick.unit3.thirtydays.data.InspirationalQuote
import com.astrick.unit3.thirtydays.data.ThirtyDaysRepo
import com.astrick.unit3.thirtydays.theme.ThirtyDaysTheme

@Composable
fun QuoteCard(
    quote: InspirationalQuote,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }
    
    Card(
        modifier = modifier
            .clickable { isExpanded = !isExpanded }
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
        ) {
            
            Text(
                text = stringResource(
                    id = R.string.day_x,
                    quote.day
                ),
                style = MaterialTheme.typography.labelMedium
            )
            Text(
                text = quote.title,
                style = MaterialTheme.typography.titleLarge
            )
            Image(
                painter = painterResource(id = quote.drawableId),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            )
            
            if (isExpanded) {
                Text(
                    text = quote.content,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }
    }
}

// Preview
@Preview
@Composable
fun QuoteCardOnePreview() {
    ThirtyDaysTheme {
        QuoteCard(quote = ThirtyDaysRepo.quotes[0])
    }
}

@Preview
@Composable
fun QuoteCardTwoPreview() {
    ThirtyDaysTheme {
        QuoteCard(quote = ThirtyDaysRepo.quotes[1])
    }
}

@Preview
@Composable
fun QuoteCardThreePreview() {
    ThirtyDaysTheme {
        QuoteCard(quote = ThirtyDaysRepo.quotes[2])
    }
}

package com.astrick.unit3.affirmations

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.astrick.unit3.affirmations.data.Affirmation
import com.astrick.unit3.affirmations.data.Datasource
import com.astrick.androidbasicscompose.R
import com.astrick.core.ui.theme.SandboxTheme

@Composable
fun Affirmations() {
    AffirmationList(affirmations = Datasource().loadAffirmations())
}

@Composable
fun AffirmationList(
    affirmations: List<Affirmation>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(affirmations.size) {
            AffirmationCard(
                affirmation = affirmations[it],
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun AffirmationCard(
    affirmation: Affirmation,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Column {
            Image(
                painter = painterResource(id = affirmation.imageResourceId),
                contentDescription = stringResource(id = affirmation.stringResourceId),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = stringResource(id = affirmation.stringResourceId),
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineSmall
            )
            
        }
    }
}

// Previews
@Preview
@Composable
fun CardPreview() {
    SandboxTheme {
        AffirmationCard(
            affirmation = Affirmation(
                R.string.affirmation1,
                R.drawable.affirmations1
            )
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun ListPreview() {
    SandboxTheme {
        AffirmationList(affirmations = Datasource().loadAffirmations())
    }
}

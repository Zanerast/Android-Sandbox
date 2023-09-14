package com.astrick.unit3.superhero

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.astrick.androidbasicscompose.R
import com.astrick.unit3.superhero.data.HeroesRepository
import com.astrick.unit3.superhero.data.SuperHero
import com.astrick.unit3.superhero.theme.SuperHeroTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroesScreen(
    heroes: List<SuperHero>,
) {
    SuperHeroTheme {
        Scaffold(topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.hero_app_name),
                        style = MaterialTheme.typography.displayLarge
                    )
                }
            )
        }) { padding ->
            LazyColumn(contentPadding = padding) {
                items(heroes.size) {
                    HeroesCard(
                        hero = heroes[it],
                        modifier = Modifier.padding(
                            vertical = 4.dp,
                            horizontal = 8.dp
                        )
                    )
                }
            }
        }
        
    }
}

// Previews
@Preview(showSystemUi = true)
@Composable
fun HeroListPreview() {
    SuperHeroTheme {
        HeroesScreen(heroes = HeroesRepository.heroes)
    }
}

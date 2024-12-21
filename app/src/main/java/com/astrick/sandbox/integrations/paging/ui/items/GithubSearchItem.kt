package com.astrick.sandbox.integrations.paging.ui.items

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ForkLeft
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.astrick.compose.R
import com.astrick.sandbox.integrations.paging.domain.GithubRepoDetails

@Composable
fun GithubSearchItem(githubRepoDetails: GithubRepoDetails) {
    
    ConstraintLayout(
        Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .border(2.dp, Color.Black, RoundedCornerShape(6.dp))
            .clip(RoundedCornerShape(6.dp))
    ) {
        val starsGuide = createGuidelineFromStart(0.5f)
        val forksGuide = createGuidelineFromStart(0.75f)
        val (fullName, description, language, stars, starsText, forks, forksText) = createRefs()
        
        Text(
            githubRepoDetails.fullName, style = MaterialTheme.typography.titleLarge, modifier = Modifier
                .padding(8.dp)
                .constrainAs(fullName) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }, color = Color.Blue
        )
        
        Text(
            text = githubRepoDetails.description.toString(),
            style = MaterialTheme.typography.titleMedium,
            maxLines = 5,
            modifier = Modifier
                .padding(8.dp)
                .constrainAs(description) {
                    top.linkTo(fullName.bottom)
                    start.linkTo(parent.start)
                }
        )
        
        Text(
            text = stringResource(R.string.language, githubRepoDetails.language.toString()),
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier
                .padding(8.dp)
                .constrainAs(language) {
                    start.linkTo(parent.start)
                    top.linkTo(description.bottom)
                    bottom.linkTo(parent.bottom)
                }
        )
        
        Icon(
            imageVector = Icons.Filled.Star,
            contentDescription =  "Stars",
             modifier = Modifier
                 .constrainAs(stars) {
                     start.linkTo(starsGuide)
                     bottom.linkTo(parent.bottom)
                     top.linkTo(description.bottom)
                 }
        )
        Text(
            githubRepoDetails.stars.toString(), style = MaterialTheme.typography.labelMedium,
            modifier = Modifier
                .padding(8.dp)
                .constrainAs(starsText) {
                    start.linkTo(stars.end)
                    bottom.linkTo(parent.bottom)
                }
        )
        
        
        Icon(Icons.Filled.ForkLeft, "Forks",
             modifier = Modifier
                 .constrainAs(forks) {
                     start.linkTo(forksGuide)
                     bottom.linkTo(parent.bottom)
                     top.linkTo(description.bottom)
                 }
        )
        Text(
            githubRepoDetails.forks.toString(), style = MaterialTheme.typography.labelMedium,
            modifier = Modifier
                .padding(8.dp)
                .constrainAs(forksText) {
                    start.linkTo(forks.end)
                    bottom.linkTo(parent.bottom)
                }
        )
        
        
    }
    
}

@Preview(showBackground = true)
@Composable
fun PreviewRepoItem() {
    val githubSearchItemModel = GithubRepoDetails(
        "repo", "author/repo", "An awesome library that you need to do awesome stuff",
        "https://example.com", 20000, 10, "Kotlin"
    )
    
    GithubSearchItem(githubRepoDetails = githubSearchItemModel)
}

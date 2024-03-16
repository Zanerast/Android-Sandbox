package com.astrick.compose.lists.paging.ui.items

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ForkLeft
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.astrick.compose.R
import com.astrick.compose.lists.paging.data.remote.GithubSearchItemModel

@Composable
fun GithubSearchItem(githubSearchItemModel: GithubSearchItemModel) {
    
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
            githubSearchItemModel.fullName, style = MaterialTheme.typography.h5, modifier = Modifier
                .padding(8.dp)
                .constrainAs(fullName) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }, color = Color.Blue
        )
        
        Text(
            text = githubSearchItemModel.description.toString(),
            style = MaterialTheme.typography.subtitle1,
            maxLines = 5,
            modifier = Modifier
                .padding(8.dp)
                .constrainAs(description) {
                    top.linkTo(fullName.bottom)
                    start.linkTo(parent.start)
                }
        )
        
        Text(
            text = stringResource(R.string.language, githubSearchItemModel.language.toString()),
            style = MaterialTheme.typography.caption,
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
            githubSearchItemModel.stars.toString(), style = MaterialTheme.typography.caption,
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
            githubSearchItemModel.forks.toString(), style = MaterialTheme.typography.caption,
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
    val githubSearchItemModel = GithubSearchItemModel(
        1, "repo", "author/repo", "An awesome library that you need to do awesome stuff",
        "https://example.com", 20000, 10, "Kotlin"
    )
    
    GithubSearchItem(githubSearchItemModel = githubSearchItemModel)
    
    
}
